package ru.burmistrov.tm.command.task;

import org.jetbrains.annotations.NotNull;
import ru.burmistrov.tm.command.AbstractCommand;
import ru.burmistrov.tm.endpoint.CloneNotSupportedException_Exception;

import java.util.Objects;

public final class TaskClearCommand extends AbstractCommand {

    public TaskClearCommand() {
    }

    @NotNull
    @Override
    public String getName() {
        return "-clearTasks";
    }

    @NotNull
    @Override
    public String getDescription() {
        return "Clear project tasks by ID";
    }

    @Override
    public void execute() throws CloneNotSupportedException_Exception {
        getServiceLocator().getTaskEndpoint().removeAllTasks(getServiceLocator().getSession(), Objects.requireNonNull(getServiceLocator().getSession().getUserId()));
    }

    @Override
    public boolean isSecure() {
        return true;
    }
}
