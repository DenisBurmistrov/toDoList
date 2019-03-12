package ru.burmistrov.tm.command.project;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.burmistrov.tm.api.loader.ServiceLocator;
import ru.burmistrov.tm.api.service.IProjectService;
import ru.burmistrov.tm.command.AbstractCommand;

import java.util.Scanner;

public final class ProjectCreateCommand extends AbstractCommand {

    public ProjectCreateCommand() {
    }

    @Override
    public String getName() {
        return "-createProject";
    }

    @Override
    public String getDescription() {
        return "Create new project";
    }

    @Override
    public void execute() {
        if (getServiceLocator() != null) {
            @Nullable final Scanner scanner = getServiceLocator().getScanner();
            @Nullable final IProjectService projectService = getServiceLocator().getProjectService();
            System.out.println("Введите имя:");
            String name = scanner.nextLine();
            if (name.length() == 0) {
                System.out.println("Нельзя использовать пустое имя");
            }
            System.out.println("Введите описание:");
            @Nullable final String description = scanner.nextLine();
            if (projectService != null) {
                projectService.persist(getServiceLocator().getCurrentUser().getId(), name, description);
            }
        }
    }

    @Override
    public boolean isSecure() {
        return true;
    }
}
