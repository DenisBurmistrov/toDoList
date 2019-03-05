package ru.burmistrov.tm.repository;

import ru.burmistrov.tm.Bootstrap;
import ru.burmistrov.tm.api.IProjectRepository;
import ru.burmistrov.tm.entity.Project;
import ru.burmistrov.tm.entity.Task;

import java.util.Iterator;
import java.util.Map;

public class ProjectRepository implements IProjectRepository {

    private Map<String, Project> projects = Bootstrap.projects;
    private Map<String, Task> tasks = Bootstrap.tasks;


    @Override
    public String persist(String name, String description) {
        Project project = new Project();
        project.setName(name);
        project.setDescription(description);

        if (projects.containsValue(project)) {
           return "Проект с таким именем уже существует";
        } else {
            projects.put(project.getId(), project);
            return "Добавление произведено";
        }
    }

    @Override
    public void remove(String projectId){

            projects.entrySet().removeIf(e -> e.getValue().getId().equals(projectId));
            tasks.entrySet().removeIf(e -> e.getValue().getProjectId().equals(projectId));
        }

    @Override
    public void removeAll() {
        tasks.clear();
        projects.clear();
    }

    @Override
    public Map<String, Project> findAll() {
        return projects;
    }

    @Override
    public String merge(String id, String name, String description) {
        Iterator it = projects.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            if(pair.getKey().equals(id)){
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


}