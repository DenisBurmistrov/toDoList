package ru.burmistrov.tm.command.task;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.burmistrov.tm.api.service.ITaskService;
import ru.burmistrov.tm.command.AbstractCommand;

public class TaskFindByDescriptionCommand extends AbstractCommand {

    @NotNull
    @Override
    public String getName() {
        return "-printTaskByDescription";
    }

    @NotNull
    @Override
    public String getDescription() {
        return "Print task by name";
    }

    @Override
    public void execute() {
        System.out.println("Введите описание задачи:");
        @NotNull final String description = getServiceLocator().getTerminalCommandService().nextLine();
        System.out.println(getServiceLocator().getTaskEndpoint().findTaskByDescription( , description));
    }

    @Override
    public boolean isSecure() {
        return true;
    }
}
