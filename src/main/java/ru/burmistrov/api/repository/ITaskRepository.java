package ru.burmistrov.api.repository;

import ru.burmistrov.entity.Task;

import java.util.Map;

public interface ITaskRepository {

    String addTaskToProject(Long projectId, String name, String description, Integer priority);

    String deleteTaskFromProject(Long projectId, Long taskId);

    Map<Long, Task> printTasksOfProject(Long projectId);

    String clearAllTasks(Long taskId);

    // void updateTaskFromProject();
}
