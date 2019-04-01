package ru.burmistrov.tm.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.burmistrov.tm.api.repository.IProjectRepository;
import ru.burmistrov.tm.api.repository.ISessionRepository;
import ru.burmistrov.tm.api.repository.IUserRepository;
import ru.burmistrov.tm.api.service.ISessionService;
import ru.burmistrov.tm.entity.Session;
import ru.burmistrov.tm.entity.User;
import ru.burmistrov.tm.entity.enumerated.Role;
import ru.burmistrov.tm.exception.ValidateAccessException;
import ru.burmistrov.tm.repository.SessionRepository;
import ru.burmistrov.tm.repository.UserRepository;
import ru.burmistrov.tm.utils.PasswordUtil;
import ru.burmistrov.tm.utils.SignatureUtil;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import java.io.IOException;
import java.io.InputStream;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.Objects;
import java.util.Properties;

public class SessionService implements ISessionService {

    @Inject
    private ISessionRepository sessionRepository;

    @Inject
    private IUserRepository userRepository;


    @Override
    public Session persist(@NotNull final String userId) throws IOException, NoSuchAlgorithmException {
        @Nullable final InputStream inputStream;
        @NotNull final Properties property = new Properties();
        inputStream = this.getClass().getClassLoader().getResourceAsStream("application.properties");
        property.load(inputStream);

        @NotNull final String cycle = property.getProperty("cycle");
        @NotNull final String salt = property.getProperty("salt");

        @NotNull final Session session = new Session();
        session.setTimesTamp(new Date().getTime());
        session.setUserId(userId);

        session.setSignature(SignatureUtil.sign(String.valueOf(session.hashCode()), Integer.parseInt(cycle), salt));

        try {
            sessionRepository.getEntityManager().getTransaction().begin();
            Objects.requireNonNull(sessionRepository).persist(session);
            sessionRepository.getEntityManager().getTransaction().commit();
            return session;
        } catch (Exception e) {
            sessionRepository.getEntityManager().getTransaction().rollback();
        }
        return null;
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
    public boolean validateAdmin(@Nullable final Session session) throws CloneNotSupportedException, ValidateAccessException, NoSuchAlgorithmException {
                if (validate(session)) {
                    User foundedUser = Objects.requireNonNull(userRepository).findOne((Objects.requireNonNull(Objects.requireNonNull(session).getUserId())));
                    if (foundedUser != null) {
                        return Objects.requireNonNull(foundedUser.getRole()).equals(Role.ADMINISTRATOR);
                    }
                }
                throw new ValidateAccessException();
    }

    @Nullable
    private Session findOne(@NotNull final String id, @NotNull final String userId) {
        try {
            return Objects.requireNonNull(sessionRepository).findOne(id, userId);
        } catch (NoResultException e) {
            return null;
        }
    }
}