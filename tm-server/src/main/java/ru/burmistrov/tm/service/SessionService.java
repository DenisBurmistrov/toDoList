package ru.burmistrov.tm.service;

import org.jetbrains.annotations.NotNull;
import ru.burmistrov.tm.api.repository.ISessionRepository;
import ru.burmistrov.tm.api.repository.IUserRepository;
import ru.burmistrov.tm.api.service.ISessionService;
import ru.burmistrov.tm.entity.enumerated.Role;
import ru.burmistrov.tm.entity.Session;
import ru.burmistrov.tm.entity.User;
import ru.burmistrov.tm.exception.ValidateAccessException;

import java.io.IOException;
import java.util.Objects;

public class SessionService implements ISessionService {

    @NotNull
    private final ISessionRepository sessionRepository;

    @NotNull
    private final IUserRepository userRepository;

    public SessionService(@NotNull ISessionRepository sessionRepository, @NotNull IUserRepository userRepository) {
        this.sessionRepository = sessionRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Session persist(@NotNull String userId) throws IOException {
        Session session = new Session();
        session.setUserId(userId);
        return sessionRepository.persist(session);
    }

    @Override
    public boolean validate(Session session) throws CloneNotSupportedException, ValidateAccessException {
        return sessionRepository.validate(session);
    }

    @Override
    public boolean validateAdmin(Session session) throws CloneNotSupportedException, ValidateAccessException {
        if (validate(session)){
            User user = new User();
            user.setId(session.getUserId());
            User foundedUser = userRepository.findOne(user);
            if (foundedUser != null) {
                return Objects.requireNonNull(foundedUser.getRole()).equals(Role.ADMINISTRATOR);
            }
        }
        throw new ValidateAccessException();
    }
}