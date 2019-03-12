package ru.burmistrov.tm.command.task;

import ru.burmistrov.tm.api.loader.ServiceLocator;
import ru.burmistrov.tm.api.service.ITaskService;
import ru.burmistrov.tm.command.AbstractCommand;

import java.util.Scanner;

public final class TaskClearCommand extends AbstractCommand {

    public TaskClearCommand() {

    }

    @Override
    public String getName() {
        return "-clearTasks";
    }

    @Override
    public String getDescription() {
        return "Clear project tasks by ID";
    }

    @Override
    public void execute() {
        if(getServiceLocator() != null) {
            ITaskService taskService = getServiceLocator().getTaskService();
            if (taskService != null) {
                taskService.removeAll(getServiceLocator().getCurrentUser().getId());
            }
        }
    }

    @Override
    public boolean isSecure() {
        return true;
    }
}
