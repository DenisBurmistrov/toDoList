package ru.burmistrov.tm.command.task;

import org.jetbrains.annotations.NotNull;
import ru.burmistrov.tm.api.loader.ServiceLocator;
import ru.burmistrov.tm.command.AbstractCommand;
import ru.burmistrov.tm.endpoint.Exception_Exception;
import ru.burmistrov.tm.endpoint.Session;
import ru.burmistrov.tm.endpoint.TaskEndpoint;
import ru.burmistrov.tm.service.TerminalCommandService;

import javax.inject.Inject;
import java.util.Objects;

public final class TaskClearCommand extends AbstractCommand {

    @Inject
    private ServiceLocator serviceLocator;

    @Inject
    private TaskEndpoint taskEndpoint;

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
        taskEndpoint.removeAllTasks(serviceLocator.getSession(),
                Objects.requireNonNull(serviceLocator.getSession()).getUserId());
    }

    @Override
    public boolean isSecure() {
        return true;
    }
}
