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

public final class ProjectRemoveCommand extends AbstractCommand {

    @Inject
    private ServiceLocator serviceLocator;

    @NotNull
    @Override
    public String getName() {
        return "-removeProject";
    }

    @NotNull
    @Override
    public String getDescription() {
        return "Delete project by ID";
    }

    @Override
    public void execute() throws Exception_Exception {
        System.out.println("Введите ID проекта: ");
        @NotNull final String projectId = serviceLocator.getTerminalCommandService().nextLine();
        serviceLocator.getProjectEndpoint()
                .removeProjectById(serviceLocator.getSession(), Objects.requireNonNull(serviceLocator.getSession()).getUserId(), projectId);
    }

    @Override
    public boolean isSecure() {
        return true;
    }
}
