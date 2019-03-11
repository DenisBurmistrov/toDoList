package ru.burmistrov.tm.api.repository;

import org.jetbrains.annotations.NotNull;
import ru.burmistrov.tm.entity.AbstractEntity;

import java.util.List;

public interface ITaskRepository<T extends  AbstractEntity> {

    @NotNull
    T persist(@NotNull T abstractEntity);

    void merge(@NotNull T abstractEntity);

    void remove(@NotNull T entity);

    void removeAll(@NotNull T entity);

    @NotNull
    List<T> findAll(@NotNull T entity);

    void removeAllInProject(@NotNull T entity);

    @NotNull
    T findOne(@NotNull T entity);
}
