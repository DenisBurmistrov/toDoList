package ru.burmistrov.tm.command.task;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.burmistrov.tm.api.service.ITaskService;
import ru.burmistrov.tm.command.AbstractCommand;
import ru.burmistrov.tm.entity.AbstractEntity;

import java.util.List;

public class TaskListSortedByDateBeginCommand extends AbstractCommand {

    @NotNull
    @Override
    public String getName() {
        return "-printTasksSortedByDateBegin";
    }

    @NotNull
    @Override
    public String getDescription() {
        return "Print tasks sorted by date end";
    }

    @Override
    public void execute() {
        @NotNull final List<AbstractEntity> taskList = getServiceLocator().getTaskEndpoint().findAllTasksSortByDateBegin();
        for (AbstractEntity task : taskList) {
            System.out.println(task);
        }
    }

    @Override
    public boolean isSecure() {
        return false;
    }
}
