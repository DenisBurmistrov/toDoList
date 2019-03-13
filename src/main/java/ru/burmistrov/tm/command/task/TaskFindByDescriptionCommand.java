package ru.burmistrov.tm.command.task;

import org.jetbrains.annotations.Nullable;
import ru.burmistrov.tm.api.service.ITaskService;
import ru.burmistrov.tm.command.AbstractCommand;

import java.util.Scanner;

public class TaskFindByDescriptionCommand extends AbstractCommand {

    @Nullable
    @Override
    public String getName() {
        return "-printTaskByDescription";
    }

    @Nullable
    @Override
    public String getDescription() {
        return "Print task by name";
    }

    @Override
    public void execute() {
        @Nullable final ITaskService taskService = getServiceLocator().getTaskService();
        @Nullable final Scanner scanner = getServiceLocator().getScanner();
        System.out.println("Введите описание задачи:");
        @Nullable final String description = scanner.nextLine();
        System.out.println(taskService.findOneByDescription(getServiceLocator().getCurrentUser().getId(), description));
    }

    @Override
    public boolean isSecure() {
        return true;
    }
}
