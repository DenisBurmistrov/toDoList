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

public final class TaskRemoveCommand extends AbstractCommand {

    @Inject
    private ServiceLocator serviceLocator;

    @Inject
    private TaskEndpoint taskEndpoint;

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
    public void execute() throws Exception_Exception {
        System.out.println("Введите ID задачи");
        @NotNull final String taskId = serviceLocator.getTerminalCommandService().nextLine();
        taskEndpoint.removeTaskById(serviceLocator.getSession(),
                Objects.requireNonNull(serviceLocator.getSession()).getUserId(), taskId);
    }

    @Override
    public boolean isSecure() {
        return true;
    }
}
