package ru.burmistrov.command.project;

import ru.burmistrov.command.AbstractCommand;
import ru.burmistrov.service.ProjectService;

public class ProjectListCommand extends AbstractCommand {

    private ProjectService projectService = new ProjectService();

    @Override
    public String command() {
        return "-print";
    }

    @Override
    public String description() {
        return "Print all projects";
    }

    @Override
    public void execute() {
        projectService.printProjects();
    }
}
