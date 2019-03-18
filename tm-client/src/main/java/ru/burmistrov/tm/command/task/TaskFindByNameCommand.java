package ru.burmistrov.tm.command.task;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.burmistrov.tm.api.service.ITaskService;
import ru.burmistrov.tm.command.AbstractCommand;

public class TaskFindByNameCommand extends AbstractCommand {

    @NotNull
    @Override
    public String getName() {
        return "-printTaskByName";
    }

    @NotNull
    @Override
    public String getDescription() {
        return "Print task by name";
    }

    @Override
    public void execute() {
        System.out.println("Введите имя задачи:");
        @NotNull final String name = getServiceLocator().getTerminalCommandService().nextLine();
        System.out.println(getServiceLocator().getTaskEndpoint().findTaskByName(, name));
    }

    @Override
    public boolean isSecure() {
        return true;
    }
}
