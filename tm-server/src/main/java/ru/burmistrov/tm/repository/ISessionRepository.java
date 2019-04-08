package ru.burmistrov.tm.repository;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.burmistrov.tm.entity.AbstractEntity;
import ru.burmistrov.tm.entity.Project;
import ru.burmistrov.tm.entity.Session;

public interface ISessionRepository extends JpaRepository<Session, Long> {

    Session save(@NotNull final Session session);

    @Nullable
    @Query(value = "SELECT session FROM Session session WHERE session.id = :sessionId AND session.userId = :userId")
    Session findOne(@NotNull @Param(value = "sessionId") final String id,
                    @NotNull @Param(value = "userId") final String userId);
}
