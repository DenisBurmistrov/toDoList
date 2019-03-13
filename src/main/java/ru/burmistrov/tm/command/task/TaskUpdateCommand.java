package ru.burmistrov.tm.command.task;

import org.jetbrains.annotations.Nullable;
import ru.burmistrov.tm.Bootstrap;
import ru.burmistrov.tm.api.loader.ServiceLocator;
import ru.burmistrov.tm.api.service.ITaskService;
import ru.burmistrov.tm.command.AbstractCommand;

import java.util.Scanner;

public final class TaskUpdateCommand extends AbstractCommand {



    public TaskUpdateCommand() {
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
        if (getServiceLocator() != null) {
            @Nullable final ITaskService taskService = getServiceLocator().getTaskService();
            @Nullable final Scanner scanner = getServiceLocator().getScanner();
            System.out.println("Введите ID проекта:");
            @Nullable final String projectId = scanner.nextLine();
            System.out.println("Введите ID задачи:");
            @Nullable final String taskId = scanner.nextLine();
            System.out.println("Введите новое имя:");
            @Nullable final String newName = scanner.nextLine();
            System.out.println("Введите новое описание: ");
            @Nullable final String description = scanner.nextLine();
            System.out.println("Введите дату окончание (Пример: 27.10.2019):");
            String date = scanner.nextLine();
            if (taskService != null) {
                taskService.merge(getServiceLocator().getCurrentUser().getId(), projectId, taskId, newName, description, date);
            }
        }
    }

    @Override
    public boolean isSecure() {
        return true;
    }
}
