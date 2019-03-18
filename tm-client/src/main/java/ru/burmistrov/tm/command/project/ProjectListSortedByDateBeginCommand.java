package ru.burmistrov.tm.command.project;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.burmistrov.tm.api.service.IProjectService;
import ru.burmistrov.tm.command.AbstractCommand;
import ru.burmistrov.tm.entity.AbstractEntity;

import java.util.List;

public class ProjectListSortedByDateBeginCommand extends AbstractCommand {

    @NotNull
    @Override
    public String getName() {
        return "-printByDateBegin";
    }

    @NotNull
    @Override
    public String getDescription() {
        return "Print project list of sorted by date begin";
    }

    @Override
    public void execute() {
        System.out.println("Список проектов:");
        @Nullable final List<AbstractEntity> projects = getServiceLocator().getProjectEndpoint().findAllProjectsSortByDateBegin();
        for (AbstractEntity project : projects) {
            System.out.println(project);
        }
    }

    @Override
    public boolean isSecure() {
        return true;
    }
}
