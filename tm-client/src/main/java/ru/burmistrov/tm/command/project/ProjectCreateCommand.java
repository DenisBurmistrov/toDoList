package ru.burmistrov.tm.command.project;

import org.jetbrains.annotations.NotNull;
import ru.burmistrov.tm.api.loader.ServiceLocator;
import ru.burmistrov.tm.command.AbstractCommand;
import ru.burmistrov.tm.endpoint.Exception_Exception;
import ru.burmistrov.tm.endpoint.ProjectEndpoint;
import ru.burmistrov.tm.endpoint.Session;
import ru.burmistrov.tm.service.TerminalCommandService;

import javax.inject.Inject;
import java.util.Objects;

public final class ProjectCreateCommand extends AbstractCommand {

    @Inject
    private ServiceLocator serviceLocator;

    @NotNull
    @Override
    public String getName() {
        return "-createProject";
    }

    @NotNull
    @Override
    public String getDescription() {
        return "Create new project";
    }

    @Override
    public void execute() throws Exception_Exception {
        System.out.println("Введите имя:");
        @NotNull final String name = serviceLocator.getTerminalCommandService().nextLine();
        if (name.length() == 0) {
            System.out.println("Нельзя использовать пустое имя");
        }
        System.out.println("Введите описание:");
        @NotNull final String description = serviceLocator.getTerminalCommandService().nextLine();
        System.out.println("Введите дату окончания (Пример: 27.10.2019):");
        @NotNull final String date = serviceLocator.getTerminalCommandService().nextLine();
        System.out.println("Введите статус(Запланировано || В процессе || Готово): ");
        @NotNull final String status = serviceLocator.getTerminalCommandService().nextLine();
        serviceLocator.getProjectEndpoint().createProject(serviceLocator.getSession(), Objects.requireNonNull(serviceLocator.getSession()).getUserId(),
                name, description, date, status);
    }

    @Override
    public boolean isSecure() {
        return true;
    }
}
