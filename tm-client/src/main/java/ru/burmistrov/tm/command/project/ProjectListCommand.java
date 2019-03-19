package ru.burmistrov.tm.command.project;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.burmistrov.tm.command.AbstractCommand;
import ru.burmistrov.tm.endpoint.Project;

import java.util.List;
import java.util.Objects;

public final class ProjectListCommand extends AbstractCommand {

    public ProjectListCommand() {
    }

    @NotNull
    @Override
    public String getName() {
        return "-print";
    }

    @NotNull
    @Override
    public String getDescription() {
        return "Print all projects";
    }

    @Override
    public void execute() {
        System.out.println("Список проектов:");
        @Nullable final List<Project> projects = getServiceLocator().getProjectEndpoint().findAllProjects(Objects.requireNonNull(getServiceLocator().getSession().getUserId()));
        for (Project project : Objects.requireNonNull(projects)) {
            System.out.println("ID: " + project.getId() + "; Название: " + project.getName() + "; Описание: " + project.getDescription()
                    + "; Дата создания: " + project.getDateBegin()+ "; ID назначенного пользователя: " + project.getDateEnd());
        }
    }

    @Override
    public boolean isSecure() {
        return true;
    }
}
