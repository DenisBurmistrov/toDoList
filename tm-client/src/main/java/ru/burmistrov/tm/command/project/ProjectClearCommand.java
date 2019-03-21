package ru.burmistrov.tm.command.project;

import org.jetbrains.annotations.NotNull;
import ru.burmistrov.tm.command.AbstractCommand;
import ru.burmistrov.tm.endpoint.Exception_Exception;

import java.util.Objects;

public final class ProjectClearCommand extends AbstractCommand {

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
        getServiceLocator().getProjectEndpoint().removeAllProjects(getServiceLocator().getSession(), Objects.requireNonNull(getServiceLocator().getSession().getUserId()));
    }

    @Override
    public boolean isSecure() {
        return true;
    }

}
