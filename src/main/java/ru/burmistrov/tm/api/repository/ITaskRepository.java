package ru.burmistrov.tm.api.repository;

import ru.burmistrov.tm.entity.AbstractEntity;

import java.util.List;

public interface ITaskRepository {

    AbstractEntity persist(AbstractEntity abstractEntity);

    void merge(AbstractEntity abstractEntity);

    void remove(AbstractEntity entity);

    void removeAll(AbstractEntity entity);

    List<AbstractEntity> findAll(AbstractEntity entity);

    void removeAllInProject(AbstractEntity entity);

    AbstractEntity findOne(AbstractEntity entity);
}
