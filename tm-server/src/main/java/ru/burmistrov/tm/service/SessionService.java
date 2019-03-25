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
import java.sql.SQLException;
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
    public Session persist(@NotNull final String userId) throws IOException, NoSuchAlgorithmException, SQLException {
        return sessionRepository.persist(userId);
    }

    @Override
    public boolean validate(@Nullable final Session session) throws CloneNotSupportedException, ValidateAccessException, NoSuchAlgorithmException, SQLException {
        return sessionRepository.validate(session);
    }

    @Override
    public boolean validateAdmin(@Nullable final Session session) throws CloneNotSupportedException, ValidateAccessException, NoSuchAlgorithmException, SQLException {
        if (validate(session)){
            User foundedUser = userRepository.findOne((Objects.requireNonNull(Objects.requireNonNull(session).getUserId())));
            if (foundedUser != null) {
                return Objects.requireNonNull(foundedUser.getRole()).equals(Role.ADMINISTRATOR);
            }
        }
        throw new ValidateAccessException();
    }
}