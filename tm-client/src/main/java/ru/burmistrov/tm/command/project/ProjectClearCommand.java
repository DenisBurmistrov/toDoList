package ru.burmistrov.tm.command.project;

import org.jetbrains.annotations.NotNull;
import ru.burmistrov.tm.api.loader.ServiceLocator;
import ru.burmistrov.tm.command.AbstractCommand;
import ru.burmistrov.tm.endpoint.Exception_Exception;
import ru.burmistrov.tm.endpoint.ProjectEndpoint;
import ru.burmistrov.tm.endpoint.Session;

import javax.inject.Inject;
import java.util.Objects;

public final class ProjectClearCommand extends AbstractCommand {

    @Inject
    private ServiceLocator serviceLocator;

    @NotNull
    @Override
    public String getName() {
        return "-clearProjects";
    }

    @NotNull
    @Override
    public String getDescription() {
        return "clear all projects";
    }

    @Override
    public void execute() throws Exception_Exception {
        serviceLocator.getProjectEndpoint().removeAllProjects
                (serviceLocator.getSession(), Objects.requireNonNull(serviceLocator.getSession()).getUserId());
    }

    @Override
    public boolean isSecure() {
        return true;
    }

}
