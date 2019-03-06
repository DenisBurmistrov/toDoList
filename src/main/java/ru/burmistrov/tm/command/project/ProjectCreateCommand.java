package ru.burmistrov.tm.command.project;

import ru.burmistrov.tm.Bootstrap;
import ru.burmistrov.tm.command.AbstractCommand;
import ru.burmistrov.tm.service.ProjectService;

import java.util.Scanner;

public class ProjectCreateCommand extends AbstractCommand {

    private final Scanner scanner = getBootstrap().getScanner();

    private final ProjectService projectService = getBootstrap().getProjectService();

    public ProjectCreateCommand(Bootstrap bootstrap) {
        super(bootstrap);
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
            projectService.persist(getBootstrap().getCurrentUser(), name, description);
    }

    @Override
    public boolean isSecure() {
        return true;
    }
}
