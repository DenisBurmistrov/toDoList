package ru.burmistrov.tm.repository;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.burmistrov.tm.api.repository.ITaskRepository;
import ru.burmistrov.tm.entity.AbstractEntity;
import ru.burmistrov.tm.entity.Task;

import java.util.*;
import java.util.stream.Collectors;

public final class TaskRepository /*extends AbstractRepository<Task>*/ implements ITaskRepository {

    private final Map<String, Task> tasks = new LinkedHashMap<>();

    @NotNull
    @Override
    public Task persist(@NotNull Task entity) {
        tasks.put(entity.getId(), entity);
        return entity;
    }


    @Override
    public void merge(@NotNull Task entity) {
        tasks.put(entity.getId(), entity);
    }

    @Override
    public void remove(@NotNull Task entity) {
        @NotNull final Task task = entity;
        tasks.remove(task.getId());
    }

    @NotNull
    @Override
    public List<Task> findAll(@NotNull Task entity) {
        @NotNull final Task task = entity;
        @NotNull final List<Task> result = new LinkedList<>();
        tasks.entrySet()
                .stream().filter(e ->Objects.requireNonNull(e.getValue().getUserId()).
                equals(task.getUserId()))
                .forEach(e -> result.add(e.getValue()));
        return result;
    }

    @NotNull
    @Override
    public List<Task> findAllInProject(@NotNull Task entity) {
        @NotNull final Task task = entity;
        @NotNull final List<Task> result = new LinkedList<>();
        tasks.entrySet()
                .stream().filter(e -> Objects.requireNonNull(e.getValue().
                getProjectId()).equals(task.getProjectId())
                && Objects.requireNonNull(e.getValue().getUserId()).
                equals(task.getUserId()))
                .forEach(e -> result.add(e.getValue()));
        return result;
    }

    @Override
    public void removeAllInProject(@NotNull Task entity) {
        @NotNull final Task task = entity;
        tasks.entrySet().removeIf((e) -> Objects.requireNonNull(e.getValue().getProjectId()).equals(task.getProjectId()) &&
                Objects.requireNonNull(task.getUserId()).equals(e.getValue().getUserId()));
    }


    @Override
    public void removeAll(@NotNull Task entity) {
        @NotNull final Task task = entity;
        tasks.entrySet().removeIf((e) -> Objects.requireNonNull(e.getValue().getUserId()).equals(task.getUserId()));
    }

    @Nullable
    @Override
    public Task findOne(@NotNull Task entity) {
        @NotNull final Task task = entity;
        List list = tasks.entrySet().stream().filter(e -> task.equals(e.getValue())).collect(Collectors.toList());
        if (list.size() > 0) {
            return (Task) list.get(0);
        }
        return null;
    }

    @NotNull
    @Override
    public List<Task> findAllSortByDateBegin(@NotNull Task abstractEntity) {
        @NotNull final Task task = abstractEntity;
        @NotNull final List<Task> result = new LinkedList<>();
        tasks.entrySet()
                .stream().filter(e -> Objects.requireNonNull(e.getValue().getUserId()).
                equals(task.getUserId()))
                .forEach(e -> result.add(e.getValue()));
        result.sort((s1, s2) -> {
            if (s1.getDateBegin().getTime() - s2.getDateBegin().getTime() < 0) {
                return 1;
            } else if (s1.getDateBegin().getTime() - s2.getDateBegin().getTime() > 0) {
                return -1;
            } else {
                return 0;
            }
        });
        return result;
    }

    @NotNull
    @Override
    public List<Task> findAllSortByDateEnd(@NotNull Task abstractEntity) {
        @NotNull final Task task = abstractEntity;
        @NotNull final List<Task> result = new LinkedList<>();
        tasks.entrySet()
                .stream().filter(e -> Objects.requireNonNull(e.getValue().getUserId()).
                equals(task.getUserId()))
                .forEach(e -> result.add(e.getValue()));
        result.sort((s1, s2) -> {
            if (Objects.requireNonNull(s1.getDateEnd()).getTime() - Objects.requireNonNull(s2.getDateEnd()).getTime() > 0) {
                return 1;
            } else if (Objects.requireNonNull(s1.getDateEnd()).getTime()
                    - Objects.requireNonNull(s2.getDateEnd()).getTime() < 0) {
                return -1;
            } else {
                return 0;
            }
        });
        return result;
    }

    @NotNull
    @Override
    public List<Task> findAllSortByStatus(@NotNull Task abstractEntity) {
        @NotNull final Task task = abstractEntity;
        @NotNull final List<Task> result = new LinkedList<>();
        tasks.entrySet()
                .stream().filter(e -> Objects.requireNonNull(e.getValue().getUserId()).
                equals(task.getUserId()))
                .forEach(e -> result.add(e.getValue()));
        result.sort((s1, s2) -> Integer.compare(0, s1.getStatus().ordinal() - s2.getStatus().ordinal()));
        return result;
    }

    @Nullable
    @Override
    public Task findOneByName(@NotNull Task abstractEntity) {
        @NotNull final Task task = abstractEntity;
        @NotNull final List<Task> result = new ArrayList<>();
        tasks.forEach((k, v) -> {
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
    public Task findOneByDescription(@NotNull Task abstractEntity) {
        @NotNull final Task task = abstractEntity;
        @NotNull final List<Task> result = new ArrayList<>();
        tasks.forEach((k, v) -> {
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
