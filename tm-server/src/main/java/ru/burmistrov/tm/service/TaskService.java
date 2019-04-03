package ru.burmistrov.tm.service;

import lombok.NoArgsConstructor;
import org.apache.deltaspike.jpa.api.transaction.Transactional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.burmistrov.tm.repository.ITaskRepository;
import ru.burmistrov.tm.api.service.ITaskService;
import ru.burmistrov.tm.entity.AbstractEntity;
import ru.burmistrov.tm.entity.Task;
import ru.burmistrov.tm.util.DateUtil;

import javax.inject.Inject;
import javax.persistence.NoResultException;
import java.text.ParseException;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import static ru.burmistrov.tm.entity.enumerated.Status.createStatus;

@NoArgsConstructor
@Transactional
public class TaskService implements ITaskService {

    @Inject
    private ITaskRepository taskRepository;

    @Override
    @Nullable
    public Task persist(@NotNull final String userId, @NotNull final String projectId, @NotNull final String name,
                        @NotNull final String description, @NotNull final String dateEndString, @NotNull final String status) throws ParseException {
        try {
            Objects.requireNonNull(taskRepository).findOneByName(userId, name);
        } catch (NoResultException e) {
            @NotNull final Task task = new Task();
            task.setUserId(userId);
            task.setDateBegin(new Date());
            task.setDateEnd(DateUtil.parseDate(dateEndString));
            task.setDescription(description);
            task.setName(name);
            task.setProjectId(projectId);
            task.setStatus(createStatus(status));
            Objects.requireNonNull(taskRepository).persist(task);
            return task;
        }
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
            task.setDateEnd(DateUtil.parseDate(dateEndString));
            @Nullable final AbstractEntity abstractEntity = Objects.requireNonNull(taskRepository).findOne(task.getId(), Objects.requireNonNull(task.getUserId()));
            if (newName.length() != 0 && abstractEntity != null) {
                Objects.requireNonNull(taskRepository).merge(task);
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
        try {
            Task task = taskRepository.findOne(taskId, userId);
            if (task != null) {
                Objects.requireNonNull(taskRepository).remove(task);
            }
        } catch (NoResultException e) {
            e.printStackTrace();
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
        result.stream().filter(e -> Objects.requireNonNull(e.getUserId()).
                equals(userId))
                .forEach(result::add);
        result.sort((s1, s2) -> Integer.compare(0, Objects.requireNonNull(s1.getStatus()).ordinal() - Objects.requireNonNull(s2.getStatus()).ordinal()));
        return result;
    }

    @Nullable
    @Override
    public Task findOneByName(@NotNull final String userId, @NotNull final String name) {
        try {
            return Objects.requireNonNull(taskRepository).findOneByName(userId, name);
        } catch (NoResultException e) {
            return null;
        }
    }

    @Nullable
    @Override
    public Task findOneByDescription(@Nullable final String userId, @NotNull final String description) {
        try {
            return Objects.requireNonNull(taskRepository).findOneByDescription(Objects.requireNonNull(userId), description);
        } catch (NoResultException e) {
            return null;
        }
    }

    @Nullable
    @Override
    public List<Task> findAllInProject(@NotNull final String userId, @NotNull final String projectId) {
        try {
            return Objects.requireNonNull(taskRepository).findAllByProjectId(userId, projectId);
        } catch (NoResultException e) {
            return null;
        }
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
