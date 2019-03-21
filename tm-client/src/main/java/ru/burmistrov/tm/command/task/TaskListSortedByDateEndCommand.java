package ru.burmistrov.tm.command.task;

import org.jetbrains.annotations.NotNull;
import ru.burmistrov.tm.command.AbstractCommand;
import ru.burmistrov.tm.endpoint.Exception_Exception;
import ru.burmistrov.tm.endpoint.Task;

import java.util.List;
import java.util.Objects;

public class TaskListSortedByDateEndCommand extends AbstractCommand {

    @NotNull
    @Override
    public String getName() {
        return "-printTasksSortedByDateEnd";
    }

    @NotNull
    @Override
    public String getDescription() {
        return "Print tasks sorted by date end";
    }

    @Override
    public void execute() throws Exception_Exception {
        @NotNull final List<Task> taskList = getServiceLocator().getTaskEndpoint()
                .findAllTasksSortByDateEnd(getServiceLocator().getSession(), Objects.requireNonNull(getServiceLocator().getSession().getUserId()));
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
