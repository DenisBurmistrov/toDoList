package ru.burmistrov.tm.api.service;

import ru.burmistrov.tm.entity.AbstractEntity;

import java.util.List;

public interface ITaskService {

    void merge(String userId, String projectId, String oldName, String newName, String description);

    AbstractEntity persist(String userId, String projectId, String name, String description);

    List<AbstractEntity> findAll(String userId, String projectId);

    void removeAllInProject(String userId, String projectId);

    void remove(String userId, String projectId, String taskId);

    void removeAll(String userId);
}
