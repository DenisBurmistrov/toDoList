package ru.burmistrov.tm.api;


import ru.burmistrov.tm.entity.Task;

import java.util.Map;

public interface ITaskRepository {

    String merge(String projectId, String oldName, String newName, String description, Integer priority);

    String persist(String projectId, String name, String description, Integer priority);

    void remove(String projectId, String name);

    Map <String, Task> findAll(String projectId);

    void removeAll(String projectId);

    Task findOne(String projectId, String name);
}
