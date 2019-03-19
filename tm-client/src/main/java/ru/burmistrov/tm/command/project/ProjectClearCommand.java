package ru.burmistrov.tm.command.project;

import org.jetbrains.annotations.NotNull;
import ru.burmistrov.tm.command.AbstractCommand;

import java.util.Objects;

public final class ProjectClearCommand extends AbstractCommand {

    public ProjectClearCommand() {
    }

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
    public void execute() {
        getServiceLocator().getProjectEndpoint().removeAllProjects(Objects.requireNonNull(getServiceLocator().getSession().getUserId()));
    }

    @Override
    public boolean isSecure() {
        return true;
    }

}
