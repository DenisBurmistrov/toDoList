package ru.burmistrov.tm.command.task;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.burmistrov.tm.api.loader.ServiceLocator;
import ru.burmistrov.tm.api.service.ITaskService;
import ru.burmistrov.tm.command.AbstractCommand;

import java.util.Scanner;

public final class TaskRemoveCommand extends AbstractCommand {


    public TaskRemoveCommand() {

    }

    @NotNull
    @Override
    public String getName() {
        return "-removeTask";
    }

    @NotNull
    @Override
    public String getDescription() {
        return "Remove task by task ID by Project ID";
    }

    @Override
    public void execute() {
        @Nullable final ITaskService taskService = getServiceLocator().getTaskService();
        System.out.println("Введите ID проекта");
        @NotNull final String projectId = getServiceLocator().getTerminalCommandService().nextLine();
        System.out.println("Введите ID задачи");
        @NotNull final String taskId = getServiceLocator().getTerminalCommandService().nextLine();
        taskService.remove(getServiceLocator().getCurrentUser().getId(), projectId, taskId);
    }

    @Override
    public boolean isSecure() {
        return true;
    }
}
