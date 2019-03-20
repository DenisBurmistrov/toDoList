package ru.burmistrov.tm.service;

import org.jetbrains.annotations.NotNull;
import ru.burmistrov.tm.api.repository.ISessionRepository;
import ru.burmistrov.tm.api.endpoint.ISessionEndpoint;
import ru.burmistrov.tm.api.service.ISessionService;
import ru.burmistrov.tm.entity.Session;
import ru.burmistrov.tm.entity.User;

public class SessionService implements ISessionService {

    @NotNull
    private final ISessionRepository sessionRepository;

    public SessionService(@NotNull ISessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
    }

    @Override
    public Session persist(@NotNull String userId) {
        Session session = new Session();
        session.setUserId(userId);
        return sessionRepository.persist(session);
    }
}
