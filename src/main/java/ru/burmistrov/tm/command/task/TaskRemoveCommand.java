package ru.burmistrov.tm.command.task;

import ru.burmistrov.tm.api.loader.ServiceLocator;
import ru.burmistrov.tm.api.service.ITaskService;
import ru.burmistrov.tm.command.AbstractCommand;
import java.util.Scanner;

public final class TaskRemoveCommand extends AbstractCommand {

    private final ITaskService taskService = getServiceLocator().getTaskService();

    private final Scanner scanner = getServiceLocator().getScanner();

    public TaskRemoveCommand() {

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
            taskService.remove(getServiceLocator().getCurrentUser().getId(), projectId, taskId);
    }

    @Override
    public boolean isSecure() {
        return true;
    }
}
