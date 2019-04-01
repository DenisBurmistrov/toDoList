package ru.burmistrov.tm.api.repository;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.burmistrov.tm.entity.Session;

import javax.persistence.EntityManager;

public interface ISessionRepository {

    void persist(@NotNull final Session session);

    @Nullable
    Session findOne(@NotNull final String id,
                    @NotNull final String userId);

    EntityManager getEntityManager();
}
