package ru.burmistrov.tm.command.task;

import org.jetbrains.annotations.NotNull;
import ru.burmistrov.tm.command.AbstractCommand;
import ru.burmistrov.tm.endpoint.CloneNotSupportedException_Exception;

import java.util.Objects;

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
    public void execute() throws CloneNotSupportedException_Exception {
        System.out.println("Введите ID задачи");
        @NotNull final String taskId = getServiceLocator().getTerminalCommandService().nextLine();
        getServiceLocator().getTaskEndpoint()
                .removeTaskById(getServiceLocator().getSession(), Objects.requireNonNull(getServiceLocator().getSession().getUserId()), taskId);
    }

    @Override
    public boolean isSecure() {
        return true;
    }
}
