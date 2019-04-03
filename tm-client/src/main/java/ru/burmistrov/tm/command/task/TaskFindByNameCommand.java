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

public class TaskFindByNameCommand extends AbstractCommand {

    @Inject
    private ServiceLocator serviceLocator;

    @Inject
    private TaskEndpoint taskEndpoint;

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
    public void execute() throws Exception_Exception {
        System.out.println("Введите имя задачи:");
        @NotNull final String name = serviceLocator.getTerminalCommandService().nextLine();
        System.out.println(taskEndpoint.findTaskByName(serviceLocator.getSession(),
                Objects.requireNonNull(serviceLocator.getSession()).getUserId(), name));
    }

    @Override
    public boolean isSecure() {
        return true;
    }
}
