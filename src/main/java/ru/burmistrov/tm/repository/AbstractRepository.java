package ru.burmistrov.tm.repository;

import ru.burmistrov.tm.entity.AbstractEntity;

import java.util.List;

public abstract class AbstractRepository<T extends AbstractEntity> {

    public abstract T persist(T abstractEntity);

    public abstract void merge(T abstractEntity);

    public abstract void remove(T abstractEntity);

    public abstract void removeAll(T abstractEntity);

    public abstract List<T> findAll(T abstractEntity);

    public abstract T findOne(T abstractEntity);

}
