package ru.burmistrov.tm.api;

import ru.burmistrov.tm.entity.Task;

import java.util.Map;

public interface ITaskRepository {

    String addTaskToProject(Long projectId, String name, String description, Integer priority);

    String deleteTaskFromProject(Long projectId, Long taskId);

    Map<Long, Task> printTasksOfProject(Long projectId);

    String clearAllTasks(Long taskId);

}
