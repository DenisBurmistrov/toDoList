package ru.burmistrov.tm.command.project;

import ru.burmistrov.tm.api.loader.ServiceLocator;
import ru.burmistrov.tm.api.service.IProjectService;
import ru.burmistrov.tm.command.AbstractCommand;

import java.util.Scanner;

public final class ProjectRemoveCommand extends AbstractCommand {



    public ProjectRemoveCommand() {

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
        if (getServiceLocator() != null) {
            Scanner scanner = getServiceLocator().getScanner();

            IProjectService projectService = getServiceLocator().getProjectService();
            System.out.println("Введите ID проекта: ");
            String projectId = scanner.nextLine();
            if (projectService != null) {
                projectService.remove(getServiceLocator().getCurrentUser().getId(), projectId);
            }
        }
    }

    @Override
    public boolean isSecure() {
        return true;
    }
}
