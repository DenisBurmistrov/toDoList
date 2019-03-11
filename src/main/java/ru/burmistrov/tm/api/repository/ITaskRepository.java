package ru.burmistrov.tm.api.repository;

import ru.burmistrov.tm.entity.AbstractEntity;

import java.util.List;

public interface ITaskRepository<T extends  AbstractEntity> {

    T persist(T abstractEntity);

    void merge(T abstractEntity);

    void remove(T entity);

    void removeAll(T entity);

    List<T> findAll(T entity);

    void removeAllInProject(T entity);

    T findOne(T entity);
}
