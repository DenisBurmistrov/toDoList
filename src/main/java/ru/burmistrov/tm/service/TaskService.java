package ru.burmistrov.tm.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.burmistrov.tm.api.repository.ITaskRepository;
import ru.burmistrov.tm.api.service.ITaskService;
import ru.burmistrov.tm.entity.AbstractEntity;
import ru.burmistrov.tm.entity.Task;

import java.util.List;

public final class TaskService extends AbstractService implements ITaskService {

    @Nullable private final ITaskRepository<AbstractEntity> taskRepository;

    public TaskService(@Nullable ITaskRepository<AbstractEntity> taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Nullable
    public Task persist(@Nullable String userId,@Nullable String projectId,@Nullable String name,@Nullable String description) {
        Task task = new Task();
        task.setName(name);
        task.setDescription(description);
        task.setProjectId(projectId);
        task.setUserId(userId);
        if(taskRepository != null) {
            AbstractEntity abstractEntity = taskRepository.findOne(task);
            if (name != null && name.length() != 0 && abstractEntity == null) {
                return (Task) taskRepository.persist(task);
            }
        }
        return null;
    }

    public void merge(@Nullable String userId, @Nullable String projectId,@Nullable String taskId,@Nullable String newName,@Nullable String description) {
        Task task = new Task();
        task.setId(taskId);
        task.setName(newName);
        task.setDescription(description);
        task.setProjectId(projectId);
        task.setUserId(userId);
        if (taskRepository != null) {
            AbstractEntity abstractEntity = taskRepository.findOne(task);
            if (newName != null && newName.length() != 0 && abstractEntity != null) {
                taskRepository.merge(task);
            }
        }
    }

    @Nullable
    public List<AbstractEntity> findAll(@Nullable String userId,@Nullable String projectId) {
        Task task = new Task();
        task.setUserId(userId);
        task.setProjectId(projectId);
        if (taskRepository != null) {
            return taskRepository.findAll(task);
        }
        return null;
    }

    public void removeAllInProject(String userId, String projectId) {
        Task task = new Task();
        task.setUserId(userId);
        task.setProjectId(projectId);
        if (taskRepository != null) {
            taskRepository.removeAllInProject(task);
        }
    }

    public void remove(String userId, String projectId, String taskId) {
        Task task = new Task();
        task.setProjectId(userId);
        task.setProjectId(projectId);
        task.setId(taskId);
        if (taskRepository != null) {
            taskRepository.remove(task);
        }
    }

    public void removeAll(@Nullable String userId) {
        Task task = new Task();
        task.setUserId(userId);
        if (taskRepository != null) {
            taskRepository.removeAll(task);
        }
    }
}
