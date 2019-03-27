package ru.burmistrov.tm.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.burmistrov.tm.api.repository.ITaskRepository;
import ru.burmistrov.tm.api.service.ITaskService;
import ru.burmistrov.tm.entity.AbstractEntity;
import ru.burmistrov.tm.entity.Task;
import ru.burmistrov.tm.entity.enumerated.Status;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public final class TaskService implements ITaskService {

    @NotNull
    private final ITaskRepository taskRepository;

    public TaskService(@NotNull final ITaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    @Nullable
    public Task persist(@NotNull final String userId, @NotNull final String projectId, @NotNull final String name,
                        @NotNull final String description, @NotNull final String dateEndString, @NotNull final String status) throws ParseException {
        @NotNull final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy"); //dd-MM-yyyy
        @NotNull final Date dateEnd = simpleDateFormat.parse(dateEndString);
        @Nullable final AbstractEntity abstractEntity = taskRepository.findOneByName(userId, name);
        if(abstractEntity == null)
            return taskRepository.persist(userId, new Date(), dateEnd, description, name, projectId, Objects.requireNonNull(createStatus(status)));

        return null;
    }

    @Override
    public void merge(@NotNull final String userId, @NotNull final String projectId, @NotNull final String taskId,
                      @NotNull final String newName, @NotNull final String description, @NotNull final String dateEndString,
                      @NotNull final String status) throws ParseException {
        @NotNull final Task task = new Task();
        task.setId(taskId);
        task.setName(newName);
        task.setDescription(description);
        task.setProjectId(projectId);
        task.setUserId(userId);
        task.setStatus(createStatus(status));
        @NotNull final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy"); //dd-MM-yyyy
        @NotNull final Date dateEnd = simpleDateFormat.parse(dateEndString);
        task.setDateEnd(dateEnd);
        @Nullable final AbstractEntity abstractEntity = taskRepository.findOne(task.getId(), Objects.requireNonNull(task.getUserId()));
        if (newName.length() != 0 && abstractEntity != null) {
            taskRepository.merge(task);
        }
    }

    @NotNull
    @Override
    public List<Task> findAll(@Nullable final String userId) throws SQLException {
        return taskRepository.findAll(Objects.requireNonNull(userId));
    }

    @Override
    public void removeAllInProject(@NotNull final String userId, @NotNull final String projectId) throws SQLException {
        taskRepository.removeAllInProject(userId, projectId);
    }

    @Override
    public void remove(@NotNull final String userId, @NotNull final String taskId) throws SQLException {
        taskRepository.remove(taskId, userId);
    }

    @Override
    public void removeAll(@Nullable final String userId) throws SQLException {
        taskRepository.removeAll(Objects.requireNonNull(userId));
    }

    @NotNull

    @Override
    public List<Task> findAllSortByDateBegin(@Nullable final String userId) throws SQLException {
        return taskRepository.findAllSortByDateBegin(Objects.requireNonNull(userId));
    }

    @NotNull
    @Override
    public List<Task> findAllSortByDateEnd(@Nullable final String userId) throws SQLException {
        return taskRepository.findAllSortByDateEnd(Objects.requireNonNull(userId));
    }

    @NotNull
    @Override
    public List<Task> findAllSortByStatus(@NotNull final String userId) throws SQLException {
        return taskRepository.findAllSortByStatus(userId);
    }

    @Nullable
    @Override
    public Task findOneByName(@NotNull final String userId, @NotNull final String name) throws SQLException {
        return taskRepository.findOneByName(userId, name);
    }

    @Nullable
    @Override
    public Task findOneByDescription(@Nullable final String userId, @NotNull final String description) throws SQLException {
        return taskRepository.findOneByDescription(Objects.requireNonNull(userId), description);
    }

    @NotNull
    @Override
    public List<Task> findAllInProject(@NotNull final String userId, @NotNull final String projectId) throws SQLException {
        return taskRepository.findAllInProject(userId, projectId);
    }

    @Nullable
    private Status createStatus(String string) {
        switch (string) {
            case "Запланировано":
                return Status.SCHEDULED;
            case "В процессе":
                return Status.IN_PROCESS;
            case "Готово":
                return Status.COMPLETE;
        }
        return null;
    }
}
