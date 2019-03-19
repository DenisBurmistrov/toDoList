package ru.burmistrov.tm.command.task;

import org.jetbrains.annotations.NotNull;
import ru.burmistrov.tm.command.AbstractCommand;

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
    public void execute() {
        getServiceLocator().getTaskEndpoint().removeAllTasks(Objects.requireNonNull(getServiceLocator().getSession().getUserId()));
    }

    @Override
    public boolean isSecure() {
        return true;
    }
}
