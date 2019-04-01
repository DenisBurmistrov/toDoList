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

public final class ProjectUpdateCommand extends AbstractCommand {

    @Inject
    private ServiceLocator serviceLocator;

    @NotNull
    @Override
    public String getName() {
        return "-updateProject";
    }

    @NotNull
    @Override
    public String getDescription() {
        return "Update project by Id";
    }

    @Override
    public void execute() throws Exception_Exception {
        System.out.println("Введите ID проекта:");
        @NotNull final String projectId = serviceLocator.getTerminalCommandService().nextLine();
        System.out.println("Введите новое название проекта:");
        @NotNull final String name = serviceLocator.getTerminalCommandService().nextLine();
        System.out.println("Введите новое описание:");
        @NotNull final String description = serviceLocator.getTerminalCommandService().nextLine();
        System.out.println("Введите новую дату окончания (Пример: 27.10.2019):");
        @NotNull final String date = serviceLocator.getTerminalCommandService().nextLine();
        System.out.println("Введите новый статус(Запланировано || В процессе || Готово):");
        @NotNull final String status = serviceLocator.getTerminalCommandService().nextLine();
        serviceLocator.getProjectEndpoint().updateProjectById(serviceLocator.getSession(), Objects.requireNonNull(serviceLocator.getSession()).getUserId(),
                projectId, name, description, date, status);
    }

    @Override
    public boolean isSecure() {
        return true;
    }
}
