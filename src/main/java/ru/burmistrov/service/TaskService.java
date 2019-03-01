package ru.burmistrov.service;

import ru.burmistrov.repository.TaskRepository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TaskService {

    private TaskRepository taskRepository;

    public void addTaskToProject() {
        if (taskRepository == null) {
            taskRepository = new TaskRepository();
        }
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Введите ID проекта:");
        try {
            Long id = Long.valueOf(bufferedReader.readLine());
            System.out.println("Введите имя: ");
            String name = bufferedReader.readLine();
            System.out.println("Введите описание: ");
            String description = bufferedReader.readLine();
            System.out.println("Введите приоритет от 0 до 5: ");
            Integer priority = Integer.parseInt(bufferedReader.readLine());
            taskRepository.addTaskToProject(id, name, description, priority);
        } catch (IOException e) {
            System.out.println("Некорректные введенные данные");
        }


    }

    public void printTasksOfProject() {
        if (taskRepository == null) {
            taskRepository = new TaskRepository();
        }

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Введите ID проекта");
        try {
            Long id = Long.valueOf(bufferedReader.readLine());
            taskRepository.printTasksOfProject(id);
        } catch (IOException e) {
            System.out.println("Некорректное значение ID");
        }


    }

    public void clearAllTasks() {
        if (taskRepository == null) {
            taskRepository = new TaskRepository();
        }
        taskRepository.clearAllTasks();

    }

    public void deleteTaskFromProject() {
        if (taskRepository == null) {
            taskRepository = new TaskRepository();
        }
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        try {
            System.out.println("Введите ID проекта");
            Long projectId = Long.valueOf(reader.readLine());
            System.out.println("Введите ID задачи");
            Long id = Long.valueOf(reader.readLine());
            taskRepository.deleteTaskFromProject(projectId, id);

        } catch (IOException e) {
            System.out.println("Некорректные данные");
        }


    }
}
