package ru.burmistrov.tm.service;

import ru.burmistrov.tm.entity.Task;
import ru.burmistrov.tm.repository.TaskRepository;

import java.util.Map;

public class TaskService {

    private TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public String persist(String projectId, String oldName , String newName, String description, String priority) {
        if (taskRepository == null) {
            taskRepository = new TaskRepository();
        }
        try {
            Long id = Long.valueOf(projectId);
            Integer priorityInt = Integer.parseInt(priority);
            return taskRepository.persist(id, oldName, newName, description, priorityInt);
        }
        catch (NumberFormatException e) {
            return  "Некорректные введенные данные";
        }

    }

    public String merge(String projectId, String name, String description, String priority) {
        if (taskRepository == null) {
            taskRepository = new TaskRepository();
        }
        try {
            Long projectIdLong = Long.valueOf(projectId);
            Integer priorityInt = Integer.parseInt(priority);
            return taskRepository.merge(projectIdLong, name, description, priorityInt);
        } catch (NumberFormatException e) {
            return "Некорректные введенные данные";
        }
    }

    public String findAll(String projectId) {
        if (taskRepository == null) {
            taskRepository = new TaskRepository();
        }
        try {
            Long id = Long.valueOf(projectId);
            return taskRepository.findAll(id);
        }
        catch (NumberFormatException e) {
            System.out.println("Некорректные введенные данные");
            return null;
        }


    }

    public String removeAll(String projectId) {
        if (taskRepository == null) {
            taskRepository = new TaskRepository();
        }
        try {
            Long id = Long.valueOf(projectId);
            return taskRepository.removeAll(id);
        }
        catch (NumberFormatException e) {
            return "Введен некорректный ID";
        }

    }

    public String remove(String projectId, String name) {
        if (taskRepository == null) {
            taskRepository = new TaskRepository();
        }

        try {
            System.out.println("Введите ID проекта");
            Long projectIdLong = Long.valueOf(projectId);
            System.out.println("Введите название задачи");
            return taskRepository.remove(projectIdLong, name);

        }
        catch (NumberFormatException e) {
            return "Некорректные введенные данные";
        }


    }

}
