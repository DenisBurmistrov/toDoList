package ru.burmistrov.tm.command.project;

import org.jetbrains.annotations.Nullable;
import ru.burmistrov.tm.api.service.IProjectService;
import ru.burmistrov.tm.command.AbstractCommand;
import ru.burmistrov.tm.entity.AbstractEntity;

import java.util.List;

public class ProjectListSortedByDateEndCommand extends AbstractCommand {

    @Nullable
    @Override
    public String getName() {
        return "-printByDateEnd";
    }

    @Nullable
    @Override
    public String getDescription() {
        return "Print project list of sorted by date end";
    }

    @Override
    public void execute() {
        @Nullable final IProjectService<AbstractEntity> projectService = getServiceLocator().getProjectService();
        System.out.println("Список проектов:");
        @Nullable final List<AbstractEntity> projects = projectService.findAllSortByDateEnd(getServiceLocator().getCurrentUser().getId());
        System.out.println("У пользователя нет проектов");
        for (AbstractEntity project : projects) {
            System.out.println(project);
        }
    }

    @Override
    public boolean isSecure() {
        return true;
    }
}
