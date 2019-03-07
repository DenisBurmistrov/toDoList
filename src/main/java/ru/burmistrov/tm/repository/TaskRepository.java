package ru.burmistrov.tm.repository;

import ru.burmistrov.tm.api.repository.ITaskRepository;
import ru.burmistrov.tm.entity.AbstractEntity;
import ru.burmistrov.tm.entity.Task;
import ru.burmistrov.tm.entity.User;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public final class TaskRepository implements ITaskRepository {

    private final Map<String, Task> tasks = new LinkedHashMap<>();

    @Override
    public AbstractEntity persist(AbstractEntity entity) {
        tasks.put(entity.getId(), (Task) entity);
        return entity;
    }


    @Override
    public void merge(AbstractEntity entity) {
        tasks.put(entity.getId(), (Task) entity);
    }

    @Override
    public void remove(String userId, String projectId, String taskId) {

        tasks.entrySet().removeIf((k) ->
                (k != null && projectId.equals(k.getValue().getProjectId()) && taskId.equals(k.getValue().getId()) && k.getValue().getUserId().equals(userId)));
    }


    @Override
    public List<Task> findAll(String userId, String projectId) {
        List<Task> result = new LinkedList<>();
        tasks.entrySet()
                .stream().filter(e -> e.getValue().
                getProjectId().equals(projectId)
                && e.getValue().getUserId().
                equals(userId))
                .forEach(e -> result.add(e.getValue()));
        return result;
    }

    @Override
    public void removeAllInProject(String userId, String projectId) {
        tasks.entrySet().removeIf((e) -> e.getValue().getProjectId().equals(projectId) && e.getValue().getUserId().equals(userId));
    }

    @Override
    public void removeAll(String userId) {
        tasks.entrySet().removeIf((e) -> e.getValue().getUserId().equals(userId));
    }

    @Override
    public Task findOne(String userId, String projectId, String name) {
        Task task;
        List list = tasks.entrySet().stream().filter(e -> projectId.equals(e.getValue().getProjectId()) && name.equals(e.getValue().getName())
                && e.getValue().getUserId().equals(userId)).collect(Collectors.toList());
        task = (Task) list.get(0);

        return task;
    }

    @Override
    public Map<String, AbstractEntity> getAbstractEntities() {
        Map<String, AbstractEntity> map = new LinkedHashMap<>();
        tasks.forEach(map::put);
        return map;
    }
}
