package ru.burmistrov.tm.command.project;

import org.jetbrains.annotations.Nullable;
import ru.burmistrov.tm.api.service.IProjectService;
import ru.burmistrov.tm.command.AbstractCommand;

import java.text.ParseException;
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
    public void execute() throws ParseException {
        @Nullable final Scanner scanner = getServiceLocator().getScanner();
        @Nullable final IProjectService projectService = getServiceLocator().getProjectService();
        System.out.println("Введите имя:");
        String name = scanner.nextLine();
        if (name.length() == 0) {
            System.out.println("Нельзя использовать пустое имя");
        }
        System.out.println("Введите описание:");
        @Nullable final String description = scanner.nextLine();
        System.out.println("Введите дату окончания (Пример: 27.10.2019):");
        String date = scanner.nextLine();
        projectService.persist(getServiceLocator().getCurrentUser().getId(), name, description, date);
    }

    @Override
    public boolean isSecure() {
        return true;
    }
}
