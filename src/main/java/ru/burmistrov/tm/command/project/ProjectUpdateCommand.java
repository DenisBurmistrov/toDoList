package ru.burmistrov.tm.command.project;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.burmistrov.tm.api.loader.ServiceLocator;
import ru.burmistrov.tm.api.service.IProjectService;
import ru.burmistrov.tm.command.AbstractCommand;

import java.text.ParseException;
import java.util.Arrays;
import java.util.Scanner;

public final class ProjectUpdateCommand extends AbstractCommand {
    
    public ProjectUpdateCommand() {
    }

    @NotNull
    @Override
    public String getName() {
        return "-updateProject";
    }

    @NotNull
    @Override
    public String getDescription() {
        return "Update project by Id";
    }

    @Override
    public void execute() throws ParseException {
        @Nullable final IProjectService projectService = getServiceLocator().getProjectService();
        @Nullable final Scanner scanner = getServiceLocator().getScanner();
        System.out.println("Введите ID проекта:");
        @NotNull final String projectId = scanner.nextLine();
        System.out.println("Введите новое название проекта:");
        @NotNull final String name = scanner.nextLine();
        System.out.println("Введите новое описание:");
        @NotNull final String description = scanner.nextLine();
        System.out.println("Введите новую дату окончания (Пример: 27.10.2019):");
        @NotNull final String date = scanner.nextLine();
        projectService.merge(getServiceLocator().getCurrentUser().getId(), projectId, name, description, date);
    }

    @Override
    public boolean isSecure() {
        return true;
    }
}
