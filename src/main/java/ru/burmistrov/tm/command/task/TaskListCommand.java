package ru.burmistrov.tm.command.task;

import org.jetbrains.annotations.Nullable;
import ru.burmistrov.tm.api.service.ITaskService;
import ru.burmistrov.tm.command.AbstractCommand;
import ru.burmistrov.tm.entity.AbstractEntity;

import java.util.List;
import java.util.Scanner;

public final class TaskListCommand extends AbstractCommand {

    public TaskListCommand() {
    }

    @Override
    public String getName() {
        return "-printTasks";
    }

    @Override
    public String getDescription() {
        return "Print tasks of project by project ID";
    }

    @Override
    public void execute() {
        @Nullable final ITaskService<AbstractEntity> taskService = getServiceLocator().getTaskService();
        @Nullable final Scanner scanner = getServiceLocator().getScanner();
        System.out.println("Введите ID проекта:");
        @Nullable final String id = scanner.nextLine();
        @Nullable final List<AbstractEntity> taskList = taskService.findAll(getServiceLocator().getCurrentUser().getId(), id);
        for (AbstractEntity task : taskList) {
            System.out.println(task);
        }
    }

    @Override
    public boolean isSecure() {
        return true;
    }
}
