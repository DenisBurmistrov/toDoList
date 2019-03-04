package ru.burmistrov.tm.command.project;

import ru.burmistrov.tm.Bootstrap;
import ru.burmistrov.tm.command.AbstractCommand;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ProjectUpdateCommand extends AbstractCommand {


    public ProjectUpdateCommand(Bootstrap bootstrap) {
        super(bootstrap);
    }

    @Override
    public String command() {
        return "-merge";
    }

    @Override
    public String description() {
        return "Update project by Id";
    }

    @Override
    public void execute() {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        try {
            System.out.println("Введите ID проекта:");
            String projectId = bufferedReader.readLine();
            System.out.println("Введите новое название проекта:");
            String name = bufferedReader.readLine();
            System.out.println("Введите новое описание:");
            String description = bufferedReader.readLine();
            System.out.println(super.getBootstrap().getProjectService().persist(projectId, name, description));
        } catch (IOException e) {
            System.out.println("Некорректные параметры");
        }

    }
}
