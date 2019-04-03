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
import java.util.List;
import java.util.Objects;

public final class ProjectListCommand extends AbstractCommand {

    @Inject
    private ServiceLocator serviceLocator;

    @Inject
    private ProjectEndpoint projectEndpoint;


    @NotNull
    @Override
    public String getName() {
        return "-print";
    }

    @NotNull
    @Override
    public String getDescription() {
        return "Print all projects";
    }

    @Override
    public void execute() throws Exception_Exception {
        System.out.println("Список проектов:");
        @Nullable final List<ProjectDto> projects = projectEndpoint
                .findAllProjects(serviceLocator.getSession(), Objects.requireNonNull(serviceLocator.getSession()).getUserId());
        for (ProjectDto project : Objects.requireNonNull(projects)) {
            System.out.println("ID: " + project.getId() + "; Название: " + project.getName() + "; Описание: " + project.getDescription()
                    + "; Дата создания: " + project.getDateBegin()
                    + "; Статус: " + project.getStatus());
        }
    }

    @Override
    public boolean isSecure() {
        return true;
    }
}
