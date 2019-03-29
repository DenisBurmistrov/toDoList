package ru.burmistrov.tm.command.project;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.burmistrov.tm.command.AbstractCommand;
import ru.burmistrov.tm.endpoint.Exception_Exception;
import ru.burmistrov.tm.endpoint.ProjectDto;

import java.util.Objects;

public class ProjectFindByDescriptionCommand extends AbstractCommand {

    @NotNull
    @Override
    public String getName() {
        return "-printProjectByDescription";
    }

    @NotNull
    @Override
    public String getDescription() {
        return "Print project by description";
    }

    @Override
    public void execute() throws Exception_Exception {
        System.out.println("Введите описание проекта:");
        @NotNull final String description = getServiceLocator().getTerminalCommandService().nextLine();
        System.out.println("Проект:");
        @Nullable final ProjectDto project = getServiceLocator().getProjectEndpoint().findProjectByDescription(getServiceLocator().getSession(),
                Objects.requireNonNull(Objects.requireNonNull(getServiceLocator().getSession()).getUserId()), description);
        if (project == null) {
            System.out.println("Нет проекта с таким описанием");
        } else {
            System.out.println(project);
        }
    }

    @Override
    public boolean isSecure() {
        return true;
    }
}
