package ru.burmistrov.tm.command.task;

import org.jetbrains.annotations.NotNull;
import ru.burmistrov.tm.command.AbstractCommand;
import ru.burmistrov.tm.endpoint.CloneNotSupportedException_Exception;
import ru.burmistrov.tm.endpoint.Task;

import java.util.List;
import java.util.Objects;

public class TaskListSortedByStatus extends AbstractCommand {

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
    public void execute() throws CloneNotSupportedException_Exception {
        @NotNull final List<Task> taskList = getServiceLocator().getTaskEndpoint()
                .findAllTasksSortByStatus(getServiceLocator().getSession(), Objects.requireNonNull(getServiceLocator().getSession().getUserId()));
        System.out.println("Нет задач");
        for (Task task : taskList) {
            System.out.println("ID: " + task.getId() +
                    "; Название: " + task.getName() +
                    "; Описание: " + task.getDescription() +
                    "; ID проекта: " + task.getProjectId());
        }
    }

    @Override
    public boolean isSecure() {
        return false;
    }
}
