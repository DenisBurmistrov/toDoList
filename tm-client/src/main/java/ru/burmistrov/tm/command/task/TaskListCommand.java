package ru.burmistrov.tm.command.task;

import org.jetbrains.annotations.NotNull;
import ru.burmistrov.tm.command.AbstractCommand;
import ru.burmistrov.tm.endpoint.Exception_Exception;
import ru.burmistrov.tm.endpoint.Task;

import java.util.List;
import java.util.Objects;

public final class TaskListCommand extends AbstractCommand {

    @NotNull
    @Override
    public String getName() {
        return "-printTasks";
    }

    @NotNull
    @Override
    public String getDescription() {
        return "Print tasks of project by project ID";
    }

    @Override
    public void execute() throws Exception_Exception {
        System.out.println("Введите ID проекта:");
        @NotNull final String id = getServiceLocator().getTerminalCommandService().nextLine();
        @NotNull final List<Task> taskList = getServiceLocator().getTaskEndpoint()
                .findAllTasksInProject(getServiceLocator().getSession(),
                        Objects.requireNonNull(Objects.requireNonNull(getServiceLocator().getSession()).getUserId()), id);
        for (Task task : taskList) {
            System.out.println("ID: " + task.getId() +
                    "; Название: " + task.getName() +
                    "; Описание: " + task.getDescription() +
                    "; ID проекта: " + task.getProjectId() +
                    "; Статус" );
        }
    }

    @Override
    public boolean isSecure() {
        return true;
    }
}
