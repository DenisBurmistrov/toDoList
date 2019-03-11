package ru.burmistrov.tm.api.repository;

import ru.burmistrov.tm.entity.AbstractEntity;

import java.util.List;

public interface IProjectRepository<T extends  AbstractEntity> {

    T persist(T abstractEntity);

    void merge(T abstractEntity);

    void remove(T abstractEntity);

    void removeAll(T abstractEntity);

    List<T> findAll(T abstractEntity);

    T findOne(T abstractEntity);


}
