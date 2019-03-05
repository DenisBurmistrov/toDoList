package ru.burmistrov.tm.service;

import ru.burmistrov.tm.entity.Project;
import ru.burmistrov.tm.repository.ProjectRepository;
import java.util.Map;

public class ProjectService {

    private final ProjectRepository projectRepository;

    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public void remove(String projectId) {
            projectRepository.remove(projectId);
    }

    public String persist(String name, String merge) {
        return projectRepository.persist(name, merge);
    }

    public String merge(String projectId, String name, String description) {
            return projectRepository.merge(projectId, name, description);
    }

    public void removeAll() {
        projectRepository.removeAll();
    }

    public Map<String, Project> findAll() {
           return projectRepository.findAll();
    }

    public void assignUser(String projectId, String userId) {
        projectRepository.assignExpert(projectId, userId);
    }
}
