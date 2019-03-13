package ru.burmistrov.tm.command.task;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.burmistrov.tm.api.service.ITaskService;
import ru.burmistrov.tm.command.AbstractCommand;

import java.util.Scanner;

public class TaskFindByNameCommand extends AbstractCommand {

    @NotNull
    @Override
    public String getName() {
        return "-printTaskByName";
    }

    @NotNull
    @Override
    public String getDescription() {
        return "Print task by name";
    }

    @Override
    public void execute() {
        @Nullable final ITaskService taskService = getServiceLocator().getTaskService();
        System.out.println("Введите имя задачи:");
        @NotNull final String name = getServiceLocator().getTerminalCommandService().nextLine();
        System.out.println(taskService.findOneByName(getServiceLocator().getCurrentUser().getId(), name));
    }

    @Override
    public boolean isSecure() {
        return true;
    }
}
