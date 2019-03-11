package ru.burmistrov.tm.command.project;

import ru.burmistrov.tm.api.loader.ServiceLocator;
import ru.burmistrov.tm.api.service.IProjectService;
import ru.burmistrov.tm.command.AbstractCommand;
import ru.burmistrov.tm.entity.AbstractEntity;
import ru.burmistrov.tm.entity.Project;

import java.util.List;

public final class ProjectListCommand extends AbstractCommand {

    private final IProjectService<AbstractEntity> projectService = getServiceLocator().getProjectService();

    public ProjectListCommand(final ServiceLocator serviceLocator) {
        super(serviceLocator);
    }

    @Override
    public String getName() {
        return "-print";
    }

    @Override
    public String getDescription() {
        return "Print all projects";
    }

    @Override
    public void execute() {
        System.out.println("Список проектов:");
        List<AbstractEntity> projects = projectService.findAll(getServiceLocator().getCurrentUser().getId());
        if (projects == null) {
            System.out.println("У пользователя нет проектов");
        } else {
            for(AbstractEntity project : projects){
                System.out.println(project);
            }
        }
    }

    @Override
    public boolean isSecure() {
        return true;
    }
}
