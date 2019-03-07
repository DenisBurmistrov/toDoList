package ru.burmistrov.tm.repository;

import ru.burmistrov.tm.api.repository.IProjectRepository;
import ru.burmistrov.tm.entity.AbstractEntity;
import ru.burmistrov.tm.entity.Project;

import java.util.*;

public final class ProjectRepository implements IProjectRepository {

    private final Map<String, Project> projects = new LinkedHashMap<>();

    @Override
    public AbstractEntity persist(AbstractEntity entity) {
        projects.put(entity.getId(), (Project) entity);
        return entity;

    }

    @Override
    public void merge(AbstractEntity entity) {
        projects.put(entity.getId(), (Project) entity);
    }

    @Override
    public void remove(String userId, String projectId) {
        projects.entrySet().removeIf(e -> e.getValue().getId().equals(projectId) && e.getValue().getUserId().equals(userId));
    }

    @Override
    public void removeAll(String userId) {
        projects.entrySet().removeIf(e -> e.getValue().getUserId().equals(userId));
    }

    @Override
    public List<Project> findAll(String userId) {
        List<Project> result = new LinkedList<>();
        projects.entrySet()
                .stream().filter(e -> e.getValue().getUserId().equals(userId))
                .forEach(e -> result.add(e.getValue()));
        return result;
    }




    public Map<String, AbstractEntity> getAbstractEntities() {
        Map<String, AbstractEntity> map = new LinkedHashMap<>();
        projects.forEach(map::put);
        return map;
    }
}