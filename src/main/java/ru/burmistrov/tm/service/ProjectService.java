package ru.burmistrov.tm.service;

import ru.burmistrov.tm.entity.Task;
import ru.burmistrov.tm.repository.ProjectRepository;

import java.util.Map;

public class ProjectService {

    private ProjectRepository projectRepository;

    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

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

    public boolean checkContainsProject(String projectId) {
        if (projectRepository == null) {
            projectRepository = new ProjectRepository();
        }
        try {
            Long projectIdLong = Long.valueOf(projectId);
            return projectRepository.checkContainsProject(projectIdLong);
        }
        catch (NumberFormatException e) {
            return false;
        }
    }

    public boolean checkHavingTasks(String projectId) {
        if (projectRepository == null) {
            projectRepository = new ProjectRepository();
        }
        try {
            Long projectIdLong = Long.valueOf(projectId);
            if (projectRepository.checkContainsProject(projectIdLong)) {
                return projectRepository.checkHavingTasks(projectIdLong);
            }
            else {
                return false;
            }
        }
        catch (NumberFormatException e) {
            return false;
        }
    }

    public Map<Long, Task> getProjectTasks(String projectId) {

        try {
        Long projectIdLong = Long.valueOf(projectId);
        if (projectRepository.checkContainsProject(projectIdLong)) {
            return projectRepository.getProjectTasks(projectIdLong);
        }
        else {
            return null;
        }
    }
        catch (NumberFormatException e) {
        return null;
    }

    }
}
