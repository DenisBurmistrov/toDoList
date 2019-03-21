package ru.burmistrov.tm.repository;

import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.burmistrov.tm.api.repository.ITaskRepository;
import ru.burmistrov.tm.entity.Task;

import java.util.*;

@NoArgsConstructor
public final class TaskRepository extends AbstractRepository<Task> implements ITaskRepository {

    @NotNull
    @Override
    public List<Task> findAllInProject(@NotNull final Task entity) {
        @NotNull final Task task = entity;
        @NotNull final List<Task> result = new LinkedList<>();
        map.entrySet()
                .stream().filter(e -> Objects.requireNonNull(e.getValue().
                getProjectId()).equals(task.getProjectId())
                && Objects.requireNonNull(e.getValue().getUserId()).
                equals(task.getUserId()))
                .forEach(e -> result.add(e.getValue()));
        return result;
    }

    @Override
    public void removeAllInProject(@NotNull final Task entity) {
        @NotNull final Task task = entity;
        map.entrySet().removeIf((e) -> Objects.requireNonNull(e.getValue().getProjectId()).equals(task.getProjectId()) &&
                Objects.requireNonNull(task.getUserId()).equals(e.getValue().getUserId()));
    }

    @NotNull
    @Override
    public List<Task> findAllSortByDateBegin(@NotNull final Task abstractEntity) {
        @NotNull final Task task = abstractEntity;
        @NotNull final List<Task> result = new LinkedList<>();
        map.entrySet()
                .stream().filter(e -> Objects.requireNonNull(e.getValue().getUserId()).
                equals(task.getUserId()))
                .forEach(e -> result.add(e.getValue()));
        result.sort((s1, s2) -> {
            boolean firstDateMoreThanSecond= s1.getDateBegin().getTime() - s2.getDateBegin().getTime() < 0;
            boolean secondDateMoreThaFirst = s1.getDateBegin().getTime() - s2.getDateBegin().getTime() > 0;

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
    public List<Task> findAllSortByDateEnd(@NotNull final Task abstractEntity) {
        @NotNull final Task task = abstractEntity;
        @NotNull final List<Task> result = new LinkedList<>();
        map.entrySet()
                .stream().filter(e -> Objects.requireNonNull(e.getValue().getUserId()).
                equals(task.getUserId()))
                .forEach(e -> result.add(e.getValue()));
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
    public List<Task> findAllSortByStatus(@NotNull final Task abstractEntity) {
        @NotNull final Task task = abstractEntity;
        @NotNull final List<Task> result = new LinkedList<>();
        map.entrySet()
                .stream().filter(e -> Objects.requireNonNull(e.getValue().getUserId()).
                equals(task.getUserId()))
                .forEach(e -> result.add(e.getValue()));
        result.sort((s1, s2) -> Integer.compare(0, s1.getStatus().ordinal() - s2.getStatus().ordinal()));
        return result;
    }

    @Nullable
    @Override
    public Task findOneByName(@NotNull final Task abstractEntity) {
        @NotNull final Task task = abstractEntity;
        @NotNull final List<Task> result = new ArrayList<>();
        map.forEach((k, v) -> {
            if (Objects.requireNonNull(task.getUserId()).equals(v.getUserId()) &&
                    Objects.requireNonNull(task.getName()).equals(v.getName())) {
                result.add(v);
            }
        });
        if (result.size() > 0) {
            return result.get(0);
        }
        return null;
    }

    @Nullable
    @Override
    public Task findOneByDescription(@NotNull final Task abstractEntity) {
        @NotNull final Task task = abstractEntity;
        @NotNull final List<Task> result = new ArrayList<>();
        map.forEach((k, v) -> {
            if (Objects.requireNonNull(task.getUserId()).equals(v.getUserId()) &&
                    Objects.requireNonNull(task.getDescription()).equals(v.getDescription())) {
                result.add(v);
            }
        });
        if (result.size() > 0) {
            return result.get(0);
        }
        return null;
    }
}
