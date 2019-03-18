package ru.burmistrov.tm.command.task;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.burmistrov.tm.api.service.ITaskService;
import ru.burmistrov.tm.command.AbstractCommand;
import ru.burmistrov.tm.entity.AbstractEntity;

import java.util.List;

public final class TaskListCommand extends AbstractCommand {

    public TaskListCommand() {
    }

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
    public void execute() {
        System.out.println("Введите ID проекта:");
        @NotNull final String id = getServiceLocator().getTerminalCommandService().nextLine();
        @NotNull final List<AbstractEntity> taskList = getServiceLocator().getTaskEndpoint().findAllTasksInProject( , id);
        for (AbstractEntity task : taskList) {
            System.out.println(task);
        }
    }

    @Override
    public boolean isSecure() {
        return true;
    }
}
