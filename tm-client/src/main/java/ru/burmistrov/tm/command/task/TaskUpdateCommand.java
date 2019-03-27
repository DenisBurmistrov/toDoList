package ru.burmistrov.tm.command.task;

import org.jetbrains.annotations.NotNull;
import ru.burmistrov.tm.command.AbstractCommand;
import ru.burmistrov.tm.endpoint.Exception_Exception;

import java.util.Objects;

public final class TaskUpdateCommand extends AbstractCommand {

    @NotNull
    @Override
    public String getName() {
        return "-updateTask";
    }

    @NotNull
    @Override
    public String getDescription() {
        return "Update task by project ID by task ID";
    }

    @Override
    public void execute() throws Exception_Exception {
        System.out.println("Введите ID проекта:");
        @NotNull final String projectId = getServiceLocator().getTerminalCommandService().nextLine();
        System.out.println("Введите ID задачи:");
        @NotNull final String taskId = getServiceLocator().getTerminalCommandService().nextLine();
        System.out.println("Введите новое имя:");
        @NotNull final String newName = getServiceLocator().getTerminalCommandService().nextLine();
        System.out.println("Введите новое описание: ");
        @NotNull final String description = getServiceLocator().getTerminalCommandService().nextLine();
        System.out.println("Введите новую дату окончания (Пример: 27.10.2019):");
        @NotNull final String date = getServiceLocator().getTerminalCommandService().nextLine();
        System.out.println("Введите новый статус(Запланировано || В процессе || Готово)");
        @NotNull final String status = getServiceLocator().getTerminalCommandService().nextLine();
        getServiceLocator().getTaskEndpoint()
                .updateTaskById(getServiceLocator().getSession(),
                        Objects.requireNonNull
                                (Objects.requireNonNull(getServiceLocator().getSession()).getUserId()), projectId, taskId, newName, description, date, status);
    }

    @Override
    public boolean isSecure() {
        return true;
    }
}
