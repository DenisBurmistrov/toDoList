package ru.burmistrov.command.project;

import ru.burmistrov.command.AbstractCommand;
import ru.burmistrov.service.ProjectService;

public class ProjectClearCommand extends AbstractCommand {

    private ProjectService projectService = new ProjectService();

    @Override
    public String command() {
        return "-clearProjects";
    }

    @Override
    public String description() {
        return "clear all projects";
    }

    @Override
    public void execute() {
        System.out.println(projectService.clearAll());

    }
}
