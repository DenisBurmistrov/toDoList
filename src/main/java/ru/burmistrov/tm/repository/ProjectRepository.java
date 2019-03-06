package ru.burmistrov.tm.repository;

import ru.burmistrov.tm.api.repository.IProjectRepository;
import ru.burmistrov.tm.entity.Project;
import ru.burmistrov.tm.entity.User;

import java.util.*;

public final class ProjectRepository implements IProjectRepository {

    private final Map<String, Project> projects = new LinkedHashMap<>();

    @Override
    public Project persist(User currentUser, String name, String description) {
        Project project = new Project();
        project.setName(name);
        project.setDescription(description);
        project.setUserId(currentUser.getId());
        projects.put(project.getId(), project);
        return project;

    }

    @Override
    public void remove(User currentUser, String projectId) {
        projects.entrySet().removeIf(e -> e.getValue().getId().equals(projectId) && e.getValue().getUserId().equals(currentUser.getId()));
    }

    @Override
    public void removeAll(User currentUser) {
        projects.entrySet().removeIf(e -> e.getValue().getUserId().equals(currentUser.getId()));
    }

    @Override
    public List<Project> findAll(User currentUser) {
        List<Project> result = new LinkedList<>();
        projects.entrySet()
                .stream().filter(e -> e.getValue().getUserId().equals(currentUser.getId()))
                .forEach(e -> result.add(e.getValue()));
        return result;
    }

    @Override
    public void merge(User currentUser, String id, String name, String description) {
        Iterator it = projects.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            if (pair.getKey().equals(id)) {
                Project project = new Project();
                project.setId(id);
                project.setName(name);
                project.setDescription(description);
                projects.put(id, project);
            }
        }
    }

    @Override
    public void assignExpert(User currentUser, String projectId, String userId) {
        projects.forEach((k,v) -> {
            if(v.getUserId().equals(projectId)){
                v.setUserId(userId);
            }
        });
    }

    public Map<String, Project> getProjects() {
        return projects;
    }
}