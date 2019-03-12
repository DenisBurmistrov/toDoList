package ru.burmistrov.tm.command.task;

import org.jetbrains.annotations.Nullable;
import ru.burmistrov.tm.api.service.ITaskService;
import ru.burmistrov.tm.command.AbstractCommand;
import ru.burmistrov.tm.entity.AbstractEntity;

import java.util.List;
import java.util.Scanner;

public class TaskListSortedByDateBeginCommand extends AbstractCommand {
    @Nullable
    @Override
    public String getName() {
        return "-printTasksSortedByDateBegin";
    }

    @Nullable
    @Override
    public String getDescription() {
        return "Print tasks sorted by date begin";
    }

    @Override
    public void execute() {
        if (getServiceLocator() != null) {
            @Nullable final ITaskService<AbstractEntity> taskService = getServiceLocator().getTaskService();
            if(taskService != null) {
                @Nullable final List<AbstractEntity> taskList = taskService.findAllSortByDateBegin(getServiceLocator().getCurrentUser().getId());
                if (taskList == null) {
                    System.out.println("Нет задач");
                } else {
                    for (AbstractEntity task : taskList) {
                        System.out.println(task);
                    }
                }
            }
        }
    }

    @Override
    public boolean isSecure() {
        return false;
    }
}
