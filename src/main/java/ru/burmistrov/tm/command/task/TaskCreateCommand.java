package ru.burmistrov.tm.command.task;

import ru.burmistrov.tm.api.loader.ServiceLocator;
import ru.burmistrov.tm.api.service.ITaskService;
import ru.burmistrov.tm.command.AbstractCommand;
import java.util.Scanner;

public final class TaskCreateCommand extends AbstractCommand {



    public TaskCreateCommand() {

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
        if(getServiceLocator() != null) {
            ITaskService taskService = getServiceLocator().getTaskService();
            Scanner scanner = getServiceLocator().getScanner();
            System.out.println("Введите ID проекта:");
            String id = scanner.nextLine();
            System.out.println("Введите имя задачи:");
            String oldName = scanner.nextLine();
            System.out.println("Введите описание для задачи: ");
            String description = scanner.nextLine();
            if (taskService != null) {
                taskService.persist(getServiceLocator().getCurrentUser().getId(), id, oldName, description);
            }
        }
    }

    @Override
    public boolean isSecure() {
        return true;
    }
}
