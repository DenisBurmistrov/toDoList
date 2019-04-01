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

public class TaskFindByDescriptionCommand extends AbstractCommand {

    @Inject
    private ServiceLocator serviceLocator;

    @NotNull
    @Override
    public String getName() {
        return "-printTaskByDescription";
    }

    @NotNull
    @Override
    public String getDescription() {
        return "Print task by name";
    }

    @Override
    public void execute() throws Exception_Exception {
        System.out.println("Введите описание задачи:");
        @NotNull final String description = serviceLocator.getTerminalCommandService().nextLine();
        System.out.println(serviceLocator.getTaskEndpoint().findTaskByDescription(serviceLocator.getSession(),
                Objects.requireNonNull(serviceLocator.getSession()).getUserId(), description));
    }

    @Override
    public boolean isSecure() {
        return true;
    }
}
