package ru.burmistrov.tm.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.burmistrov.tm.repository.ISessionRepository;
import ru.burmistrov.tm.repository.IUserRepository;
import ru.burmistrov.tm.api.service.ISessionService;
import ru.burmistrov.tm.entity.Session;
import ru.burmistrov.tm.entity.User;
import ru.burmistrov.tm.entity.enumerated.Role;
import ru.burmistrov.tm.exception.ValidateAccessException;
import ru.burmistrov.tm.util.PasswordUtil;
import ru.burmistrov.tm.util.SignatureUtil;

import javax.persistence.NoResultException;
import java.io.IOException;
import java.io.InputStream;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.Objects;
import java.util.Properties;

@Transactional
@Service
public class SessionService implements ISessionService {

    @Autowired
    private ISessionRepository sessionRepository;

    @Autowired
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
        return sessionRepository.save(session);
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
            return Objects.requireNonNull(sessionRepository).findOne(id, userId);
    }
}