package ru.burmistrov.tm.command.project;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.burmistrov.tm.command.AbstractCommand;
import ru.burmistrov.tm.endpoint.Exception_Exception;
import ru.burmistrov.tm.endpoint.ProjectDto;

import java.util.Objects;

public class ProjectFindByNameCommand extends AbstractCommand {

    @NotNull
    @Override
    public String getName() {
        return "-printProjectByName";
    }

    @NotNull
    @Override
    public String getDescription() {
        return "Print project by name";
    }

    @Override
    public void execute() throws Exception_Exception {
        System.out.println("Введите имя проекта:");
        @NotNull final String name = getServiceLocator().getTerminalCommandService().nextLine();
        System.out.println("Проект:");
        @Nullable final ProjectDto project = getServiceLocator().getProjectEndpoint()
                .findProjectByName(getServiceLocator().getSession(),
                        Objects.requireNonNull(Objects.requireNonNull(getServiceLocator().getSession()).getUserId()), name);
        System.out.println(project);
    }

    @Override
    public boolean isSecure() {
        return true;
    }
}
