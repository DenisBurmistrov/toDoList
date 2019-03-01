package ru.burmistrov.command.project;

import ru.burmistrov.command.AbstractCommand;
import ru.burmistrov.service.ProjectService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ProjectRemoveCommand extends AbstractCommand {

    private ProjectService projectService = new ProjectService();

    @Override
    public String command() {
        return "-deleteById";
    }

    @Override
    public String description() {
        return "Delete project by ID";
    }

    @Override
    public void execute() {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Введите ID проекта: ");
        try {
            String projectId = bufferedReader.readLine();
            System.out.println(projectService.deleteProjectById(projectId));
        } catch (IOException e) {
            System.out.println("Некорректные данные");
        }

    }
}
