package ru.burmistrov.tm.api.repository;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.burmistrov.tm.entity.AbstractEntity;

import java.util.List;

public interface IProjectRepository<T extends  AbstractEntity> {

    @Nullable
    T persist(@NotNull T abstractEntity);

    void merge(@NotNull T abstractEntity);

    void remove(@NotNull T abstractEntity);

    void removeAll(@NotNull T abstractEntity);

    @Nullable List<T> findAll(@NotNull T abstractEntity);

    @Nullable T findOne(@NotNull T abstractEntity);

    @NotNull List<T> findAllSortByDateBegin(@NotNull T abstractEntity);

    @NotNull List<T> findAllSortByDateEnd(@NotNull T abstractEntity);

    @NotNull List<T> findAllSortByStatus(@NotNull T abstractEntity);

    @Nullable T findOneByName(@NotNull T abstractEntity);

    @Nullable T findOneByDescription(@NotNull T abstractEntity);
}
