package ru.burmistrov.tm.command.project;

import ru.burmistrov.tm.api.loader.ServiceLocator;
import ru.burmistrov.tm.api.service.IProjectService;
import ru.burmistrov.tm.command.AbstractCommand;

import java.util.Scanner;

public final class ProjectUpdateCommand extends AbstractCommand {



    public ProjectUpdateCommand() {
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
        if (getServiceLocator() != null) {
            IProjectService projectService = getServiceLocator().getProjectService();

            Scanner scanner = getServiceLocator().getScanner();
            System.out.println("Введите ID проекта:");
            String projectId = scanner.nextLine();
            System.out.println("Введите новое название проекта:");
            String name = scanner.nextLine();
            System.out.println("Введите новое описание:");
            String description = scanner.nextLine();
            if (projectService != null) {
                projectService.merge(getServiceLocator().getCurrentUser().getId(), projectId, name, description);
            }
        }
    }

    @Override
    public boolean isSecure() {
        return true;
    }
}
