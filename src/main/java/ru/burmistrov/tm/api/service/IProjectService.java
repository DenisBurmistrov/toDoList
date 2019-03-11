package ru.burmistrov.tm.api.service;

import ru.burmistrov.tm.entity.AbstractEntity;

import java.util.List;

public interface IProjectService<T extends AbstractEntity> {

     void remove(String userId, String projectId);

    T persist(String userId, String name, String merge);

    void merge(String userId, String projectId, String name, String description);

    void removeAll(String userId);

    List<T> findAll(String userId);

}
