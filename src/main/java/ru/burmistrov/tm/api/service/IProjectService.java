package ru.burmistrov.tm.api.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.burmistrov.tm.entity.AbstractEntity;

import java.util.List;

public interface IProjectService<T extends AbstractEntity> {

    void remove(@Nullable String userId, @Nullable String projectId);

    @Nullable
    T persist(@Nullable String userId,@Nullable String name,@Nullable String merge);

    void merge(@Nullable String userId,@Nullable String projectId,@Nullable String name,@Nullable String description);

    void removeAll(@Nullable String userId);

    @Nullable
    List<T> findAll(@Nullable String userId);

    @Nullable
    List<T> findAllSortByDateBegin(@Nullable String userId);

}
