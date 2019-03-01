package ru.burmistrov.service;

import ru.burmistrov.repository.ProjectRepository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ProjectService {

    private ProjectRepository projectRepository;

    public void deleteProjectById(){
        if (projectRepository == null) {
            projectRepository = new ProjectRepository();
        }
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        try {
            System.out.println("Введите");
            Long id = Long.valueOf(bufferedReader.readLine());
            projectRepository.deleteProjectById(id);
        }
        catch (IOException e) {
            System.out.println("Некорректное значение ID");
        }
    }

    public void addProject() {
        if (projectRepository == null) {
            projectRepository = new ProjectRepository();
        }
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Введите имя: ");
            String name = bufferedReader.readLine();
            System.out.println("Введите описание: ");
            String description = bufferedReader.readLine();
            projectRepository.addProject(name, description);
        }
        catch (IOException e) {
            System.out.println("Некорректные данные");
        }

    }

    public void printProjects() {
        if (projectRepository == null) {
            projectRepository = new ProjectRepository();
        }
            projectRepository.printProjects();

    }

    public void clearAll() {
        if (projectRepository == null) {
            projectRepository = new ProjectRepository();
        }
            projectRepository.clearAll();

    }

}
