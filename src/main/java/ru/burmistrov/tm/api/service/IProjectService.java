package ru.burmistrov.tm.api.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.burmistrov.tm.entity.AbstractEntity;

import java.util.List;

public interface IProjectService<T extends AbstractEntity> {

    void remove(@Nullable String userId, @Nullable String projectId);

    @Nullable
    T persist(@Nullable String userId,@Nullable String name, @Nullable String description, @Nullable String dateEnd);

    void merge(@Nullable String userId,@Nullable String projectId,@Nullable String name,@Nullable String description,@Nullable String dateEnd);

    void removeAll(@Nullable String userId);

    @Nullable
    List<T> findAll(@Nullable String userId);

    @Nullable
    List<T> findAllSortByDateBegin(@Nullable String userId);

    @Nullable
    List<T> findAllSortByDateEnd(@Nullable String userId);

    @Nullable
    List<T> findAllSortByStatus(@NotNull String userId);

    @Nullable
    T findOneByName(@Nullable String userId, String name);

    @Nullable
    T findOneByDescription(@Nullable String userId, String description);

}
