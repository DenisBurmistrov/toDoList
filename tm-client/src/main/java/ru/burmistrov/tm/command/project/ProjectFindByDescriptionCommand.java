package ru.burmistrov.tm.command.project;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.burmistrov.tm.api.loader.ServiceLocator;
import ru.burmistrov.tm.command.AbstractCommand;
import ru.burmistrov.tm.endpoint.Exception_Exception;
import ru.burmistrov.tm.endpoint.ProjectDto;
import ru.burmistrov.tm.endpoint.ProjectEndpoint;
import ru.burmistrov.tm.endpoint.Session;
import ru.burmistrov.tm.service.TerminalCommandService;

import java.util.Objects;

@Component
public class ProjectFindByDescriptionCommand extends AbstractCommand {

    @Autowired
    private ServiceLocator serviceLocator;

    @Autowired
    private ProjectEndpoint projectEndpoint;


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
        @NotNull final String description = serviceLocator.getTerminalCommandService().nextLine();
        System.out.println("Проект:");
        @Nullable final ProjectDto project = projectEndpoint.findProjectByDescription
                (serviceLocator.getSession(), Objects.requireNonNull(serviceLocator.getSession()).getUserId(), description);
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
