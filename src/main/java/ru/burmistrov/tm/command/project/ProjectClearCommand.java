package ru.burmistrov.tm.command.project;

import ru.burmistrov.tm.Bootstrap;
import ru.burmistrov.tm.command.AbstractCommand;

public class ProjectClearCommand extends AbstractCommand {


    public ProjectClearCommand(Bootstrap bootstrap) {
        super(bootstrap);
    }

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
        System.out.println(super.getBootstrap().getProjectService().removeAll());

    }
}
