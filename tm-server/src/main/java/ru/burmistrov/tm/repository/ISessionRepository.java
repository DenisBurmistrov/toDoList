package ru.burmistrov.tm.repository;

import org.apache.deltaspike.data.api.FullEntityRepository;
import org.apache.deltaspike.data.api.Query;
import org.apache.deltaspike.data.api.QueryParam;
import org.apache.deltaspike.data.api.Repository;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.burmistrov.tm.entity.Session;

import javax.persistence.EntityManager;

@Repository
public interface ISessionRepository extends FullEntityRepository<Session, Long> {

    void persist(@NotNull final Session session);

    @Nullable
    @Query(value = "SELECT session FROM Session session WHERE session.id = :sessionId AND session.userId = :userId", max = 1)
    Session findOne(@NotNull @QueryParam(value = "sessionId") final String id,
                    @NotNull @QueryParam(value = "userId") final String userId);
}
