package ru.burmistrov.tm.command.project;

import ru.burmistrov.tm.Bootstrap;
import ru.burmistrov.tm.command.AbstractCommand;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ProjectRemoveCommand extends AbstractCommand {

    public ProjectRemoveCommand(Bootstrap bootstrap) {
        super(bootstrap);
    }

    @Override
    public String getName() {
        return "-removeProject";
    }

    @Override
    public String getDescription() {
        return "Delete project by ID";
    }

    @Override
    public void execute() {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Введите ID проекта: ");
        try {
            String projectId = bufferedReader.readLine();
            super.getBootstrap().getProjectService().remove(projectId);
        } catch (IOException e) {
            System.out.println("Некорректные данные");
        }

    }
}
