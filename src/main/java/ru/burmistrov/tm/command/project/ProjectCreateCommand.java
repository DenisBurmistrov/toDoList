package ru.burmistrov.tm.command.project;

import ru.burmistrov.tm.api.loader.ServiceLocator;
import ru.burmistrov.tm.api.service.IProjectService;
import ru.burmistrov.tm.command.AbstractCommand;

import java.util.Scanner;

public final class ProjectCreateCommand extends AbstractCommand {

    private final Scanner scanner = getServiceLocator().getScanner();

    private final IProjectService projectService = getServiceLocator().getProjectService();

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
            System.out.println("Введите имя:");
            String name = scanner.nextLine();
            if (name.length() == 0) {
                System.out.println("Нельзя использовать пустое имя");
            }
            System.out.println("Введите описание:");
            String description = scanner.nextLine();
            projectService.persist(getServiceLocator().getCurrentUser().getId(), name, description);
    }

    @Override
    public boolean isSecure() {
        return true;
    }
}
