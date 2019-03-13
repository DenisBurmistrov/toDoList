package ru.burmistrov.tm.command.project;

import org.jetbrains.annotations.Nullable;
import ru.burmistrov.tm.api.service.IProjectService;
import ru.burmistrov.tm.command.AbstractCommand;
import ru.burmistrov.tm.entity.AbstractEntity;

import java.util.List;

public class ProjectListSortedByStatus extends AbstractCommand {

    @Nullable
    @Override
    public String getName() {
        return "-printByStatus";
    }

    @Nullable
    @Override
    public String getDescription() {
        return "Print project list of sorted by status";
    }

    @Override
    public void execute() {
        if (getServiceLocator() != null) {
            @Nullable final IProjectService<AbstractEntity> projectService = getServiceLocator().getProjectService();
            System.out.println("Список проектов:");
            if(projectService != null) {
                @Nullable final List<AbstractEntity> projects = projectService.findAllSortByStatus(getServiceLocator().getCurrentUser().getId());
                if (projects == null) {
                    System.out.println("У пользователя нет проектов");
                } else {
                    for (AbstractEntity project : projects) {
                        System.out.println(project);
                    }
                }
            }
        }
    }

    @Override
    public boolean isSecure() {
        return true;
    }
}
