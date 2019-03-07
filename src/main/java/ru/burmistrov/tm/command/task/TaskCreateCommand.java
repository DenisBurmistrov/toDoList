package ru.burmistrov.tm.command.task;

import ru.burmistrov.tm.api.loader.ServiceLocator;
import ru.burmistrov.tm.api.service.ITaskService;
import ru.burmistrov.tm.command.AbstractCommand;
import java.util.Scanner;

public final class TaskCreateCommand extends AbstractCommand {

    private final ITaskService taskService = getServiceLocator().getTaskService();

    private final Scanner scanner = getServiceLocator().getScanner();

    public TaskCreateCommand(final ServiceLocator serviceLocator) {
        super(serviceLocator);
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
                System.out.println("Введите имя задачи:");
                String oldName = scanner.nextLine();
                System.out.println("Введите описание для задачи: ");
                String description = scanner.nextLine();
                taskService.persist(getServiceLocator().getCurrentUser().getId(), id, oldName, description);
    }

    @Override
    public boolean isSecure() {
        return true;
    }
}
