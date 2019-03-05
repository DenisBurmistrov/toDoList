package ru.burmistrov.tm.service;

import ru.burmistrov.tm.entity.Project;
import ru.burmistrov.tm.repository.ProjectRepository;

import java.util.Map;

public class ProjectService {

    private ProjectRepository projectRepository;

    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public void remove(String projectId) {
        if (projectRepository == null) {
            projectRepository = new ProjectRepository();
        }
            projectRepository.remove(projectId);
    }

    public String persist(String name, String merge) {
        if (projectRepository == null) {
            projectRepository = new ProjectRepository();
        }
        return projectRepository.persist(name, merge);

    }

    public String merge(String projectId, String name, String description) {
        if (projectRepository == null) {
            projectRepository = new ProjectRepository();
        }
            return projectRepository.merge(projectId, name, description);
    }

   /* public void printProjects() {
        if (projectRepository == null) {
            projectRepository = new ProjectRepository();
        }
            projectRepository.printProjects();

    }*/

    public void removeAll() {
        if (projectRepository == null) {
            projectRepository = new ProjectRepository();
        }
        projectRepository.removeAll();
    }

   /* public boolean checkContainsProject(String projectId) {
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
    }*/

    public Map<String, Project> findAll() {
           return projectRepository.findAll();
    }
}
