package ru.burmistrov.tm.command.task;

import org.jetbrains.annotations.NotNull;
import ru.burmistrov.tm.command.AbstractCommand;
import ru.burmistrov.tm.endpoint.Exception_Exception;

import java.util.Objects;

public final class TaskClearCommand extends AbstractCommand {

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
    public void execute() throws Exception_Exception {
        getServiceLocator().getTaskEndpoint().removeAllTasks(getServiceLocator().getSession(),
                Objects.requireNonNull(Objects.requireNonNull(getServiceLocator().getSession()).getUserId()));
    }

    @Override
    public boolean isSecure() {
        return true;
    }
}
