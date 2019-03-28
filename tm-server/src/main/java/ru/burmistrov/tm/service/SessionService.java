package ru.burmistrov.tm.service;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.burmistrov.tm.repository.SessionRepository;
import ru.burmistrov.tm.repository.UserRepository;
import ru.burmistrov.tm.api.service.ISessionService;
import ru.burmistrov.tm.entity.Session;
import ru.burmistrov.tm.entity.User;
import ru.burmistrov.tm.entity.enumerated.Role;
import ru.burmistrov.tm.exception.ValidateAccessException;
import ru.burmistrov.tm.utils.PasswordUtil;
import ru.burmistrov.tm.utils.SignatureUtil;

import java.io.InputStream;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.Objects;
import java.util.Properties;

public class SessionService implements ISessionService {

    @Nullable
    private SessionRepository sessionRepository;

    @NotNull
    private final SqlSessionFactory sqlSessionFactory;

    public SessionService(@NotNull final SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    @Override
    public Session persist(@NotNull final String userId) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            try {
                sessionRepository = sqlSession.getMapper(SessionRepository.class);
                @Nullable final InputStream inputStream;
                Properties property = new Properties();

                inputStream = this.getClass().getClassLoader().getResourceAsStream("application.properties");
                property.load(inputStream);
                @NotNull final String cycle = property.getProperty("cycle");
                @NotNull final String salt = property.getProperty("salt");

                @NotNull final Session session = new Session();
                session.setTimesTamp(new Date().getTime());
                session.setUserId(userId);

                session.setSignature(SignatureUtil.sign(String.valueOf(session.hashCode()), Integer.parseInt(cycle), salt));

                Objects.requireNonNull(sessionRepository).persist(session.getId(), Objects.requireNonNull(session.getSignature()), session.getTimesTamp(), Objects.requireNonNull(session.getUserId()));
                Objects.requireNonNull(sqlSession).commit();
                return session;
            } catch (Exception e) {
                Objects.requireNonNull(sqlSession).rollback();
            }
            return null;
        }
    }

    @Override
    public boolean validate(@Nullable final Session session) throws CloneNotSupportedException, ValidateAccessException, NoSuchAlgorithmException {
        if (session == null) throw new ValidateAccessException();
        else if (session.getSignature() == null) throw new ValidateAccessException();
        else if (session.getUserId() == null) throw new ValidateAccessException();
        else if (session.getTimesTamp() == 0) throw new ValidateAccessException();
        final Session temp = (Session) session.clone();
        if (temp == null) throw new ValidateAccessException();
        @NotNull final String sourceSignature = PasswordUtil.hashPassword(String.valueOf(session.hashCode()));
        @NotNull final String targetSignature = PasswordUtil.hashPassword(String.valueOf(temp.hashCode()));
        boolean check = Objects.requireNonNull(sourceSignature).equals(targetSignature);
        if (!check) throw new ValidateAccessException();

        return findOne(session.getId(), session.getUserId()) != null;

    }

    @Override
    public boolean validateAdmin(@Nullable final Session session) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            try {
                sessionRepository = sqlSession.getMapper(SessionRepository.class);
                UserRepository userRepository = sqlSession.getMapper(UserRepository.class);
                if (validate(session)) {
                    User foundedUser = Objects.requireNonNull(userRepository).findOne((Objects.requireNonNull(Objects.requireNonNull(session).getUserId())));
                    if (foundedUser != null) {
                        return Objects.requireNonNull(foundedUser.getRole()).equals(Role.ADMINISTRATOR);
                    }
                }
                throw new ValidateAccessException();
            } catch (Exception e) {
                sqlSession.rollback();
            }
        }
        return false;
    }

    @Nullable
    private Session findOne(@NotNull final String id, @NotNull final String userId) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            try {
                sessionRepository = sqlSession.getMapper(SessionRepository.class);
                return Objects.requireNonNull(sessionRepository).findOne(id, userId);
            } catch (Exception e) {
                sqlSession.rollback();
            }
        }
        return null;
    }
}