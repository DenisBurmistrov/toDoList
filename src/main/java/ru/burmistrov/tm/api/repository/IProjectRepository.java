package ru.burmistrov.tm.api.repository;

import ru.burmistrov.tm.entity.AbstractEntity;

import java.util.List;

public interface IProjectRepository {

    AbstractEntity persist(AbstractEntity abstractEntity);

    void merge(AbstractEntity abstractEntity);

    void remove(AbstractEntity abstractEntity);

    void removeAll(AbstractEntity abstractEntity);

    List<AbstractEntity> findAll(AbstractEntity abstractEntity);

    AbstractEntity findOne(AbstractEntity abstractEntity);


}
