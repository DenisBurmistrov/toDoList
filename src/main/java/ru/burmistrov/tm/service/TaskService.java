package ru.burmistrov.tm.service;

import ru.burmistrov.tm.entity.Task;
import ru.burmistrov.tm.repository.TaskRepository;

import java.util.Map;

public class TaskService {

    private TaskRepository taskRepository;

    public String addTaskToProject(String projectId, String name, String description, String priority) {
        if (taskRepository == null) {
            taskRepository = new TaskRepository();
        }
        try {
            Long id = Long.valueOf(projectId);
            Integer priorityInt = Integer.parseInt(priority);
            return taskRepository.addTaskToProject(id, name, description, priorityInt);
        }
        catch (NumberFormatException e) {
            return  "Некорректные введенные данные";
        }


    }

    public Map<Long, Task> printTasksOfProject(String projectId) {
        if (taskRepository == null) {
            taskRepository = new TaskRepository();
        }
        try {
            Long id = Long.valueOf(projectId);
            return taskRepository.printTasksOfProject(id);
        }
        catch (NumberFormatException e) {
            System.out.println("Некорректные введенные данные");
            return null;
        }


    }

    public String clearAllTasks(String projectId) {
        if (taskRepository == null) {
            taskRepository = new TaskRepository();
        }
        try {
            Long id = Long.valueOf(projectId);
            return taskRepository.clearAllTasks(id);
        }
        catch (NumberFormatException e) {
            return "Введен некорректный ID";
        }

    }

    public String deleteTaskFromProject(String projectId, String taskId) {
        if (taskRepository == null) {
            taskRepository = new TaskRepository();
        }

        try {
            System.out.println("Введите ID проекта");
            Long projectIdLong = Long.valueOf(projectId);
            System.out.println("Введите ID задачи");
            Long taskIdLong = Long.valueOf(taskId);
            return taskRepository.deleteTaskFromProject(projectIdLong, taskIdLong);

        }
        catch (NumberFormatException e) {
            return "Некорректные введенные данные";
        }


    }

}
