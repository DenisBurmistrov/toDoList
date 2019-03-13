package ru.burmistrov.tm.command.task;

import org.jetbrains.annotations.Nullable;
import ru.burmistrov.tm.api.loader.ServiceLocator;
import ru.burmistrov.tm.api.service.ITaskService;
import ru.burmistrov.tm.command.AbstractCommand;

import java.util.Scanner;

public final class TaskRemoveCommand extends AbstractCommand {


    public TaskRemoveCommand() {

    }

    @Override
    public String getName() {
        return "-removeTask";
    }

    @Override
    public String getDescription() {
        return "Remove task by task ID by Project ID";
    }

    @Override
    public void execute() {
        @Nullable final ITaskService taskService = getServiceLocator().getTaskService();
        @Nullable final Scanner scanner = getServiceLocator().getScanner();
        System.out.println("Введите ID проекта");
        @Nullable final String projectId = scanner.nextLine();
        System.out.println("Введите ID задачи");
        @Nullable final String taskId = scanner.next();
        taskService.remove(getServiceLocator().getCurrentUser().getId(), projectId, taskId);
    }

    @Override
    public boolean isSecure() {
        return true;
    }
}
