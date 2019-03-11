package ru.burmistrov.tm.api.repository;

import org.jetbrains.annotations.NotNull;
import ru.burmistrov.tm.entity.AbstractEntity;

import java.util.List;

public interface IProjectRepository<T extends  AbstractEntity> {

    @NotNull
    T persist(@NotNull T abstractEntity);

    void merge(@NotNull T abstractEntity);

    void remove(@NotNull T abstractEntity);

    void removeAll(@NotNull T abstractEntity);

    @NotNull List<T> findAll(@NotNull T abstractEntity);

    @NotNull T findOne(@NotNull T abstractEntity);


}
