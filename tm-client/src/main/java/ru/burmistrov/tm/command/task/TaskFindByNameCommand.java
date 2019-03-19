package ru.burmistrov.tm.command.task;

import org.jetbrains.annotations.NotNull;
import ru.burmistrov.tm.command.AbstractCommand;

import java.util.Objects;

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
        System.out.println(getServiceLocator().getTaskEndpoint().findTaskByName(Objects.requireNonNull(getServiceLocator().getSession().getUserId()), name));
    }

    @Override
    public boolean isSecure() {
        return true;
    }
}
