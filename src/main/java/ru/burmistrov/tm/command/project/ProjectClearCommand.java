package ru.burmistrov.tm.command.project;

import ru.burmistrov.tm.api.loader.ServiceLocator;
import ru.burmistrov.tm.api.service.IProjectService;
import ru.burmistrov.tm.command.AbstractCommand;

public final  class ProjectClearCommand extends AbstractCommand {

    private final IProjectService projectService = getServiceLocator().getProjectService();

    public ProjectClearCommand(final ServiceLocator serviceLocator) {
        super(serviceLocator);
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
            projectService.removeAll(getServiceLocator().getCurrentUser().getId());
    }

    @Override
    public boolean isSecure() {
        return true;
    }
}
