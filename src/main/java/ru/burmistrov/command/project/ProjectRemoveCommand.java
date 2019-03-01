package ru.burmistrov.command.project;

import ru.burmistrov.command.AbstractCommand;
import ru.burmistrov.service.ProjectService;

public class ProjectRemoveCommand extends AbstractCommand {

    private ProjectService projectService = new ProjectService();

    @Override
    public String command() {
        return "-deleteById";
    }

    @Override
    public String description() {
        return "Delete project by ID";
    }

    @Override
    public void execute() {

        projectService.deleteProjectById();
    }
}
