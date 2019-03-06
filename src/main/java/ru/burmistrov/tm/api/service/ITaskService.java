package ru.burmistrov.tm.api.service;

import ru.burmistrov.tm.entity.Task;
import ru.burmistrov.tm.entity.User;

import java.util.List;

public interface ITaskService {

    void merge(User currentUser, String projectId, String oldName, String newName, String description);

    Task persist(User currentUser, String projectId, String name, String description);

    List<Task> findAll(User currentUser, String projectId);

    void removeAllInProject(User currentUser, String projectId);

    void remove(User currentUser, String projectId, String taskId);
}
