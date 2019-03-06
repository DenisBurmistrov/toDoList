package ru.burmistrov.tm.command.task;

import ru.burmistrov.tm.Bootstrap;
import ru.burmistrov.tm.api.loader.ServiceLocator;
import ru.burmistrov.tm.api.service.ITaskService;
import ru.burmistrov.tm.command.AbstractCommand;

import java.util.Scanner;

public final class TaskUpdateCommand extends AbstractCommand {

    private final ITaskService taskService = getServiceLocator().getTaskService();

    private final Scanner scanner = getServiceLocator().getScanner();

    public TaskUpdateCommand(final ServiceLocator serviceLocator) {
        super(serviceLocator);
    }

    @Override
    public String getName() {
        return "-updateTask";
    }

    @Override
    public String getDescription() {
        return "Update task by project ID by task ID";
    }

    @Override
    public void execute() {
        System.out.println("Введите ID проекта:");
        String projectId = scanner.nextLine();
        System.out.println("Введите имя задачи:");
        String oldName = scanner.nextLine();
        System.out.println("Введите новое имя:");
        String newName = scanner.nextLine();
        System.out.println("Введите новое описание: ");
        String description = scanner.nextLine();
        taskService.merge(getServiceLocator().getCurrentUser(), projectId, oldName, newName, description);
    }

    @Override
    public boolean isSecure() {
        return true;
    }
}
