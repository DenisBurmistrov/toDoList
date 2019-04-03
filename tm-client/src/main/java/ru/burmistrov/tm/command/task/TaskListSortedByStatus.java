package ru.burmistrov.tm.command.task;

import org.jetbrains.annotations.NotNull;
import ru.burmistrov.tm.api.loader.ServiceLocator;
import ru.burmistrov.tm.command.AbstractCommand;
import ru.burmistrov.tm.endpoint.Exception_Exception;
import ru.burmistrov.tm.endpoint.Session;
import ru.burmistrov.tm.endpoint.TaskDto;
import ru.burmistrov.tm.endpoint.TaskEndpoint;
import ru.burmistrov.tm.service.TerminalCommandService;

import javax.inject.Inject;
import java.util.List;
import java.util.Objects;

public class TaskListSortedByStatus extends AbstractCommand {

    @Inject
    private ServiceLocator serviceLocator;

    @Inject
    private TaskEndpoint taskEndpoint;

    @NotNull
    @Override
    public String getName() {
        return "-printTasksSortedByStatus";
    }

    @NotNull
    @Override
    public String getDescription() {
        return "Print tasks sorted by status";
    }

    @Override
    public void execute() throws Exception_Exception {
        @NotNull final List<TaskDto> taskList = taskEndpoint
                .findAllTasksSortByStatus(serviceLocator.getSession(), Objects.requireNonNull(serviceLocator.getSession()).getUserId());
        System.out.println("Нет задач");
        for (TaskDto task : taskList) {
            System.out.println("ID: " + task.getId() +
                    "; Название: " + task.getName() +
                    "; Описание: " + task.getDescription() +
                    "; ID проекта: " + task.getProjectId() +
                    "; Статус: " + task.getStatus());
        }
    }

    @Override
    public boolean isSecure() {
        return false;
    }
}
