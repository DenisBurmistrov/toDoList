package ru.burmistrov.tm.command.task;

import ru.burmistrov.tm.Bootstrap;
import ru.burmistrov.tm.command.AbstractCommand;
import ru.burmistrov.tm.service.TaskService;

import java.util.Scanner;

public class TaskClearCommand extends AbstractCommand {

    private final TaskService taskService = getBootstrap().getTaskService();

    private final Scanner scanner = getBootstrap().getScanner();

    public TaskClearCommand(Bootstrap bootstrap) {
        super(bootstrap);
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
            System.out.println("Введите ID проекта:");
            String id = scanner.nextLine();
            taskService.removeAllInProject(getBootstrap().getCurrentUser(), id);
    }

    @Override
    public boolean isSecure() {
        return true;
    }
}
