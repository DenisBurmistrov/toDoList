package ru.burmistrov.tm.command.project;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.burmistrov.tm.api.service.IProjectService;
import ru.burmistrov.tm.command.AbstractCommand;

import java.util.Scanner;

public final class ProjectRemoveCommand extends AbstractCommand {

    public ProjectRemoveCommand() {
    }

    @NotNull
    @Override
    public String getName() {
        return "-removeProject";
    }

    @NotNull
    @Override
    public String getDescription() {
        return "Delete project by ID";
    }

    @Override
    public void execute() {
        @Nullable final IProjectService projectService = getServiceLocator().getProjectService();
        System.out.println("Введите ID проекта: ");
        @NotNull final String projectId = getServiceLocator().getTerminalCommandService().nextLine();
        projectService.remove(getServiceLocator().getCurrentUser().getId(), projectId);
    }

    @Override
    public boolean isSecure() {
        return true;
    }
}
