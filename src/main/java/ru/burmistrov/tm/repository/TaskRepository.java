package ru.burmistrov.tm.repository;

import ru.burmistrov.tm.api.repository.ITaskRepository;
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
    public void merge(User currentUser, String projectId, String oldName, String newName, String description) {

        tasks.forEach((key, value) -> {
            if (oldName.equals(value.getName()) && projectId.equals(value.getProjectId()) && value.getUserId().equals(currentUser.getId())) {
                Task task = new Task();
                task.setId(value.getId());
                task.setName(newName);
                task.setDescription(description);
                task.setProjectId(projectId);
                task.setUserId(currentUser.getId());
                tasks.put(task.getId(), task);
            }
        });
    }

    @Override
    public Task persist(User currentUser, String projectId, String name, String description) {

        Task task = new Task();
        task.setName(name);
        task.setDescription(description);
        task.setProjectId(projectId);
        task.setUserId(currentUser.getId());
        tasks.put(task.getId(), task);
        return task;
    }

    @Override
    public void remove(User currentUser, String projectId, String taskId) {

        tasks.entrySet().removeIf((k) ->
                (k != null && projectId.equals(k.getValue().getProjectId()) && taskId.equals(k.getValue().getId()) && k.getValue().getUserId().equals(currentUser.getId())));
    }


    @Override
    public List<Task> findAll(User currentUser, String projectId) {
        List<Task> result = new LinkedList<>();
        tasks.entrySet()
                .stream().filter(e -> e.getValue().
                getProjectId().equals(projectId)
                && e.getValue().getUserId().
                equals(currentUser.getId()))
                .forEach(e -> result.add(e.getValue()));
        return result;
    }

    @Override
    public void removeAllinProject(User currentUser, String projectId) {
        tasks.entrySet().removeIf((e) -> e.getValue().getProjectId().equals(projectId) && e.getValue().getUserId().equals(currentUser.getId()));
    }

    @Override
    public void removeAll(User currentUser) {
        tasks.entrySet().removeIf((e) -> e.getValue().getUserId().equals(currentUser.getId()));
    }

    @Override
    public Task findOne(User currentUser, String projectId, String name) {
        Task task;
        List list = tasks.entrySet().stream().filter(e -> projectId.equals(e.getValue().getProjectId()) && name.equals(e.getValue().getName())
                && e.getValue().getUserId().equals(currentUser.getId())).collect(Collectors.toList());
        task = (Task) list.get(0);

        return task;
    }
}
