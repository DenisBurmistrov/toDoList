package ru.burmistrov.command.project;

import ru.burmistrov.command.AbstractCommand;
import ru.burmistrov.service.ProjectService;

public class ProjectCreateCommand extends AbstractCommand {

    private ProjectService projectService = new ProjectService();

    @Override
    public String command() {
        return "-createProject";
    }

    @Override
    public String description() {
        return "Create new project";
    }

    @Override
    public void execute() {
        projectService.addProject();
    }
}
