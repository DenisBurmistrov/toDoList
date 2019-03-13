package ru.burmistrov.tm.command.project;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.burmistrov.tm.api.loader.ServiceLocator;
import ru.burmistrov.tm.api.service.IProjectService;
import ru.burmistrov.tm.command.AbstractCommand;
import ru.burmistrov.tm.entity.AbstractEntity;
import ru.burmistrov.tm.entity.Project;

import java.util.List;

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
        @Nullable final IProjectService<AbstractEntity> projectService = getServiceLocator().getProjectService();
        System.out.println("Список проектов:");
        @Nullable final List<AbstractEntity> projects = projectService.findAll(getServiceLocator().getCurrentUser().getId());
        for (AbstractEntity project : projects) {
            System.out.println(project);
        }
    }

    @Override
    public boolean isSecure() {
        return true;
    }
}
