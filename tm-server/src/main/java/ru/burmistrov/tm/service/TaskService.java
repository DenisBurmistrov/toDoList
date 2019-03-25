package ru.burmistrov.tm.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.burmistrov.tm.api.repository.ITaskRepository;
import ru.burmistrov.tm.api.service.ITaskService;
import ru.burmistrov.tm.entity.AbstractEntity;
import ru.burmistrov.tm.entity.Task;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public final class TaskService implements ITaskService {

    @NotNull
    private final ITaskRepository taskRepository;

    public TaskService(@NotNull final ITaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    @Nullable
    public Task persist(@NotNull final String userId, @NotNull final String projectId, @NotNull final String name,
                        @NotNull final String description, @NotNull final String dateEndString) throws ParseException, IOException, NoSuchAlgorithmException, SQLException {
        @NotNull final Task task = new Task();
        task.setName(name);
        task.setDescription(description);
        task.setProjectId(projectId);
        task.setUserId(userId);
        @NotNull final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy"); //dd-MM-yyyy
        @NotNull final Date dateEnd = simpleDateFormat.parse(dateEndString);
        task.setDateEnd(dateEnd);
        @Nullable final AbstractEntity abstractEntity = taskRepository.findOneByName(task);
        if(abstractEntity == null)
            return taskRepository.persist(task);

        return null;
    }

    @Override
    public void merge(@NotNull final String userId, @NotNull final String projectId, @NotNull final String taskId,
                      @NotNull final String newName, @NotNull final String description, @NotNull final String dateEndString) throws ParseException, SQLException {
        @NotNull final Task task = new Task();
        task.setId(taskId);
        task.setName(newName);
        task.setDescription(description);
        task.setProjectId(projectId);
        task.setUserId(userId);
        @NotNull final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy"); //dd-MM-yyyy
        @NotNull final Date dateEnd = simpleDateFormat.parse(dateEndString);
        task.setDateEnd(dateEnd);
        @Nullable final AbstractEntity abstractEntity = taskRepository.findOne(task);
        if (newName.length() != 0 && abstractEntity != null) {
            taskRepository.merge(task);
        }
    }

    @NotNull
    @Override
    public List<Task> findAll(@Nullable final String userId) throws SQLException {
        Task task = new Task();
        task.setUserId(userId);
        return taskRepository.findAll(task);
    }

    @Override
    public void removeAllInProject(@NotNull final String userId, @NotNull final String projectId) throws SQLException {
        Task task = new Task();
        task.setUserId(userId);
        task.setProjectId(projectId);
        taskRepository.removeAllInProject(task);
    }

    @Override
    public void remove(@NotNull final String userId, @NotNull final String taskId) throws SQLException {
        Task task = new Task();
        task.setProjectId(userId);
        task.setId(taskId);
        taskRepository.remove(task);
    }

    @Override
    public void removeAll(@Nullable final String userId) throws SQLException {
        Task task = new Task();
        task.setUserId(userId);
        taskRepository.removeAll(task);
    }

    @NotNull

    @Override
    public List<Task> findAllSortByDateBegin(@Nullable final String userId) {
        Task task = new Task();
        task.setUserId(userId);
        return taskRepository.findAllSortByDateBegin(task);
    }

    @NotNull
    @Override
    public List<Task> findAllSortByDateEnd(@Nullable final String userId) {
        Task task = new Task();
        task.setUserId(userId);
        return taskRepository.findAllSortByDateEnd(task);
    }

    @NotNull
    @Override
    public List<Task> findAllSortByStatus(@NotNull final String userId) throws SQLException {
        Task task = new Task();
        task.setUserId(userId);
        return taskRepository.findAllSortByStatus(task);
    }

    @Nullable
    @Override
    public Task findOneByName(@NotNull final String userId, @NotNull final String name) throws SQLException {
        Task task = new Task();
        task.setUserId(userId);
        task.setName(name);
        return taskRepository.findOneByName(task);
    }

    @Nullable
    @Override
    public Task findOneByDescription(@Nullable final String userId, @NotNull final String description) throws SQLException {
        Task task = new Task();
        task.setUserId(userId);
        task.setDescription(description);
        return taskRepository.findOneByDescription(task);
    }

    @NotNull
    @Override
    public List<Task> findAllInProject(@NotNull final String userId, @NotNull final String projectId) throws SQLException {
        Task task = new Task();
        task.setUserId(userId);
        task.setProjectId(projectId);
        return taskRepository.findAllInProject(task);
    }
}
