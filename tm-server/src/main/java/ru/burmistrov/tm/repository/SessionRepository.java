package ru.burmistrov.tm.repository;

import org.jetbrains.annotations.NotNull;
import ru.burmistrov.tm.api.repository.ISessionRepository;
import ru.burmistrov.tm.entity.Project;
import ru.burmistrov.tm.entity.Session;

import java.util.LinkedHashMap;

public class SessionRepository extends AbstractRepository<Session> implements ISessionRepository {

    public SessionRepository(LinkedHashMap abstractMap) {
        super(abstractMap);
    }

    @NotNull
    private final LinkedHashMap<String, Session> sessions = getAbstractMap();

    @Override
    public Session persist(@NotNull String userId) {
        Session session = new Session();
        session.setUserId(userId);
        return session;
    }
}
