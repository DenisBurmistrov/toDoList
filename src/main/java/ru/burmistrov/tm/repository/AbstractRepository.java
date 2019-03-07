package ru.burmistrov.tm.repository;

import ru.burmistrov.tm.entity.AbstractEntity;

import java.util.List;

public abstract class AbstractRepository {

    public abstract AbstractEntity persist(AbstractEntity abstractEntity);

    public abstract void merge(AbstractEntity abstractEntity);

    public abstract void remove(AbstractEntity abstractEntity);

    public abstract void removeAll(AbstractEntity abstractEntity);

    public abstract List<AbstractEntity> findAll(AbstractEntity abstractEntity);

    public abstract AbstractEntity findOne(AbstractEntity abstractEntity);

}
