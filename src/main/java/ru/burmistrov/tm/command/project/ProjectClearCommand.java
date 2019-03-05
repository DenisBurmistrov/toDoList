package ru.burmistrov.tm.command.project;

import ru.burmistrov.tm.Bootstrap;
import ru.burmistrov.tm.command.AbstractCommand;
import ru.burmistrov.tm.service.ProjectService;

public class ProjectClearCommand extends AbstractCommand {

    private final ProjectService projectService = getBootstrap().getProjectService();

    public ProjectClearCommand(Bootstrap bootstrap) {
        super(bootstrap);
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
            projectService.removeAll();
    }

    @Override
    public boolean isSecure() {
        return true;
    }
}
