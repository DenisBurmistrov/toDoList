package ru.burmistrov.tm.command.project;

import ru.burmistrov.tm.command.AbstractCommand;
import ru.burmistrov.tm.service.ProjectService;

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
