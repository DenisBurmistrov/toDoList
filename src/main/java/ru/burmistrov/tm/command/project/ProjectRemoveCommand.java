package ru.burmistrov.tm.command.project;

import ru.burmistrov.tm.Bootstrap;
import ru.burmistrov.tm.command.AbstractCommand;
import ru.burmistrov.tm.service.ProjectService;

import java.util.Scanner;

public class ProjectRemoveCommand extends AbstractCommand {

    private final Scanner scanner = getBootstrap().getScanner();

    private final ProjectService projectService = getBootstrap().getProjectService();

    public ProjectRemoveCommand(Bootstrap bootstrap) {
        super(bootstrap);
    }

    @Override
    public String getName() {
        return "-removeProject";
    }

    @Override
    public String getDescription() {
        return "Delete project by ID";
    }

    @Override
    public void execute() {
            System.out.println("Введите ID проекта: ");
            String projectId = scanner.nextLine();
            projectService.remove(getBootstrap().getCurrentUser(), projectId);
        }

    @Override
    public boolean isSecure() {
        return true;
    }
}
