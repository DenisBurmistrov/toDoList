package ru.burmistrov.tm.api.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.burmistrov.tm.entity.AbstractEntity;

import java.text.ParseException;
import java.util.List;

public interface IProjectService<T extends AbstractEntity> {

    void remove(@NotNull String userId, @NotNull String projectId);

    @Nullable
    T persist(@NotNull String userId,@NotNull String name, @NotNull String description, @NotNull String dateEnd) throws ParseException;

    void merge(@NotNull String userId,@NotNull String projectId,@NotNull String name,@NotNull String description,@NotNull String dateEnd) throws ParseException;

    void removeAll(@NotNull String userId);

    @NotNull
    List<T> findAll(@NotNull String userId);

    @NotNull
    List<T> findAllSortByDateBegin(@NotNull String userId);

    @NotNull
    List<T> findAllSortByDateEnd(@NotNull String userId);

    @NotNull
    List<T> findAllSortByStatus(@NotNull String userId);

    @Nullable
    T findOneByName(@NotNull String userId, @NotNull String name);

    @Nullable
    T findOneByDescription(@NotNull String userId, @NotNull String description);

}
