package ru.burmistrov.tm.command.task;

import org.jetbrains.annotations.NotNull;
import ru.burmistrov.tm.command.AbstractCommand;
import ru.burmistrov.tm.endpoint.CloneNotSupportedException_Exception;
import ru.burmistrov.tm.endpoint.IOException_Exception;
import ru.burmistrov.tm.endpoint.ParseException_Exception;

import java.text.ParseException;
import java.util.Objects;

public final class TaskCreateCommand extends AbstractCommand {

    public TaskCreateCommand() {
    }

    @NotNull
    @Override
    public String getName() {
        return "-createTask";
    }

    @NotNull
    @Override
    public String getDescription() {
        return "Create task to project by project ID";
    }

    @Override
    public void execute() throws ParseException_Exception, CloneNotSupportedException_Exception, IOException_Exception {
        System.out.println("Введите ID проекта:");
        @NotNull final String id = getServiceLocator().getTerminalCommandService().nextLine();
        System.out.println("Введите имя задачи:");
        @NotNull final String oldName = getServiceLocator().getTerminalCommandService().nextLine();
        System.out.println("Введите описание для задачи: ");
        @NotNull final String description = getServiceLocator().getTerminalCommandService().nextLine();
        System.out.println("Введите дату окончание (Пример: 27.10.2019):");
        @NotNull final String date = getServiceLocator().getTerminalCommandService().nextLine();
        getServiceLocator().getTaskEndpoint()
                .createTask(getServiceLocator().getSession(), Objects.requireNonNull(getServiceLocator().getSession().getUserId()), id, oldName, description, date);
    }

    @Override
    public boolean isSecure() {
        return true;
    }
}
