package ru.burmistrov.tm.command.task;

import ru.burmistrov.tm.api.loader.ServiceLocator;
import ru.burmistrov.tm.api.service.ITaskService;
import ru.burmistrov.tm.command.AbstractCommand;

import java.util.Scanner;

public final class TaskClearCommand extends AbstractCommand {

    private final ITaskService taskService = getServiceLocator().getTaskService();

    private final Scanner scanner = getServiceLocator().getScanner();

    public TaskClearCommand(final ServiceLocator serviceLocator) {
        super(serviceLocator);
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
            taskService.removeAllInProject(getServiceLocator().getCurrentUser(), id);
    }

    @Override
    public boolean isSecure() {
        return true;
    }
}
