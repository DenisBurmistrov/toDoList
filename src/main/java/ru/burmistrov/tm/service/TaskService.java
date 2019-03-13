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

public final class TaskService extends AbstractService implements ITaskService {

    @Nullable
    private final ITaskRepository<AbstractEntity> taskRepository;

    public TaskService(@Nullable ITaskRepository<AbstractEntity> taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Nullable
    public Task persist(@Nullable String userId, @Nullable String projectId, @Nullable String name, @Nullable String description, @Nullable String dateEndString) throws ParseException {
        Task task = new Task();
        task.setName(name);
        task.setDescription(description);
        task.setProjectId(projectId);
        task.setUserId(userId);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy"); //dd-MM-yyyy
        Date dateEnd = simpleDateFormat.parse(dateEndString);
        task.setDateEnd(dateEnd);
        AbstractEntity abstractEntity = taskRepository.findOne(task);
        return (Task) taskRepository.persist(abstractEntity);
    }

    public void merge(@Nullable String userId, @Nullable String projectId, @Nullable String taskId, @Nullable String newName, @Nullable String description, @Nullable String dateEndString) throws ParseException {
        Task task = new Task();
        task.setId(taskId);
        task.setName(newName);
        task.setDescription(description);
        task.setProjectId(projectId);
        task.setUserId(userId);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy"); //dd-MM-yyyy
        Date dateEnd = simpleDateFormat.parse(dateEndString);
        task.setDateEnd(dateEnd);
        AbstractEntity abstractEntity = taskRepository.findOne(task);
        if (newName.length() != 0) {
            taskRepository.merge(abstractEntity);
        }
    }

    @Nullable
    public List<AbstractEntity> findAll(@Nullable String userId, @Nullable String projectId) {
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

    public void removeAll(@Nullable String userId) {
        Task task = new Task();
        task.setUserId(userId);
        taskRepository.removeAll(task);
    }

    @Override
    public List<AbstractEntity> findAllSortByDateBegin(@Nullable String userId) {
        Task task = new Task();
        task.setUserId(userId);
        return taskRepository.findAllSortByDateBegin(task);
    }

    @NotNull
    @Override
    public List findAllSortByDateEnd(@Nullable String userId) {
        Task task = new Task();
        task.setUserId(userId);
        return taskRepository.findAllSortByDateEnd(task);
    }

    @NotNull
    @Override
    public List findAllSortByStatus(@NotNull String userId) {
        Task task = new Task();
        task.setUserId(userId);
        return taskRepository.findAllSortByStatus(task);
    }

    @Nullable
    @Override
    public AbstractEntity findOneByName(@Nullable String userId, String name) {
        Task task = new Task();
        task.setUserId(userId);
        task.setName(name);
        return taskRepository.findOneByName(task);
    }

    @Nullable
    @Override
    public AbstractEntity findOneByDescription(@Nullable String userId, String description) {
        Task task = new Task();
        task.setUserId(userId);
        task.setDescription(description);
        return taskRepository.findOneByDescription(task);
    }
}
