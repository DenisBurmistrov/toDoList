package ru.burmistrov.tm.repository;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.burmistrov.tm.api.repository.ITaskRepository;
import ru.burmistrov.tm.entity.AbstractEntity;
import ru.burmistrov.tm.entity.Project;
import ru.burmistrov.tm.entity.Task;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public final class TaskRepository extends AbstractRepository implements ITaskRepository<AbstractEntity> {

    private final Map<String, Task> tasks = new LinkedHashMap<>();

    @NotNull
    @Override
    public AbstractEntity persist(@NotNull AbstractEntity entity) {
        tasks.put(entity.getId(), (Task) entity);
        return entity;
    }


    @Override
    public void merge(@NotNull AbstractEntity entity) {
        tasks.put(entity.getId(), (Task) entity);
    }

    @Override
    public void remove(@NotNull AbstractEntity entity) {
        Task task = (Task) entity;
        tasks.remove(task.getId());
    }


    @NotNull
    @Override
    public List<AbstractEntity> findAll(@NotNull AbstractEntity entity) {
        Task task = (Task) entity;
        List<AbstractEntity> result = new LinkedList<>();
        tasks.entrySet()
                .stream().filter(e -> e.getValue().
                getProjectId().equals(task.getProjectId())
                && e.getValue().getUserId().
                equals(task.getUserId()))
                .forEach(e -> result.add(e.getValue()));
        return result;
    }

    @Override
    public void removeAllInProject(@NotNull AbstractEntity entity) {
        Task task = (Task) entity;
        tasks.entrySet().removeIf((e) -> e.getValue().getProjectId().equals(task.getProjectId()) &&
                task.getUserId().equals(e.getValue().getUserId()));
    }

    @Override
    public void removeAll(@NotNull AbstractEntity entity) {
        Task task = (Task) entity;
        tasks.entrySet().removeIf((e) -> e.getValue().getUserId().equals(task.getUserId()));
    }

    @Nullable
    @Override
    public AbstractEntity findOne(@NotNull AbstractEntity entity) {
        Task task = (Task) entity;
        List list = tasks.entrySet().stream().filter(e -> task.equals(e.getValue())).collect(Collectors.toList());
        if(list.size() > 0) {
            return (AbstractEntity) list.get(0);
        }
        return null;
    }

    @NotNull
    @Override
    public List<AbstractEntity> findAllSortByDateBegin(@NotNull AbstractEntity abstractEntity) {
        Task task = (Task) abstractEntity;
        List<AbstractEntity> result = new LinkedList<>();
        tasks.entrySet()
                .stream().filter(e -> e.getValue().getUserId().
                equals(task.getUserId()))
                .forEach(e -> result.add(e.getValue()));
        result.sort((s1, s2) -> {
            if(((Task) s1).getDateBegin().getTime() - ((Task) s2).getDateBegin().getTime() < 0){
                return 1;
            }
            else if(((Task) s1).getDateBegin().getTime() - ((Task) s2).getDateBegin().getTime() > 0){
                return -1;
            }
            else {
                return 0;
            }
        });
        return result;
    }

    @NotNull
    @Override
    public List<AbstractEntity> findAllSortByDateEnd(@NotNull AbstractEntity abstractEntity) {
        Task task = (Task) abstractEntity;
        List<AbstractEntity> result = new LinkedList<>();
        tasks.entrySet()
                .stream().filter(e -> e.getValue().getUserId().
                equals(task.getUserId()))
                .forEach(e -> result.add(e.getValue()));
        result.sort((s1, s2) -> {
            if(((Task) s1).getDateEnd().getTime() - ((Task) s2).getDateEnd().getTime() > 0){
                return 1;
            }
            else if(((Task) s1).getDateEnd().getTime() - ((Task) s2).getDateEnd().getTime() < 0){
                return -1;
            }
            else {
                return 0;
            }
        });
        return result;
    }

    @NotNull
    @Override
    public List<AbstractEntity> findAllSortByStatus(@NotNull AbstractEntity abstractEntity) {
        Task task = (Task) abstractEntity;
        List<AbstractEntity> result = new LinkedList<>();
        tasks.entrySet()
                .stream().filter(e -> e.getValue().getUserId().
                equals(task.getUserId()))
                .forEach(e -> result.add(e.getValue()));
        result.sort((s1, s2) -> Integer.compare(0, ((Task) s1).getStatus().ordinal() - ((Task) s2).getStatus().ordinal()));
        return result;
    }
}
