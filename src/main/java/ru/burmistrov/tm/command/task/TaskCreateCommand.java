package ru.burmistrov.tm.command.task;

import ru.burmistrov.tm.Bootstrap;
import ru.burmistrov.tm.command.AbstractCommand;
import ru.burmistrov.tm.service.TaskService;
import java.util.Scanner;

public class TaskCreateCommand extends AbstractCommand {

    private final TaskService taskService = getBootstrap().getTaskService();

    private final Scanner scanner = getBootstrap().getScanner();

    public TaskCreateCommand(Bootstrap bootstrap) {
        super(bootstrap);
    }

    @Override
    public String getName() {
        return "-createTask";
    }

    @Override
    public String getDescription() {
        return "Create task to project by project ID";
    }

    @Override
    public void execute() {
                System.out.println("Введите ID проекта:");
                String id = scanner.nextLine();
                System.out.println("Введите имя задачи");
                String oldName = scanner.nextLine();
                System.out.println("Введите описание для задачи: ");
                String description = scanner.nextLine();
                System.out.println("Введите приоритет для задачи от 0 до 5: ");
                String priority = scanner.nextLine();
                System.out.println(taskService.persist(id, oldName, description, priority));
    }

    @Override
    public boolean isSecure() {
        return true;
    }
}
