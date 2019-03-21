package ru.burmistrov.tm.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.burmistrov.tm.api.repository.ISessionRepository;
import ru.burmistrov.tm.api.repository.IUserRepository;
import ru.burmistrov.tm.api.service.ISessionService;
import ru.burmistrov.tm.entity.enumerated.Role;
import ru.burmistrov.tm.entity.Session;
import ru.burmistrov.tm.entity.User;
import ru.burmistrov.tm.exception.ValidateAccessException;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

public class SessionService implements ISessionService {

    @NotNull
    private final ISessionRepository sessionRepository;

    @NotNull
    private final IUserRepository userRepository;

    public SessionService(@NotNull final ISessionRepository sessionRepository, @NotNull final IUserRepository userRepository) {
        this.sessionRepository = sessionRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Session persist(@NotNull final String userId) throws IOException, NoSuchAlgorithmException {
        Session session = new Session();
        session.setUserId(userId);
        return sessionRepository.persist(session);
    }

    @Override
    public boolean validate(@Nullable final Session session) throws CloneNotSupportedException, ValidateAccessException, NoSuchAlgorithmException {
        return sessionRepository.validate(session);
    }

    @Override
    public boolean validateAdmin(@Nullable final Session session) throws CloneNotSupportedException, ValidateAccessException, NoSuchAlgorithmException {
        if (validate(session)){
            User user = new User();
            user.setId(Objects.requireNonNull(session).getUserId());
            User foundedUser = userRepository.findOne(user);
            if (foundedUser != null) {
                return Objects.requireNonNull(foundedUser.getRole()).equals(Role.ADMINISTRATOR);
            }
        }
        throw new ValidateAccessException();
    }
}