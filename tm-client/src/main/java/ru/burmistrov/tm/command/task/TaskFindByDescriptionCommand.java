package ru.burmistrov.tm.command.task;

import org.jetbrains.annotations.NotNull;
import ru.burmistrov.tm.command.AbstractCommand;
import ru.burmistrov.tm.endpoint.CloneNotSupportedException_Exception;

import java.util.Objects;

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
    public void execute() throws CloneNotSupportedException_Exception {
        System.out.println("Введите описание задачи:");
        @NotNull final String description = getServiceLocator().getTerminalCommandService().nextLine();
        System.out.println(getServiceLocator().getTaskEndpoint()
                .findTaskByDescription(getServiceLocator().getSession(), Objects.requireNonNull(getServiceLocator().getSession().getUserId()), description));
    }

    @Override
    public boolean isSecure() {
        return true;
    }
}
