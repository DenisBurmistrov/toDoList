package ru.burmistrov.service;

import ru.burmistrov.repository.ProjectRepository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ProjectService {

    private ProjectRepository projectRepository;

    public String deleteProjectById(String projectId){
        if (projectRepository == null) {
            projectRepository = new ProjectRepository();
        }

        try {
            Long id = Long.valueOf(projectId);
            return projectRepository.deleteProjectById(id);
        }
        catch (NumberFormatException e) {
            return "Некорректное значение ID";
        }

    }

    public String addProject(String name, String descriprion) {
        if (projectRepository == null) {
            projectRepository = new ProjectRepository();
        }
            return projectRepository.addProject(name, descriprion);

    }

   /* public void printProjects() {
        if (projectRepository == null) {
            projectRepository = new ProjectRepository();
        }
            projectRepository.printProjects();

    }*/

    public String  clearAll() {
        if (projectRepository == null) {
            projectRepository = new ProjectRepository();
        }
           return projectRepository.clearAll();

    }

}
