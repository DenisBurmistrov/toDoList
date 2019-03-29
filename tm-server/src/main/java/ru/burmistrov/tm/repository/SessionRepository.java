package ru.burmistrov.tm.repository;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.burmistrov.tm.api.repository.ISessionRepository;
import ru.burmistrov.tm.entity.Session;

import javax.persistence.EntityManager;

public class SessionRepository implements ISessionRepository {

    @NotNull final private EntityManager entityManager;

    public SessionRepository(@NotNull EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void persist(@NotNull final Session session) {
        entityManager.persist(session);
    }

    @Nullable
    @Override
    public Session findOne(@NotNull String id, @NotNull String userId) {
        return (Session) entityManager.createQuery("SELECT session FROM Session session WHERE session.id = '" + id + "' AND session.userId = '" + userId + "'").getSingleResult();
    }
}
