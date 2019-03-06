package ru.burmistrov.tm.command.task;

import ru.burmistrov.tm.Bootstrap;
import ru.burmistrov.tm.command.AbstractCommand;
import ru.burmistrov.tm.entity.Task;
import ru.burmistrov.tm.service.TaskService;

import java.util.List;
import java.util.Scanner;

public class TaskListCommand extends AbstractCommand {

    private final TaskService taskService = getBootstrap().getTaskService();

    private final Scanner scanner = getBootstrap().getScanner();

    public TaskListCommand(Bootstrap bootstrap) {
        super(bootstrap);
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
            List<Task> taskList = taskService.findAll(getBootstrap().getCurrentUser(), id);
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
