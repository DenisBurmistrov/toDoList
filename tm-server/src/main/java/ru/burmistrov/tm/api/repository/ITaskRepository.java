package ru.burmistrov.tm.api.repository;

import org.apache.ibatis.annotations.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.burmistrov.tm.entity.Task;
import ru.burmistrov.tm.entity.enumerated.Status;

import java.util.Date;
import java.util.List;

public interface ITaskRepository {

    void persist(@NotNull final Task task);

    void merge(@NotNull final Task task);

    void remove(@NotNull final String id, @NotNull final String userId);

    void removeAll(@NotNull final String userId);

    @NotNull
    List<Task> findAll(@NotNull final String userId);

    @Nullable
    Task findOne(@NotNull final String id, @NotNull final String userId);

    @Nullable
    Task findOneByName(@NotNull final String userId, @NotNull final String name);

    @Nullable
    Task findOneByDescription(@NotNull final String userId, @NotNull final String description);

    void removeAllInProject(@NotNull final String userId,
                            @NotNull final String projectId);

    @Nullable
    List<Task> findAllByProjectId(@NotNull final String userId, @NotNull final String projectId);
}
