package ru.burmistrov.tm.api.repository;

import org.jetbrains.annotations.Nullable;
import ru.burmistrov.tm.entity.AbstractEntity;

import java.util.List;

public interface IProjectRepository<T extends  AbstractEntity> {

    @Nullable
    T persist(@Nullable T abstractEntity);

    void merge(@Nullable T abstractEntity);

    void remove(@Nullable T abstractEntity);

    void removeAll(@Nullable T abstractEntity);

    @Nullable List<T> findAll(@Nullable T abstractEntity);

    @Nullable T findOne(@Nullable T abstractEntity);


}
