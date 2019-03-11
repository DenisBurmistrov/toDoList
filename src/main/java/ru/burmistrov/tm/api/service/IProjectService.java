package ru.burmistrov.tm.api.service;

import com.sun.istack.internal.NotNull;
import com.sun.istack.internal.Nullable;
import ru.burmistrov.tm.entity.AbstractEntity;

import java.util.List;

public interface IProjectService<T extends AbstractEntity> {

    void remove(@NotNull String userId,@NotNull String projectId);

    @NotNull
    T persist(@NotNull String userId,@NotNull String name,@NotNull String merge);

    void merge(@NotNull String userId,@NotNull String projectId,@NotNull String name,@NotNull String description);

    void removeAll(@NotNull String userId);

    @NotNull
    List<T> findAll(@NotNull String userId);

}
