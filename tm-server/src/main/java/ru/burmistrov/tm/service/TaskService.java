package ru.burmistrov.tm.service;

import org.apache.ibatis.session.SqlSession;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.burmistrov.tm.api.repository.ITaskRepository;
import ru.burmistrov.tm.api.service.ITaskService;
import ru.burmistrov.tm.entity.AbstractEntity;
import ru.burmistrov.tm.entity.Task;
import ru.burmistrov.tm.entity.enumerated.Status;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public final class TaskService implements ITaskService {

    @NotNull
    private final ITaskRepository taskRepository;

    @NotNull
    private final SqlSession session;

    public TaskService(@NotNull final SqlSession session) {
        this.session = session;
        this.taskRepository = session.getMapper(ITaskRepository.class);
    }

    @Override
    @Nullable
    public Task persist(@NotNull final String userId, @NotNull final String projectId, @NotNull final String name,
                        @NotNull final String description, @NotNull final String dateEndString, @NotNull final String status) {
        try {
            @NotNull final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");
            @NotNull final Date dateEnd = simpleDateFormat.parse(dateEndString);
            @Nullable final AbstractEntity abstractEntity = taskRepository.findOneByName(userId, name);
            if (abstractEntity == null) {
                @NotNull final Task task = new Task();
                task.setUserId(userId);
                task.setDateBegin(new Date());
                task.setDateEnd(dateEnd);
                task.setDescription(description);
                task.setName(name);
                task.setProjectId(projectId);
                task.setStatus(createStatus(status));
                Objects.requireNonNull(taskRepository).persist(task.getId(), Objects.requireNonNull(task.getUserId()), Objects.requireNonNull(task.getProjectId()),
                        task.getDateBegin(), Objects.requireNonNull(task.getDateEnd()), Objects.requireNonNull(task.getDescription()),
                        Objects.requireNonNull(task.getName()), Objects.requireNonNull(task.getStatus()));
                Objects.requireNonNull(session).commit();
                return task;
            }
        } catch (Exception e) {
            session.rollback();
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
            @NotNull final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy"); //dd-MM-yyyy
            @NotNull final Date dateEnd = simpleDateFormat.parse(dateEndString);
            task.setDateEnd(dateEnd);
            @Nullable final AbstractEntity abstractEntity = taskRepository.findOne(task.getId(), Objects.requireNonNull(task.getUserId()));
            if (newName.length() != 0 && abstractEntity != null) {
                Objects.requireNonNull(taskRepository).merge(task);
                Objects.requireNonNull(session).commit();
            }
        } catch (Exception e) {
            session.rollback();
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
            Objects.requireNonNull(taskRepository).removeAllInProject(userId, projectId);
            Objects.requireNonNull(session).commit();
        } catch (Exception e) {
            session.rollback();
        }
    }

    @Override
    public void remove(@NotNull final String userId, @NotNull final String taskId) {
        try {
            Objects.requireNonNull(taskRepository).remove(taskId, userId);
            Objects.requireNonNull(session).commit();
        } catch (Exception e) {
            session.rollback();
        }
    }

    @Override
    public void removeAll(@Nullable final String userId) {
        try {
            Objects.requireNonNull(taskRepository).removeAll(Objects.requireNonNull(userId));
            Objects.requireNonNull(session).commit();
        } catch (Exception e) {
            session.rollback();
        }
    }

    @NotNull

    @Override
    public List<Task> findAllSortByDateBegin(@Nullable final String userId) {
        @NotNull final List<Task> result = findAll(userId);
        result.sort((s1, s2) -> {
            @NotNull final boolean firstDateMoreThanSecond = s1.getDateBegin().getTime() - s2.getDateBegin().getTime() < 0;
            @NotNull final boolean secondDateMoreThaFirst = s1.getDateBegin().getTime() - s2.getDateBegin().getTime() > 0;

            if (firstDateMoreThanSecond) {
                return 1;
            } else if (secondDateMoreThaFirst) {
                return -1;
            } else {
                return 0;
            }
        });
        return result;
    }

    @NotNull
    @Override
    public List<Task> findAllSortByDateEnd(@Nullable final String userId) {
        @NotNull final List<Task> result = findAll(userId);
        result.sort((s1, s2) -> {
            boolean firstDateMoreThanSecond = Objects.requireNonNull(s1.getDateEnd()).getTime() - Objects.requireNonNull(s2.getDateEnd()).getTime() > 0;
            boolean secondDateMoreThanFirst = Objects.requireNonNull(s1.getDateEnd()).getTime() - Objects.requireNonNull(s2.getDateEnd()).getTime() < 0;

            if (firstDateMoreThanSecond) {
                return 1;
            } else if (secondDateMoreThanFirst) {
                return -1;
            } else {
                return 0;
            }
        });
        return result;
    }

    @NotNull
    @Override
    public List<Task> findAllSortByStatus(@NotNull final String userId) {
        @NotNull final List<Task> result = findAll(userId);
        result.stream().filter(e -> Objects.requireNonNull(e.getUserId()).
                equals(userId))
                .forEach(result::add);
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
        assert userId != null;
        return Objects.requireNonNull(taskRepository).findOneByDescription(userId, description);
    }

    @NotNull
    @Override
    public List<Task> findAllInProject(@NotNull final String userId, @NotNull final String projectId) {
        return Objects.requireNonNull(taskRepository).findAllByProjectId(userId, projectId);
    }

    @Nullable
    public Task findOne(@NotNull final String id, @NotNull final String userId) {
        return Objects.requireNonNull(taskRepository).findOne(id, userId);
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
