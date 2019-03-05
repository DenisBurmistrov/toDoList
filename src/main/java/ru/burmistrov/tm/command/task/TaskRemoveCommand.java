package ru.burmistrov.tm.command.task;

import ru.burmistrov.tm.Bootstrap;
import ru.burmistrov.tm.command.AbstractCommand;
import ru.burmistrov.tm.service.TaskService;
import java.util.Scanner;

public class TaskRemoveCommand extends AbstractCommand {

    private final TaskService taskService = getBootstrap().getTaskService();

    private final Scanner scanner = getBootstrap().getScanner();

    public TaskRemoveCommand(Bootstrap bootstrap) {
        super(bootstrap);
    }

    @Override
    public String getName() {
        return "-removeTask";
    }

    @Override
    public String getDescription() {
        return "Remove task by task ID by Project ID";
    }

    @Override
    public void execute() {
            System.out.println("Введите ID проекта");
            String projectId = scanner.nextLine();
            System.out.println("Введите ID задачи");
            String taskId = scanner.next();
            taskService.remove(projectId, taskId);
    }

    @Override
    public boolean isSecure() {
        return true;
    }
}
