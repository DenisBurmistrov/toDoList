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

import java.util.List;
import java.util.Objects;

@Component
public class ProjectListSortedByDateEndCommand extends AbstractCommand {

    @Autowired
    private ServiceLocator serviceLocator;

    @Autowired
    private ProjectEndpoint projectEndpoint;


    @NotNull
    @Override
    public String getName() {
        return "-printByDateEnd";
    }

    @NotNull
    @Override
    public String getDescription() {
        return "Print project list of sorted by date end";
    }

    @Override
    public void execute() throws Exception_Exception {
        System.out.println("Список проектов:");
        @Nullable final List<ProjectDto> projects = projectEndpoint
                .findAllProjectsSortByDateEnd(serviceLocator.getSession(), Objects.requireNonNull(serviceLocator.getSession()).getUserId());
        for (ProjectDto project : Objects.requireNonNull(projects)) {
            System.out.println("ID: " + project.getId() + "; Название: " + project.getName() + "; Описание: " + project.getDescription()
                    + "; Дата создания: " + project.getDateBegin()+ "; ID назначенного пользователя: " + project.getDateEnd() + "; Статус: " + project.getStatus());
        }
    }

    @Override
    public boolean isSecure() {
        return true;
    }
}

