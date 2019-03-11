package ru.burmistrov.tm.api.repository;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.burmistrov.tm.entity.AbstractEntity;

import java.util.List;

public interface ITaskRepository<T extends  AbstractEntity> {

    @Nullable
    T persist(@Nullable T abstractEntity);

    void merge(@Nullable T abstractEntity);

    void remove(@Nullable T entity);

    void removeAll(@Nullable T entity);

    @Nullable
    List<T> findAll(@Nullable T entity);

    void removeAllInProject(@Nullable T entity);

    @Nullable
    T findOne(@Nullable T entity);
}
