package ru.burmistrov.tm.api.service;

import ru.burmistrov.tm.entity.Task;

import java.util.List;

public interface ITaskService {

    void merge(String userId, String projectId, String oldName, String newName, String description);

    Task persist(String userId, String projectId, String name, String description);

    List<Task> findAll(String userId, String projectId);

    void removeAllInProject(String userId, String projectId);

    void remove(String userId, String projectId, String taskId);

    void removeAll(String userId);
}
