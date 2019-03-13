package ru.burmistrov.tm.command.task;

import org.jetbrains.annotations.Nullable;
import ru.burmistrov.tm.api.service.ITaskService;
import ru.burmistrov.tm.command.AbstractCommand;

import java.util.Scanner;

public class TaskFindByNameCommand extends AbstractCommand {

    @Nullable
    @Override
    public String getName() {
        return "-printTaskByName";
    }

    @Nullable
    @Override
    public String getDescription() {
        return "Print task by name";
    }

    @Override
    public void execute() {
        if (getServiceLocator() != null) {
            @Nullable final ITaskService taskService = getServiceLocator().getTaskService();
            @Nullable final Scanner scanner = getServiceLocator().getScanner();
            System.out.println("Введите имя задачи:");
            @Nullable final String name = scanner.nextLine();
            if (taskService != null) {
                System.out.println(taskService.findOneByName(getServiceLocator().getCurrentUser().getId(), name));
            }
        }
    }

    @Override
    public boolean isSecure() {
        return true;
    }
}
