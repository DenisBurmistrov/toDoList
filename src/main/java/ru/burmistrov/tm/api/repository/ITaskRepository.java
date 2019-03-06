package ru.burmistrov.tm.api.repository;

import ru.burmistrov.tm.entity.Task;
import ru.burmistrov.tm.entity.User;

import java.util.List;

public interface ITaskRepository {

    void merge(User currentUser, String projectId, String oldName, String newName, String description);

    Task persist(User currentUser, String projectId, String name, String description);

    void remove(User currentUser, String projectId, String name);

    List<Task> findAll(User currentUser, String projectId);

    void removeAllinProject(User currentUser, String projectId);

    void removeAll(User currentUser);

    Task findOne(User currentUser, String projectId, String name);
}
