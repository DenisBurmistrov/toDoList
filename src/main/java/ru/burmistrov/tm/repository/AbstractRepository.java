package ru.burmistrov.tm.repository;

import org.jetbrains.annotations.NotNull;
import ru.burmistrov.tm.entity.AbstractEntity;

import java.util.List;

public abstract class AbstractRepository<T extends AbstractEntity> {

    @NotNull
    public abstract T persist(@NotNull T abstractEntity);

    public abstract void merge(@NotNull T abstractEntity);

    public abstract void remove(@NotNull T abstractEntity);

    public abstract void removeAll(@NotNull T abstractEntity);

    @NotNull
    public abstract List<T> findAll(@NotNull T abstractEntity);

    @NotNull
    public abstract T findOne(@NotNull T abstractEntity);

}
