package ru.burmistrov.tm.command.project;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.burmistrov.tm.api.loader.ServiceLocator;
import ru.burmistrov.tm.command.AbstractCommand;
import ru.burmistrov.tm.endpoint.Exception_Exception;
import ru.burmistrov.tm.endpoint.ProjectDto;
import ru.burmistrov.tm.endpoint.ProjectEndpoint;
import ru.burmistrov.tm.endpoint.Session;
import ru.burmistrov.tm.service.TerminalCommandService;

import javax.inject.Inject;
import java.util.Objects;

public class ProjectFindByNameCommand extends AbstractCommand {

    @Inject
    private ServiceLocator serviceLocator;

    @NotNull
    @Override
    public String getName() {
        return "-printProjectByName";
    }

    @NotNull
    @Override
    public String getDescription() {
        return "Print project by name";
    }

    @Override
    public void execute() throws Exception_Exception {
        System.out.println("Введите имя проекта:");
        @NotNull final String name = serviceLocator.getTerminalCommandService().nextLine();
        System.out.println("Проект:");
        @Nullable final ProjectDto project = serviceLocator.getProjectEndpoint()
                .findProjectByName(serviceLocator.getSession(), Objects.requireNonNull(serviceLocator.getSession()).getUserId(), name);
        System.out.println(project);
    }

    @Override
    public boolean isSecure() {
        return true;
    }
}
