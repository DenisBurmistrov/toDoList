package ru.burmistrov.tm.command.task;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.burmistrov.tm.Bootstrap;
import ru.burmistrov.tm.api.loader.ServiceLocator;
import ru.burmistrov.tm.api.service.ITaskService;
import ru.burmistrov.tm.command.AbstractCommand;

import java.text.ParseException;
import java.util.Scanner;

public final class TaskUpdateCommand extends AbstractCommand {


    public TaskUpdateCommand() {
    }

    @NotNull
    @Override
    public String getName() {
        return "-updateTask";
    }

    @NotNull
    @Override
    public String getDescription() {
        return "Update task by project ID by task ID";
    }

    @Override
    public void execute() throws ParseException {
        @Nullable final ITaskService taskService = getServiceLocator().getTaskService();
        @Nullable final Scanner scanner = getServiceLocator().getScanner();
        System.out.println("Введите ID проекта:");
        @NotNull final String projectId = scanner.nextLine();
        System.out.println("Введите ID задачи:");
        @NotNull final String taskId = scanner.nextLine();
        System.out.println("Введите новое имя:");
        @NotNull final String newName = scanner.nextLine();
        System.out.println("Введите новое описание: ");
        @NotNull final String description = scanner.nextLine();
        System.out.println("Введите дату окончание (Пример: 27.10.2019):");
        @NotNull final String date = scanner.nextLine();
        taskService.merge(getServiceLocator().getCurrentUser().getId(), projectId, taskId, newName, description, date);
    }

    @Override
    public boolean isSecure() {
        return true;
    }
}
