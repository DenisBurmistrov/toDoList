package ru.burmistrov.tm.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.burmistrov.tm.api.repository.ITaskRepository;
import ru.burmistrov.tm.api.service.ITaskService;
import ru.burmistrov.tm.entity.AbstractEntity;
import ru.burmistrov.tm.entity.Task;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public final class TaskService implements ITaskService {

    @NotNull
    private final ITaskRepository<AbstractEntity> taskRepository;

    public TaskService(@NotNull ITaskRepository<AbstractEntity> taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Nullable
    public Task createTask(@NotNull String userId, @NotNull String projectId, @NotNull String name, @NotNull String description, @NotNull String dateEndString) throws ParseException {
        @NotNull final Task task = new Task();
        task.setName(name);
        task.setDescription(description);
        task.setProjectId(projectId);
        task.setUserId(userId);
        @NotNull final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy"); //dd-MM-yyyy
        @NotNull final Date dateEnd = simpleDateFormat.parse(dateEndString);
        task.setDateEnd(dateEnd);
        AbstractEntity abstractEntity = taskRepository.findOne(task);
        if(abstractEntity == null)
            return (Task) taskRepository.persist(task);

        return null;
    }

    public void updateTaskById(@NotNull String userId, @NotNull String projectId, @NotNull String taskId, @NotNull String newName, @NotNull String description,
                               @NotNull String dateEndString) throws ParseException {
        @NotNull final Task task = new Task();
        task.setId(taskId);
        task.setName(newName);
        task.setDescription(description);
        task.setProjectId(projectId);
        task.setUserId(userId);
        @NotNull final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy"); //dd-MM-yyyy
        @NotNull final Date dateEnd = simpleDateFormat.parse(dateEndString);
        task.setDateEnd(dateEnd);
        AbstractEntity abstractEntity = taskRepository.findOne(task);
        if (newName.length() != 0 && abstractEntity != null) {
            taskRepository.merge(task);
        }
    }

    @NotNull
    public List<AbstractEntity> findAllTasks(@Nullable String userId) {
        Task task = new Task();
        task.setUserId(userId);
        return taskRepository.findAll(task);
    }

    public void removeAllTasksInProject(@NotNull String userId, @NotNull String projectId) {
        Task task = new Task();
        task.setUserId(userId);
        task.setProjectId(projectId);
        taskRepository.removeAllInProject(task);
    }

    public void removeTaskById(@NotNull String userId, @NotNull String taskId) {
        Task task = new Task();
        task.setProjectId(userId);
        task.setId(taskId);
        taskRepository.remove(task);
    }

    public void removeAllTasks(@Nullable String userId) {
        Task task = new Task();
        task.setUserId(userId);
        taskRepository.removeAll(task);
    }

    @NotNull
    @Override
    public List<AbstractEntity> findAllTasksSortByDateBegin(@Nullable String userId) {
        Task task = new Task();
        task.setUserId(userId);
        return taskRepository.findAllSortByDateBegin(task);
    }

    @NotNull
    @Override
    public List findAllTasksSortByDateEnd(@Nullable String userId) {
        Task task = new Task();
        task.setUserId(userId);
        return taskRepository.findAllSortByDateEnd(task);
    }

    @NotNull
    @Override
    public List findAllTasksSortByStatus(@NotNull String userId) {
        Task task = new Task();
        task.setUserId(userId);
        return taskRepository.findAllSortByStatus(task);
    }

    @Nullable
    @Override
    public AbstractEntity findTaskByName(@NotNull String userId, @NotNull String name) {
        Task task = new Task();
        task.setUserId(userId);
        task.setName(name);
        return taskRepository.findOneByName(task);
    }

    @Nullable
    @Override
    public AbstractEntity findTaskByDescription(@Nullable String userId, String description) {
        Task task = new Task();
        task.setUserId(userId);
        task.setDescription(description);
        return taskRepository.findOneByDescription(task);
    }

    @NotNull
    @Override
    public List findAllTasksInProject(@NotNull String userId, @NotNull String projectId) {
        Task task = new Task();
        task.setUserId(userId);
        task.setProjectId(projectId);
        return taskRepository.findAllInProject(task);
    }
}
