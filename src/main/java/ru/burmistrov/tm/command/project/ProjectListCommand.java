package ru.burmistrov.tm.command.project;

import ru.burmistrov.tm.Bootstrap;
import ru.burmistrov.tm.command.AbstractCommand;

public class ProjectListCommand extends AbstractCommand {


    public ProjectListCommand(Bootstrap bootstrap) {
        super(bootstrap);
    }

    @Override
    public String command() {
        return "-print";
    }

    @Override
    public String description() {
        return "Print all projects";
    }

    @Override
    public void execute() {
        System.out.println("Список проектов:");
        super.getBootstrap().getProjects().forEach((k, v) -> System.out.println(v));
    }
}
