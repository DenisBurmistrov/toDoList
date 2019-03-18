package ru.burmistrov.tm.command.task;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.burmistrov.tm.api.service.ITaskService;
import ru.burmistrov.tm.command.AbstractCommand;

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
        System.out.println("Введите ID задачи");
        @NotNull final String taskId = getServiceLocator().getTerminalCommandService().nextLine();
        getServiceLocator().getTaskEndpoint().removeTaskById(, taskId);
    }

    @Override
    public boolean isSecure() {
        return true;
    }
}
