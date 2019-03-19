package ru.burmistrov.tm.command.project;

import org.jetbrains.annotations.NotNull;
import ru.burmistrov.tm.command.AbstractCommand;

import java.util.Objects;

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
        System.out.println("Введите ID проекта: ");
        @NotNull final String projectId = getServiceLocator().getTerminalCommandService().nextLine();
        getServiceLocator().getProjectEndpoint().removeProjectById(Objects.requireNonNull(getServiceLocator().getSession().getUserId()), projectId);
    }

    @Override
    public boolean isSecure() {
        return true;
    }
}
