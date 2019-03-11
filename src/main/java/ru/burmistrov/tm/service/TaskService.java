package ru.burmistrov.tm.service;

import ru.burmistrov.tm.api.repository.ITaskRepository;
import ru.burmistrov.tm.api.service.ITaskService;
import ru.burmistrov.tm.entity.AbstractEntity;
import ru.burmistrov.tm.entity.Task;

import java.util.List;

public final class TaskService extends AbstractService implements ITaskService {

    private final ITaskRepository<AbstractEntity> taskRepository;

    public TaskService(ITaskRepository<AbstractEntity> taskRepository) {
        this.taskRepository = taskRepository;
    }

    public Task persist(String userId, String projectId, String name, String description) {
        Task task = new Task();
        task.setName(name);
        task.setDescription(description);
        task.setProjectId(projectId);
        task.setUserId(userId);
        AbstractEntity abstractEntity = taskRepository.findOne(task);
        if(name.length() != 0 && abstractEntity == null) {

            return (Task) taskRepository.persist(task);
        }
        return null;
    }

    public void merge(String userId, String projectId, String taskId, String newName, String description) {
        Task task = new Task();
        task.setId(taskId);
        task.setName(newName);
        task.setDescription(description);
        task.setProjectId(projectId);
        task.setUserId(userId);
        AbstractEntity abstractEntity = taskRepository.findOne(task);

        if (newName.length() != 0 && abstractEntity != null) {
            taskRepository.merge(task);
        }
    }

    public List<AbstractEntity> findAll(String userId, String projectId) {
        Task task = new Task();
        task.setUserId(userId);
        task.setProjectId(projectId);
        return taskRepository.findAll(task);
    }

    public void removeAllInProject(String userId, String projectId) {
        Task task = new Task();
        task.setUserId(userId);
        task.setProjectId(projectId);
        taskRepository.removeAllInProject(task);
    }

    public void remove(String userId, String projectId, String taskId) {
        Task task = new Task();
        task.setProjectId(userId);
        task.setProjectId(projectId);
        task.setId(taskId);
        taskRepository.remove(task);
    }

    public void removeAll(String userId) {
        Task task = new Task();
        task.setUserId(userId);
        taskRepository.removeAll(task);
    }
}
