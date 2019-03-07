package ru.burmistrov.tm.service;

import ru.burmistrov.tm.api.repository.IProjectRepository;
import ru.burmistrov.tm.api.repository.ITaskRepository;
import ru.burmistrov.tm.api.service.IProjectService;
import ru.burmistrov.tm.entity.Project;
import ru.burmistrov.tm.entity.User;

import java.util.List;
import java.util.Map;

public final class ProjectService implements IProjectService {

    private final IProjectRepository projectRepository;

    private final ITaskRepository taskRepository;

    public ProjectService(IProjectRepository projectRepository, ITaskRepository taskRepository) {
        this.projectRepository = projectRepository;
        this.taskRepository = taskRepository;
    }

    public void remove(String userId, String projectId) {
            projectRepository.remove(userId, projectId);
            taskRepository.removeAllInProject(userId, projectId);

    }

    public Project persist(String userId, String name, String merge) {

        for(Map.Entry<String, Project> entry : projectRepository.getProjects().entrySet()){
            if(entry.getValue().getName().equals(name)){
                return null;
            }
        }
        return projectRepository.persist(userId, name, merge);
    }

    public void merge(String userId, String projectId, String name, String description) {
            projectRepository.merge(userId, projectId, name, description);
    }

    public void removeAll(String userId) {
        projectRepository.removeAll(userId);
        taskRepository.removeAll(userId);
    }

    public List<Project> findAll(String userId) {
           return projectRepository.findAll(userId);
    }

    public void assignUser(String currentUserId, String projectId, String userId) {
        projectRepository.assignExpert(currentUserId, projectId, userId);
    }
}
