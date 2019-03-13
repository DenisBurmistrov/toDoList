package ru.burmistrov.tm.command.task;

import org.jetbrains.annotations.Nullable;
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
            @Nullable final ITaskService taskService = getServiceLocator().getTaskService();
            @Nullable final Scanner scanner = getServiceLocator().getScanner();
            System.out.println("Введите ID проекта:");
            @Nullable final String id = scanner.nextLine();
            System.out.println("Введите имя задачи:");
            @Nullable final String oldName = scanner.nextLine();
            System.out.println("Введите описание для задачи: ");
            @Nullable final String description = scanner.nextLine();
            System.out.println("Введите дату окончание (Пример: 27.10.2019):");
            String date = scanner.nextLine();
            if (taskService != null) {
                taskService.persist(getServiceLocator().getCurrentUser().getId(), id, oldName, description, date);
            }
        }
    }

    @Override
    public boolean isSecure() {
        return true;
    }
}
