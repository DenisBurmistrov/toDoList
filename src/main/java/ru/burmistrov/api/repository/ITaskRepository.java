package ru.burmistrov.api.repository;

public interface ITaskRepository {

    void addTaskToProject(Long projectId, String name, String description, Integer priority);

    void deleteTaskFromProject(Long projectId, Long taskId);

    void printTasksOfProject(Long projectId);

    void clearAllTasks();

    // void updateTaskFromProject();
}
