package ru.burmistrov.tm.command.project;

import ru.burmistrov.tm.Bootstrap;
import ru.burmistrov.tm.api.loader.ServiceLocator;
import ru.burmistrov.tm.api.service.IProjectService;
import ru.burmistrov.tm.command.AbstractCommand;

import java.util.Scanner;

public final class ProjectAssignUser extends AbstractCommand {

    private final Scanner scanner = getServiceLocator().getScanner();

    private final IProjectService projectService = getServiceLocator().getProjectService();

    public ProjectAssignUser(final ServiceLocator serviceLocator) {
        super(serviceLocator);
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
        projectService.assignUser(getServiceLocator().getCurrentUser(), projectId, userId);
    }

    @Override
    public boolean isSecure() {
        return true;
    }
}
