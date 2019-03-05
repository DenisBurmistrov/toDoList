package ru.burmistrov.tm.repository;

import ru.burmistrov.tm.Bootstrap;
import ru.burmistrov.tm.api.IProjectRepository;
import ru.burmistrov.tm.entity.Project;
import ru.burmistrov.tm.entity.Task;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

public class ProjectRepository implements IProjectRepository {

    private final Bootstrap bootstrap = Bootstrap.getInstance();

    private Map<String, Project> projects = bootstrap.getProjects();

    private Map<String, Task> tasks = bootstrap.getTasks();

    @Override
    public String persist(String name, String description) {
        Project project = new Project();
        project.setName(name);
        project.setDescription(description);
        project.setUserId(bootstrap.getCurrentUser().getId());
        if (projects.containsValue(project)) {
            return "Проект с таким именем уже существует";
        } else {
            projects.put(project.getId(), project);
            return "Добавление произведено";
        }
    }

    @Override
    public void remove(String projectId) {
        projects.entrySet().removeIf(e -> e.getValue().getId().equals(projectId) && e.getValue().getUserId().equals(bootstrap.getCurrentUser().getId()));
        tasks.entrySet().removeIf(e -> e.getValue().getProjectId().equals(projectId) && e.getValue().getUserId().equals(bootstrap.getCurrentUser().getId()));
    }

    @Override
    public void removeAll() {
        tasks.entrySet().removeIf(e -> e.getValue().getUserId().equals(bootstrap.getCurrentUser().getId()));
        projects.entrySet().removeIf(e -> e.getValue().getUserId().equals(bootstrap.getCurrentUser().getId()));
    }

    @Override
    public Map<String, Project> findAll() {
        Map<String, Project> result = new LinkedHashMap<>();
        projects.entrySet()
                .stream().filter(e -> e.getValue().getUserId().equals(bootstrap.getCurrentUser().getId()))
                .forEach(e -> result.put(e.getKey(), e.getValue()));
        return result;
    }

    @Override
    public String merge(String id, String name, String description) {
        Iterator it = projects.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            if (pair.getKey().equals(id)) {
                Project project = new Project();
                project.setId(id);
                project.setName(name);
                project.setDescription(description);
                projects.put(id, project);
                return "Обновление проекта произведено";
            }
        }
        return "Нет проекта с введённый ID";
    }

    @Override
    public void assignExpert(String projectId, String userId) {
        projects.forEach((k,v) -> {
            if(v.getUserId().equals(projectId)){
                v.setUserId(userId);
            }
        });
    }
}