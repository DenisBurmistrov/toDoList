package ru.burmistrov.tm.service;

import ru.burmistrov.tm.api.repository.IProjectRepository;
import ru.burmistrov.tm.api.repository.ITaskRepository;
import ru.burmistrov.tm.entity.Project;
import ru.burmistrov.tm.entity.User;
import ru.burmistrov.tm.repository.ProjectRepository;
import ru.burmistrov.tm.repository.TaskRepository;

import java.util.List;
import java.util.Map;

public class ProjectService {

    private final ProjectRepository projectRepository;

    private final TaskRepository taskRepository;

    public ProjectService(ProjectRepository projectRepository, TaskRepository taskRepository) {
        this.projectRepository = projectRepository;
        this.taskRepository = taskRepository;
    }

    public void remove(User currentUser, String projectId) {
            projectRepository.remove(currentUser, projectId);
            taskRepository.removeAllinProject(currentUser, projectId);

    }

    public Project persist(User currentUser, String name, String merge) {

        for(Map.Entry<String, Project> entry : projectRepository.getProjects().entrySet()){
            if(entry.getValue().getName().equals(name)){
                return null;
            }
        }
        return projectRepository.persist(currentUser, name, merge);
    }

    public void merge(User currentUser, String projectId, String name, String description) {
            projectRepository.merge(currentUser, projectId, name, description);
    }

    public void removeAll(User currentUser) {
        projectRepository.removeAll(currentUser);
        taskRepository.removeAll(currentUser);
    }

    public List<Project> findAll(User currentUser) {
           return projectRepository.findAll(currentUser);
    }

    public void assignUser(User currentUser, String projectId, String userId) {
        projectRepository.assignExpert(currentUser, projectId, userId);
    }
}
