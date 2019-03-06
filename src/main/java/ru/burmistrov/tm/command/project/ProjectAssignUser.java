package ru.burmistrov.tm.command.project;

import ru.burmistrov.tm.Bootstrap;
import ru.burmistrov.tm.command.AbstractCommand;
import ru.burmistrov.tm.service.ProjectService;

import java.util.Scanner;

public class ProjectAssignUser extends AbstractCommand {

    private final Scanner scanner = getBootstrap().getScanner();

    private final ProjectService projectService = getBootstrap().getProjectService();

    public ProjectAssignUser(Bootstrap bootstrap) {
        super(bootstrap);
    }

    @Override
    public String getName() {
        return "-assignUser";
    }

    @Override
    public String getDescription() {
        return "Assign project to user";
    }

    @Override
    public void execute() {
        System.out.println("Введите ID проекта:");
        String projectId = scanner.nextLine();
        System.out.println("Введите ID пользователя");
        String userId = scanner.nextLine();
        projectService.assignUser(getBootstrap().getCurrentUser(), projectId, userId);
    }

    @Override
    public boolean isSecure() {
        return true;
    }
}
