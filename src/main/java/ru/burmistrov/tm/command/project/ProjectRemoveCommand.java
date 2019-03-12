package ru.burmistrov.tm.command.project;

import org.jetbrains.annotations.Nullable;
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
            @Nullable final Scanner scanner = getServiceLocator().getScanner();
            @Nullable final IProjectService projectService = getServiceLocator().getProjectService();
            System.out.println("Введите ID проекта: ");
            @Nullable final String projectId = scanner.nextLine();
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
