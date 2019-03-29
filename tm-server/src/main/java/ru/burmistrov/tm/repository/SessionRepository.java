package ru.burmistrov.tm.repository;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.burmistrov.tm.api.repository.ISessionRepository;
import ru.burmistrov.tm.entity.Session;

import javax.inject.Inject;
import javax.persistence.EntityManager;

@Getter
@NoArgsConstructor
public class SessionRepository implements ISessionRepository {

    @Inject
    @NotNull private EntityManager entityManager;

    @Override
    public void persist(@NotNull final Session session) {
        entityManager.persist(session);
    }

    @Nullable
    @Override
    public Session findOne(@NotNull final String id, @NotNull final String userId) {
        return (Session) entityManager.createQuery("SELECT session FROM Session session WHERE session.id = '" + id + "' AND session.userId = '" + userId + "'").getSingleResult();
    }
}
