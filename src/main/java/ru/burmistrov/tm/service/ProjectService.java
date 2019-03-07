package ru.burmistrov.tm.service;

import ru.burmistrov.tm.api.repository.IProjectRepository;
import ru.burmistrov.tm.api.repository.ITaskRepository;
import ru.burmistrov.tm.api.service.IProjectService;
import ru.burmistrov.tm.entity.AbstractEntity;
import ru.burmistrov.tm.entity.Project;

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

    public Project persist(String userId, String name, String description) {

        Project project = new Project();
        project.setUserId(userId);
        project.setName(name);
        project.setDescription(description);

        for(Map.Entry<String, AbstractEntity> entry : projectRepository.getAbstractEntities().entrySet()){
            if(entry.getValue().equals(project)){
                return null;
            }
        }
        return (Project) projectRepository.persist(project);
    }

    public void merge(String userId, String projectId, String name, String description) {
        for (Map.Entry<String, AbstractEntity> entry : projectRepository.getAbstractEntities().entrySet()) {
            if (entry.getKey().equals(projectId)) {
                Project project = new Project();
                project.setId(projectId);
                project.setUserId(userId);
                project.setName(name);
                project.setDescription(description);
                projectRepository.merge(project);
            }
        }
    }

    public void removeAll(String userId) {
        projectRepository.removeAll(userId);
        taskRepository.removeAll(userId);
    }

    public List<Project> findAll(String userId) {
           return projectRepository.findAll(userId);
    }

}
