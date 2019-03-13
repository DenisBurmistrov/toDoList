package ru.burmistrov.tm.command.task;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.burmistrov.tm.api.service.ITaskService;
import ru.burmistrov.tm.command.AbstractCommand;

import java.text.ParseException;
import java.util.Scanner;

public final class TaskCreateCommand extends AbstractCommand {

    public TaskCreateCommand() {
    }

    @NotNull
    @Override
    public String getName() {
        return "-createTask";
    }

    @NotNull
    @Override
    public String getDescription() {
        return "Create task to project by project ID";
    }

    @Override
    public void execute() throws ParseException {
        @Nullable final ITaskService taskService = getServiceLocator().getTaskService();
        System.out.println("Введите ID проекта:");
        @NotNull final String id = getServiceLocator().getTerminalCommandService().nextLine();
        System.out.println("Введите имя задачи:");
        @NotNull final String oldName = getServiceLocator().getTerminalCommandService().nextLine();
        System.out.println("Введите описание для задачи: ");
        @NotNull final String description = getServiceLocator().getTerminalCommandService().nextLine();
        System.out.println("Введите дату окончание (Пример: 27.10.2019):");
        @NotNull final String date = getServiceLocator().getTerminalCommandService().nextLine();
        taskService.persist(getServiceLocator().getCurrentUser().getId(), id, oldName, description, date);
    }

    @Override
    public boolean isSecure() {
        return true;
    }
}
