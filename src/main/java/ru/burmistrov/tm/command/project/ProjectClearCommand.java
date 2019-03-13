package ru.burmistrov.tm.command.project;

import org.jetbrains.annotations.Nullable;
import ru.burmistrov.tm.api.service.IProjectService;
import ru.burmistrov.tm.command.AbstractCommand;

public final class ProjectClearCommand extends AbstractCommand {

    public ProjectClearCommand() {
    }

    @Override
    public String getName() {
        return "-clearProjects";
    }

    @Override
    public String getDescription() {
        return "clear all projects";
    }

    @Override
    public void execute() {
        @Nullable final IProjectService projectService = getServiceLocator().getProjectService();
        projectService.removeAll(getServiceLocator().getCurrentUser().getId());
    }

    @Override
    public boolean isSecure() {
        return true;
    }

}
