package ru.burmistrov.tm.command.project;

import ru.burmistrov.tm.Bootstrap;
import ru.burmistrov.tm.command.AbstractCommand;
import ru.burmistrov.tm.service.ProjectService;

import java.util.Scanner;

public class ProjectUpdateCommand extends AbstractCommand {

    private final ProjectService projectService = getBootstrap().getProjectService();

    private final Scanner scanner = getBootstrap().getScanner();

    public ProjectUpdateCommand(Bootstrap bootstrap) {
        super(bootstrap);
    }

    @Override
    public String getName() {
        return "-updateProject";
    }

    @Override
    public String getDescription() {
        return "Update project by Id";
    }

    @Override
    public void execute() {
            System.out.println("Введите ID проекта:");
            String projectId = scanner.nextLine();
            System.out.println("Введите новое название проекта:");
            String name = scanner.nextLine();
            System.out.println("Введите новое описание:");
            String description = scanner.nextLine();
            projectService.merge(getBootstrap().getCurrentUser(), projectId, name, description);
        }

    @Override
    public boolean isSecure() {
        return true;
    }
}
