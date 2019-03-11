package ru.burmistrov.tm.api.service;

import org.jetbrains.annotations.NotNull;
import ru.burmistrov.tm.entity.AbstractEntity;

import java.util.List;

public interface ITaskService<T extends AbstractEntity> {

    @NotNull
    void merge(@NotNull String userId,@NotNull String projectId,@NotNull String oldName,@NotNull String newName,@NotNull String description);

    @NotNull
    T persist(@NotNull String userId,@NotNull String projectId,@NotNull String name,@NotNull String description);

    @NotNull
    List<T> findAll(@NotNull String userId,@NotNull String projectId);

    void removeAllInProject(@NotNull String userId,@NotNull String projectId);

    void remove(@NotNull String userId,@NotNull String projectId,@NotNull String taskId);

    void removeAll(@NotNull String userId);
}
