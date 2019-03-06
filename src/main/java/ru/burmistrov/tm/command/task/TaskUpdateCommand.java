package ru.burmistrov.tm.command.task;

import ru.burmistrov.tm.Bootstrap;
import ru.burmistrov.tm.command.AbstractCommand;
import ru.burmistrov.tm.service.TaskService;

import java.util.Scanner;

public class TaskUpdateCommand extends AbstractCommand {

    private final TaskService taskService = getBootstrap().getTaskService();

    private final Scanner scanner = getBootstrap().getScanner();

    public TaskUpdateCommand(Bootstrap bootstrap) {
        super(bootstrap);
    }

    @Override
    public String getName() {
        return "-updateTask";
    }

    @Override
    public String getDescription() {
        return "Update task by project ID by task ID";
    }

    @Override
    public void execute() {
        System.out.println("Введите ID проекта:");
        String projectId = scanner.nextLine();
        System.out.println("Введите имя задачи:");
        String oldName = scanner.nextLine();
        System.out.println("Введите новое имя:");
        String newName = scanner.nextLine();
        System.out.println("Введите новое описание: ");
        String description = scanner.nextLine();
        System.out.println("Введите новый приоритет(от 0 до 5): ");
        String priority = scanner.nextLine();
        System.out.println(taskService.merge(projectId, oldName, newName, description, priority));
    }

    @Override
    public boolean isSecure() {
        return true;
    }
}
