package ru.burmistrov.tm.api.repository;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.burmistrov.tm.entity.AbstractEntity;

import java.util.List;

public interface ITaskRepository<T extends  AbstractEntity> {

    @Nullable
    T persist(@NotNull T abstractEntity);

    void merge(@NotNull T abstractEntity);

    void remove(@NotNull T entity);

    void removeAll(@NotNull T entity);

    @Nullable
    List<T> findAll(@NotNull T entity);

    void removeAllInProject(@NotNull T entity);

    @Nullable
    T findOne(@NotNull T entity);

    @NotNull List<T> findAllSortByDateBegin(@NotNull T abstractEntity);

    @NotNull List<T> findAllSortByDateEnd(@NotNull T abstractEntity);

    @NotNull List<T> findAllSortByStatus(@NotNull T abstractEntity);
}
