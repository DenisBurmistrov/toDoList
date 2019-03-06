package ru.burmistrov.tm.service;

import ru.burmistrov.tm.entity.Task;
import ru.burmistrov.tm.entity.User;
import ru.burmistrov.tm.repository.ProjectRepository;
import ru.burmistrov.tm.repository.TaskRepository;

import java.util.List;

public class TaskService {

    private final TaskRepository taskRepository;

    private final ProjectRepository projectRepository;

    public TaskService(TaskRepository taskRepository, ProjectRepository projectRepository) {
        this.taskRepository = taskRepository;
        this.projectRepository = projectRepository;
    }

    public void merge(User currentUser, String projectId, String oldName, String newName, String description) {
            if(newName.length() != 0) {
                taskRepository.merge(currentUser, projectId, oldName, newName, description);
            }
    }

    public Task persist(User currentUser, String projectId, String name, String description) {
            if(name.length() != 0 && projectRepository.getProjects().containsKey(projectId)) {
                return taskRepository.persist(currentUser, projectId, name, description);
            }
            return null;
    }

    public List<Task> findAll(User currentUser, String projectId) {
        return taskRepository.findAll(currentUser, projectId);
    }

    public void removeAllInProject(User currentUser, String projectId) {
        taskRepository.removeAllinProject(currentUser, projectId);
    }

    public void remove(User currentUser, String projectId, String taskId) {
        taskRepository.remove(currentUser, projectId, taskId);
    }
}
