package ru.burmistrov.tm.command.project;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.burmistrov.tm.api.service.IProjectService;
import ru.burmistrov.tm.command.AbstractCommand;

import java.text.ParseException;

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
        System.out.println("Введите ID проекта:");
        @NotNull final String projectId = getServiceLocator().getTerminalCommandService().nextLine();
        System.out.println("Введите новое название проекта:");
        @NotNull final String name = getServiceLocator().getTerminalCommandService().nextLine();
        System.out.println("Введите новое описание:");
        @NotNull final String description = getServiceLocator().getTerminalCommandService().nextLine();
        System.out.println("Введите новую дату окончания (Пример: 27.10.2019):");
        @NotNull final String date = getServiceLocator().getTerminalCommandService().nextLine();
        getServiceLocator().getProjectEndpoint().updateProjectById(, projectId, name, description, date);
    }

    @Override
    public boolean isSecure() {
        return true;
    }
}