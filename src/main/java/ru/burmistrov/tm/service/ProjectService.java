package ru.burmistrov.tm.service;

import ru.burmistrov.tm.entity.Task;
import ru.burmistrov.tm.repository.ProjectRepository;

import java.util.Map;

public class ProjectService {

    private ProjectRepository projectRepository;

    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public String remove(String projectId) {
        if (projectRepository == null) {
            projectRepository = new ProjectRepository();
        }

        try {
            Long id = Long.valueOf(projectId);
            return projectRepository.remove(id);
        } catch (NumberFormatException e) {
            return "Некорректное значение ID";
        }

    }

    public String merge(String name, String merge) {
        if (projectRepository == null) {
            projectRepository = new ProjectRepository();
        }
        return projectRepository.merge(name, merge);

    }

    public String persist(String projectId, String name, String description) {
        if (projectRepository == null) {
            projectRepository = new ProjectRepository();
        }
        try {

            Long projectIdLong = Long.valueOf(projectId);
            return projectRepository.updateProject(projectIdLong, name, description);
        } catch (NumberFormatException e) {
            return "Некорректное значение ID";
        }
    }

   /* public void printProjects() {
        if (projectRepository == null) {
            projectRepository = new ProjectRepository();
        }
            projectRepository.printProjects();

    }*/

    public String removeAll() {
        if (projectRepository == null) {
            projectRepository = new ProjectRepository();
        }
        return projectRepository.removeAll();
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

    public String findAll(String projectId) {

        try {
            Long projectIdLong = Long.valueOf(projectId);
            projectRepository.findAll(projectIdLong);
            return "";
        } catch (NumberFormatException e) {
            return "";
        }
    }
}
