package ru.burmistrov.tm.command.project;

import ru.burmistrov.tm.Bootstrap;
import ru.burmistrov.tm.command.AbstractCommand;
import ru.burmistrov.tm.entity.Project;
import ru.burmistrov.tm.service.ProjectService;

import java.util.List;
import java.util.Map;

public class ProjectListCommand extends AbstractCommand {

    private final ProjectService projectService = getBootstrap().getProjectService();

    public ProjectListCommand(Bootstrap bootstrap) {
        super(bootstrap);
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
        List<Project> projects = projectService.findAll(getBootstrap().getCurrentUser());
        if (projects == null) {
            System.out.println("У пользователя нет проектов");
        } else {
            for(Project project : projects){
                System.out.println(project);
            }
        }
    }

    @Override
    public boolean isSecure() {
        return true;
    }
}
