package ru.burmistrov.tm.service;

import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.burmistrov.tm.repository.ITaskRepository;
import ru.burmistrov.tm.api.service.ITaskService;
import ru.burmistrov.tm.entity.AbstractEntity;
import ru.burmistrov.tm.entity.Task;
import ru.burmistrov.tm.util.DateUtil;

import javax.persistence.NoResultException;
import java.text.ParseException;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import static ru.burmistrov.tm.entity.enumerated.Status.createStatus;

@Transactional
@NoArgsConstructor
@Component
public class TaskService implements ITaskService {

    @Autowired
    private ITaskRepository taskRepository;

    @Nullable
    @Override
    public Task persist(@NotNull final String userId, @NotNull final String projectId, @NotNull final String name,
                        @NotNull final String description, @NotNull final String dateEndString, @NotNull final String status) throws ParseException {
            Objects.requireNonNull(taskRepository).findOneByName(userId, name);
            @NotNull final Task task = new Task();
            task.setUserId(userId);
            task.setDateBegin(new Date());
            task.setDateEnd(DateUtil.parseDate(dateEndString));
            task.setDescription(description);
            task.setName(name);
            task.setProjectId(projectId);
            task.setStatus(createStatus(status));
            Objects.requireNonNull(taskRepository).save(task);
            return task;
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
            task.setDateEnd(DateUtil.parseDate(dateEndString));
            @Nullable final AbstractEntity abstractEntity = Objects.requireNonNull(taskRepository).findOne(task.getId(), Objects.requireNonNull(task.getUserId()));
            if (newName.length() != 0 && abstractEntity != null) {
                Objects.requireNonNull(taskRepository).save(task);
            }
        }

    @NotNull
    @Override
    public List<Task> findAll(@Nullable final String userId) {
        return Objects.requireNonNull(taskRepository).findAll(Objects.requireNonNull(userId));
    }

    @Override
    public void removeAllInProject(@NotNull final String userId, @NotNull final String projectId) {
            Objects.requireNonNull(taskRepository).removeAllInProject(userId, projectId);
    }

    @Override
    public void remove(@NotNull final String userId, @NotNull final String taskId) {
        Task task = taskRepository.findOne(taskId, userId);
        if (task != null) {
            Objects.requireNonNull(taskRepository).delete(task);
        }
    }

    @Override
    public void removeAll(@Nullable final String userId) {
        Objects.requireNonNull(taskRepository).removeAll(Objects.requireNonNull(userId));
    }

    @NotNull
    @Override
    public List<Task> findAllSortByDateBegin(@Nullable final String userId) {
        @NotNull final List<Task> result = Objects.requireNonNull(findAll(userId));
        result.sort(Comparator.comparingLong(s -> s.getDateBegin().getTime()));
        return result;
    }

    @NotNull
    @Override
    public List<Task> findAllSortByDateEnd(@Nullable final String userId) {
        @NotNull final List<Task> result = Objects.requireNonNull(findAll(userId));
        result.sort((s1, s2) -> Long.compare(s2.getDateBegin().getTime(), s1.getDateBegin().getTime()));
        return result;
    }

    @NotNull
    @Override
    public List<Task> findAllSortByStatus(@NotNull final String userId) {
        @NotNull final List<Task> result = Objects.requireNonNull(findAll(userId));
        result.sort((s1, s2) -> Integer.compare(0, Objects.requireNonNull(s1.getStatus()).ordinal() - Objects.requireNonNull(s2.getStatus()).ordinal()));
        return result;
    }

    @Nullable
    @Override
    public Task findOneByName(@NotNull final String userId, @NotNull final String name) {
            return Objects.requireNonNull(taskRepository).findOneByName(userId, name);
    }

    @Nullable
    @Override
    public Task findOneByDescription(@Nullable final String userId, @NotNull final String description) {
            return Objects.requireNonNull(taskRepository).findOneByDescription(Objects.requireNonNull(userId), description);
    }

    @Nullable
    @Override
    public List<Task> findAllInProject(@NotNull final String userId, @NotNull final String projectId) {
            return Objects.requireNonNull(taskRepository).findAllByProjectId(userId, projectId);
    }

    @Nullable
    public Task findOne(@NotNull final String id, @NotNull final String userId) {
        try {
            return Objects.requireNonNull(taskRepository).findOne(id, userId);
        } catch (NoResultException e) {
            return null;
        }
    }
}
