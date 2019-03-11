package ru.burmistrov.tm.service;

import ru.burmistrov.tm.api.repository.IProjectRepository;
import ru.burmistrov.tm.api.repository.ITaskRepository;
import ru.burmistrov.tm.api.service.IProjectService;
import ru.burmistrov.tm.entity.AbstractEntity;
import ru.burmistrov.tm.entity.Project;
import ru.burmistrov.tm.entity.Task;

import java.util.List;

public final class ProjectService extends AbstractService implements IProjectService {

    private final IProjectRepository<AbstractEntity> projectRepository;

    private final ITaskRepository<AbstractEntity> taskRepository;

    public ProjectService(IProjectRepository<AbstractEntity> projectRepository, ITaskRepository<AbstractEntity> taskRepository) {
        this.projectRepository = projectRepository;
        this.taskRepository = taskRepository;
    }

    public void remove(String userId, String projectId) {
            Project project = new Project();
            project.setUserId(userId);
            project.setId(projectId);
            AbstractEntity abstractEntity = projectRepository.findOne(project);
            if(abstractEntity != null) {
                projectRepository.remove(project);
                Task task = new Task();
                task.setUserId(userId);
                task.setProjectId(projectId);
                taskRepository.removeAllInProject(task);
            }

    }

    public Project persist(String userId, String name, String description) {

        Project project = new Project();
        project.setUserId(userId);
        project.setName(name);
        project.setDescription(description);
        AbstractEntity abstractEntity = projectRepository.findOne(project);
        if(abstractEntity == null) {
            return (Project) projectRepository.persist(project);
        }
        return null;
    }

    public void merge(String userId, String projectId, String name, String description) {
        Project project = new Project();
        project.setId(projectId);
        project.setUserId(userId);
        project.setName(name);
        project.setDescription(description);
        AbstractEntity abstractEntity = projectRepository.findOne(project);
        if (abstractEntity != null) {
            projectRepository.merge(project);
        }
    }

    public void removeAll(String userId) {
        Project project = new Project();
        project.setUserId(userId);
        projectRepository.removeAll(project);
        taskRepository.removeAll(project);
    }

    public List<AbstractEntity> findAll(String userId) {
        Project project = new Project();
        project.setUserId(userId);
        return projectRepository.findAll(project);
    }

}
