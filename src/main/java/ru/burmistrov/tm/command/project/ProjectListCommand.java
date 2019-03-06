package ru.burmistrov.tm.command.project;

import ru.burmistrov.tm.Bootstrap;
import ru.burmistrov.tm.command.AbstractCommand;
import ru.burmistrov.tm.entity.Project;
import ru.burmistrov.tm.service.ProjectService;

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
        Map<String, Project> projects = projectService.findAll();
        if (projects == null) {
            System.out.println("У пользователя нет проектов");
        } else {
            projectService.findAll().forEach((k, v) -> System.out.println(v));
        }
    }

    @Override
    public boolean isSecure() {
        return true;
    }
}
