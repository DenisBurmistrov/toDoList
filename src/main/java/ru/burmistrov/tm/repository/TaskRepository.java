package ru.burmistrov.tm.repository;

import ru.burmistrov.tm.Bootstrap;
import ru.burmistrov.tm.api.ITaskRepository;
import ru.burmistrov.tm.entity.Project;
import ru.burmistrov.tm.entity.Task;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TaskRepository implements ITaskRepository {

    private final Bootstrap bootstrap = Bootstrap.getInstance();

    private Map<String, Task> tasks = bootstrap.getTasks();

    private Map<String, Project> projects = bootstrap.getProjects();

    @Override
    public String merge(String projectId, String oldName, String newName, String description, Integer priority) {

        tasks.forEach((key, value) -> {
            if (oldName.equals(value.getName()) && projectId.equals(value.getProjectId()) && value.getUserId().equals(bootstrap.getCurrentUser().getId())) {
                Task task = new Task();
                task.setId(value.getId());
                task.setName(newName);
                task.setDescription(description);
                task.setProjectId(projectId);
                task.setUserId(bootstrap.getCurrentUser().getId());
                boolean isSetPriority = task.setPriority(priority);
                if (isSetPriority) {
                    tasks.put(task.getId(), task);
                }
            }
        });
        return "";

    }

    @Override
    public String persist(String projectId, String name, String description, Integer priority) {

        Task task = new Task();
        task.setName(name);
        task.setDescription(description);
        task.setProjectId(projectId);
        task.setUserId(bootstrap.getCurrentUser().getId());
        if (projects.containsKey(projectId)) {
            boolean isSetPriority = task.setPriority(priority);
            if (isSetPriority) {
                tasks.put(task.getId(), task);
                return "Задача добавлена в проект c ID: " + projectId + "";
            } else return "Неверное значение приоритета";
        } else {
            return "Нет проекта с введенным ID";
        }
    }

    @Override
    public void remove(String projectId, String taskId) {

        tasks.entrySet().removeIf((k) ->
                (k != null && projectId.equals(k.getValue().getProjectId()) && taskId.equals(k.getValue().getId()) && k.getValue().getUserId().equals(bootstrap.getCurrentUser().getId())));
    }


    @Override
    public Map<String, Task> findAll(String projectId) {
        Map<String, Task> result = new LinkedHashMap<>();
        tasks.entrySet()
                .stream().filter(e -> e.getValue().
                getProjectId().equals(projectId)
                && e.getValue().getUserId().
                equals(bootstrap.getCurrentUser().getId()))
                .forEach(e -> result.put(e.getKey(), e.getValue()));
        return result;
    }

    @Override
    public void removeAll(String projectId) {
        tasks.entrySet().removeIf((e) -> e.getValue().getProjectId().equals(projectId) && e.getValue().getUserId().equals(bootstrap.getCurrentUser().getId()));
    }

    @Override
    public Task findOne(String projectId, String name) {
        Task task;
        List list = tasks.entrySet().stream().filter(e -> projectId.equals(e.getValue().getProjectId()) && name.equals(e.getValue().getName())
                && e.getValue().getUserId().equals(bootstrap.getCurrentUser().getId())).collect(Collectors.toList());
        task = (Task) list.get(0);

        return task;
    }

}
