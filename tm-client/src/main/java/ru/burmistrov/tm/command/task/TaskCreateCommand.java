package ru.burmistrov.tm.command.task;

import org.jetbrains.annotations.NotNull;
import ru.burmistrov.tm.command.AbstractCommand;
import ru.burmistrov.tm.endpoint.Exception_Exception;

import java.util.Objects;

public final class TaskCreateCommand extends AbstractCommand {

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
    public void execute() throws Exception_Exception {
        System.out.println("Введите ID проекта:");
        @NotNull final String id = getServiceLocator().getTerminalCommandService().nextLine();
        System.out.println("Введите имя задачи:");
        @NotNull final String oldName = getServiceLocator().getTerminalCommandService().nextLine();
        System.out.println("Введите описание для задачи: ");
        @NotNull final String description = getServiceLocator().getTerminalCommandService().nextLine();
        System.out.println("Введите дату окончание (Пример: 27.10.2019):");
        @NotNull final String date = getServiceLocator().getTerminalCommandService().nextLine();
        System.out.println("Введите статус(Запланировано || В процессе || Готово)");
        @NotNull final String status = getServiceLocator().getTerminalCommandService().nextLine();
        getServiceLocator().getTaskEndpoint()
                .createTask(getServiceLocator().getSession(),
                        Objects.requireNonNull(Objects.requireNonNull(getServiceLocator().getSession()).getUserId()), id, oldName, description, date, status);
    }

    @Override
    public boolean isSecure() {
        return true;
    }
}
