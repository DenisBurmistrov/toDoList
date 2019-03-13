package ru.burmistrov.tm.api.service;

import org.jetbrains.annotations.Nullable;
import ru.burmistrov.tm.entity.AbstractEntity;

import java.util.List;

public interface ITaskService<T extends AbstractEntity> {

    @Nullable
    void merge(@Nullable String userId,@Nullable String projectId,@Nullable String oldName,@Nullable String newName,@Nullable String description, @Nullable String dateEnd);

    @Nullable
    T persist(@Nullable String userId,@Nullable String projectId,@Nullable String name,@Nullable String description, @Nullable String dateEnd);

    @Nullable
    List<T> findAll(@Nullable String userId,@Nullable String projectId);

    void removeAllInProject(@Nullable String userId,@Nullable String projectId);

    void remove(@Nullable String userId,@Nullable String projectId,@Nullable String taskId);

    void removeAll(@Nullable String userId);

    @Nullable
    List<T> findAllSortByDateBegin(@Nullable String userId);

    @Nullable
    List<T> findAllSortByDateEnd(@Nullable String userId);
}
