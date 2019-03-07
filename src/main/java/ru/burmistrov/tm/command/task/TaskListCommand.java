package ru.burmistrov.tm.command.task;

import ru.burmistrov.tm.api.loader.ServiceLocator;
import ru.burmistrov.tm.api.service.ITaskService;
import ru.burmistrov.tm.command.AbstractCommand;
import ru.burmistrov.tm.entity.Task;

import java.util.List;
import java.util.Scanner;

public final class TaskListCommand extends AbstractCommand {

    private final ITaskService taskService = getServiceLocator().getTaskService();

    private final Scanner scanner = getServiceLocator().getScanner();

    public TaskListCommand(final ServiceLocator serviceLocator) {
        super(serviceLocator);
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
            System.out.println("Введите ID проекта:");
            String id = scanner.nextLine();
            List<Task> taskList = taskService.findAll(getServiceLocator().getCurrentUser().getId(), id);
            if(taskList == null) {
                System.out.println("У данного проекта нет задач");
            }
            else {
                for(Task task : taskList) {
                    System.out.println(task);
                }
            }
    }

    @Override
    public boolean isSecure() {
        return true;
    }
}
