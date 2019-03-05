package ru.burmistrov.tm.service;

import ru.burmistrov.tm.entity.Task;
import ru.burmistrov.tm.repository.TaskRepository;

import java.util.Map;

public class TaskService {

    private TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public String merge(String projectId, String oldName , String newName, String description, String priority) {
        if (taskRepository == null) {
            taskRepository = new TaskRepository();
        }
        try {
            Integer priorityInt = Integer.parseInt(priority);
            return taskRepository.merge(projectId, oldName, newName, description, priorityInt);
        }
        catch (NumberFormatException e) {
            return  "Некорректно введенный приоритет";
        }

    }

    public String persist(String projectId, String name, String description, String priority) {
        if (taskRepository == null) {
            taskRepository = new TaskRepository();
        }
        try {
            Integer priorityInt = Integer.parseInt(priority);
            return taskRepository.persist(projectId, name, description, priorityInt);
        } catch (NumberFormatException e) {
            return "Некорректно введенный приоритет";
        }
    }

    public Map<String, Task> findAll(String projectId) {
        if (taskRepository == null) {
            taskRepository = new TaskRepository();
        }
            return taskRepository.findAll(projectId);
    }

    public void removeAll(String projectId) {
        if (taskRepository == null) {
            taskRepository = new TaskRepository();
        }
            taskRepository.removeAll(projectId);
    }

    public void remove(String projectId, String taskId) {
        if (taskRepository == null) {
            taskRepository = new TaskRepository();
        }
            taskRepository.remove(projectId, taskId);

    }

}
