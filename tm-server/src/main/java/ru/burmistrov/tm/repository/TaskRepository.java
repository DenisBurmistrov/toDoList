package ru.burmistrov.tm.repository;

import lombok.NoArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.burmistrov.tm.api.repository.ITaskRepository;
import ru.burmistrov.tm.entity.Task;
import ru.burmistrov.tm.entity.enumerated.Status;
import ru.burmistrov.tm.mapper.ITaskMapper;

import java.util.*;
import java.util.Date;

@NoArgsConstructor
public final class TaskRepository extends AbstractRepository<Task> implements ITaskRepository {

    @Nullable
    private ITaskMapper taskMapper;

    @Nullable
    private SqlSession session;


    public TaskRepository(@Nullable SqlSession session) {
        this.session = session;
        taskMapper = Objects.requireNonNull(session).getMapper(ITaskMapper.class);
    }

    @NotNull
    @Override
    public List<Task> findAllInProject(@NotNull final String userId, @NotNull final String projectId) {
        return Objects.requireNonNull(taskMapper).findAllByProjectId(userId, projectId);
    }

    @Nullable
    @Override
    public Task findOne(@NotNull final String id, @NotNull final String userId) {
        return Objects.requireNonNull(taskMapper).findOne(id, userId);
    }

    @NotNull
    @Override
    public Task persist(@NotNull final String userId, @NotNull final Date dateBegin,
                        @NotNull final Date dateEnd, @NotNull final String description,
                        @NotNull final String name, @NotNull final String projectId,
                        @NotNull final Status status) {

        @NotNull final Task task = new Task();
        task.setUserId(userId);
        task.setDateBegin(dateBegin);
        task.setDateEnd(dateEnd);
        task.setDescription(description);
        task.setName(name);
        task.setProjectId(projectId);
        task.setStatus(status);
        Objects.requireNonNull(taskMapper).persist(task.getId(), userId, projectId, dateBegin, dateEnd, description, name, status);
        Objects.requireNonNull(session).commit();
        return task;
    }

    @Override
    public void merge(@NotNull Task task) {
        Objects.requireNonNull(taskMapper).merge(task);
        Objects.requireNonNull(session).commit();
    }

    @Override
    public void remove(@NotNull final String id, @NotNull final String userId) {
        Objects.requireNonNull(taskMapper).remove(id, userId);
        Objects.requireNonNull(session).commit();
    }

    @Override
    public void removeAll(@NotNull final String userId) {
        Objects.requireNonNull(taskMapper).removeAll(userId);
        Objects.requireNonNull(session).commit();
    }

    @NotNull
    @Override
    public List<Task> findAll(@NotNull final String userId) {
        return Objects.requireNonNull(taskMapper).findAll(userId);
    }

    @Override
    public void removeAllInProject(@NotNull final String userId, @NotNull final String projectId)  {
        Objects.requireNonNull(taskMapper).removeAllInProject(userId, projectId);
        Objects.requireNonNull(session).commit();
    }

    @NotNull
    @Override
    public List<Task> findAllSortByDateBegin(@NotNull final String userId) {
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
    public List<Task> findAllSortByDateEnd(@NotNull final String userId) {
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
        result.sort((s1, s2) -> Integer.compare(0, s1.getStatus().ordinal() - s2.getStatus().ordinal()));
        return result;
    }

    @Nullable
    @Override
    public Task findOneByName(@NotNull final String userId, @NotNull final String name) {
        return Objects.requireNonNull(taskMapper).findOneByName(userId, name);
    }

    @Nullable
    @Override
    public Task findOneByDescription(@NotNull final String userId, @NotNull final String description) {
        return Objects.requireNonNull(taskMapper).findOneByDescription(userId, description);
    }
}
