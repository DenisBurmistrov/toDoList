package ru.burmistrov.tm.api.repository;

import ru.burmistrov.tm.entity.Task;
import ru.burmistrov.tm.entity.User;

import java.util.List;

public interface ITaskRepository {

    Task persist(String userId, String projectId, String name, String description);

    void merge(String userId, String projectId, String oldName, String newName, String description);

    void remove(String userId, String projectId, String name);

    void removeAll(String userId);

    List<Task> findAll(String userId, String projectId);

    void removeAllInProject(String userId, String projectId);

    Task findOne(String userId, String projectId, String name);
}
