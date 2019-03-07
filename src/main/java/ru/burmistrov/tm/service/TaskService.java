package ru.burmistrov.tm.service;

import ru.burmistrov.tm.api.repository.IProjectRepository;
import ru.burmistrov.tm.api.repository.ITaskRepository;
import ru.burmistrov.tm.api.service.ITaskService;
import ru.burmistrov.tm.entity.Task;
import ru.burmistrov.tm.entity.User;

import java.util.List;

public final class TaskService implements ITaskService {

    private final ITaskRepository taskRepository;

    private final IProjectRepository projectRepository;

    public TaskService(ITaskRepository taskRepository, IProjectRepository projectRepository) {
        this.taskRepository = taskRepository;
        this.projectRepository = projectRepository;
    }

    public void merge(String userId, String projectId, String oldName, String newName, String description) {
            if(newName.length() != 0) {
                taskRepository.merge(userId, projectId, oldName, newName, description);
            }
    }

    public Task persist(String userId, String projectId, String name, String description) {
            if(name.length() != 0 && projectRepository.getProjects().containsKey(projectId)) {
                return taskRepository.persist(userId, projectId, name, description);
            }
            return null;
    }

    public List<Task> findAll(String userId, String projectId) {
        return taskRepository.findAll(userId, projectId);
    }

    public void removeAllInProject(String userId, String projectId) {
        taskRepository.removeAllInProject(userId, projectId);
    }

    public void remove(String userId, String projectId, String taskId) {
        taskRepository.remove(userId, projectId, taskId);
    }

    public void removeAll(String userId) {
        taskRepository.removeAll(userId);
    }
}
