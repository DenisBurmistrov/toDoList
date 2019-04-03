package ru.burmistrov.tm.service;

import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.burmistrov.tm.api.repository.ITaskRepository;
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
public final class TaskService implements ITaskService {

    @Inject
    private ITaskRepository taskRepository;

    @Override
    @Nullable
    public Task persist(@NotNull final String userId, @NotNull final String projectId, @NotNull final String name,
                        @NotNull final String description, @NotNull final String dateEndString, @NotNull final String status) {
        @Nullable AbstractEntity abstractEntity;
        try {
            abstractEntity = Objects.requireNonNull(taskRepository).findOneByName(userId, name);
        } catch (NoResultException e) {
            abstractEntity = null;
        }
        try {
            if (abstractEntity == null) {
                @NotNull final Task task = new Task();
                task.setUserId(userId);
                task.setDateBegin(new Date());
                task.setDateEnd(DateUtil.parseDate(dateEndString));
                task.setDescription(description);
                task.setName(name);
                task.setProjectId(projectId);
                task.setStatus(createStatus(status));

                taskRepository.getEntityManager().getTransaction().begin();
                Objects.requireNonNull(taskRepository).persist(task);
                taskRepository.getEntityManager().getTransaction().commit();
                return task;
            }
        } catch (Exception e) {
            taskRepository.getEntityManager().getTransaction().rollback();
        }
        return null;
    }

    @Override
    public void merge(@NotNull final String userId, @NotNull final String projectId, @NotNull final String taskId,
                      @NotNull final String newName, @NotNull final String description, @NotNull final String dateEndString,
                      @NotNull final String status) {
        try {
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
                taskRepository.getEntityManager().getTransaction().begin();
                Objects.requireNonNull(taskRepository).merge(task);
                taskRepository.getEntityManager().getTransaction().commit();
            }
        } catch (Exception e) {
            taskRepository.getEntityManager().getTransaction().rollback();
        }
    }

    @NotNull
    @Override
    public List<Task> findAll(@Nullable final String userId) {
        return Objects.requireNonNull(taskRepository).findAll(Objects.requireNonNull(userId));
    }

    @Override
    public void removeAllInProject(@NotNull final String userId, @NotNull final String projectId) {
        try {
            taskRepository.getEntityManager().getTransaction().begin();
            Objects.requireNonNull(taskRepository).removeAllInProject(userId, projectId);
            taskRepository.getEntityManager().getTransaction().commit();
        } catch (Exception e) {
            taskRepository.getEntityManager().getTransaction().rollback();
        }
    }

    @Override
    public void remove(@NotNull final String userId, @NotNull final String taskId) {
        Task task = taskRepository.findOne(taskId, userId);
        if (task != null) {
            try {
                taskRepository.getEntityManager().getTransaction().begin();
                Objects.requireNonNull(taskRepository).remove(task);
                taskRepository.getEntityManager().getTransaction().commit();
            } catch (Exception e) {
                taskRepository.getEntityManager().getTransaction().rollback();
            }
        }
    }

    @Override
    public void removeAll(@Nullable final String userId) {
        try {
            taskRepository.getEntityManager().getTransaction().begin();
            Objects.requireNonNull(taskRepository).removeAll(Objects.requireNonNull(userId));
            taskRepository.getEntityManager().getTransaction().commit();
        } catch (Exception e) {
            taskRepository.getEntityManager().getTransaction().rollback();
        }
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
