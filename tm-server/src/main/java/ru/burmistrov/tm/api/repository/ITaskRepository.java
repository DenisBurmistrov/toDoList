package ru.burmistrov.tm.api.repository;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.burmistrov.tm.entity.AbstractEntity;
import ru.burmistrov.tm.entity.Task;
import ru.burmistrov.tm.repository.AbstractRepository;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public interface ITaskRepository {

    @Nullable
    Task persist(@NotNull final String userId, @NotNull final Date dateBegin,
                 @NotNull final Date dateEnd, @NotNull final String description,
                 @NotNull final String name, @NotNull final String projectId,
                 @NotNull final String status);

    void merge(@NotNull final Task abstractEntity);

    void remove(@NotNull final String id, @NotNull final String userId);

    void removeAll(@NotNull final String userId);

    @NotNull
    List<Task> findAll(@NotNull final String userId);

    void removeAllInProject(@NotNull final String userId, @NotNull final String projectId);

    @NotNull
    List<Task> findAllInProject(@NotNull final String userId, @NotNull final String projectId);

    @Nullable
    Task findOne(@NotNull final String id, @NotNull final String userId);

    @NotNull
    List<Task> findAllSortByDateBegin(@NotNull final String userId);

    @NotNull
    List<Task> findAllSortByDateEnd(@NotNull final String userId);

    @NotNull
    List<Task> findAllSortByStatus(@NotNull final String userId);

    @Nullable
    Task findOneByName(@NotNull final String userId, @NotNull final String name);

    @Nullable
    Task findOneByDescription(@NotNull final String userId, @NotNull final String description);

}
