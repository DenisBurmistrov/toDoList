package ru.burmistrov.tm.command.task;

import ru.burmistrov.tm.api.loader.ServiceLocator;
import ru.burmistrov.tm.api.service.ITaskService;
import ru.burmistrov.tm.command.AbstractCommand;
import ru.burmistrov.tm.entity.AbstractEntity;
import ru.burmistrov.tm.entity.Task;

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
        if (getServiceLocator() != null) {
            ITaskService<AbstractEntity> taskService = getServiceLocator().getTaskService();
            Scanner scanner = getServiceLocator().getScanner();
            System.out.println("Введите ID проекта:");
            String id = scanner.nextLine();
            List<AbstractEntity> taskList = null;
            if (taskService != null) {
                taskList = taskService.findAll(getServiceLocator().getCurrentUser().getId(), id);
            }
            if (taskList == null) {
                System.out.println("У данного проекта нет задач");
            } else {
                for (AbstractEntity task : taskList) {
                    System.out.println(task);
                }
            }
        }
    }

    @Override
    public boolean isSecure() {
        return true;
    }
}
