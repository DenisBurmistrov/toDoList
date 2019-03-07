package ru.burmistrov.tm.repository;

import ru.burmistrov.tm.api.repository.IProjectRepository;
import ru.burmistrov.tm.entity.AbstractEntity;
import ru.burmistrov.tm.entity.Project;

import java.util.*;
import java.util.stream.Collectors;

public final class ProjectRepository extends AbstractRepository implements IProjectRepository {

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
    public void remove(AbstractEntity abstractEntity) {
        Project project = (Project) abstractEntity;
        projects.remove(project.getId());
    }

    @Override
    public void removeAll(AbstractEntity abstractEntity) {
        Project project = (Project) abstractEntity;
        projects.entrySet().removeIf(e -> e.getValue().getUserId().equals(project.getUserId()));
    }

    @Override
    public List<AbstractEntity> findAll(AbstractEntity abstractEntity) {
        Project project = (Project) abstractEntity;
        List<AbstractEntity> result = new LinkedList<>();
        projects.entrySet()
                .stream().filter(e -> e.getValue().getUserId().equals(project.getUserId()))
                .forEach(e -> result.add(e.getValue()));
        return result;
    }

    @Override
    public AbstractEntity findOne(AbstractEntity abstractEntity) {
        Project project = (Project) abstractEntity;
        List list = projects.entrySet().stream().filter(e -> project.equals(e.getValue())).collect(Collectors.toList());
        if(list.size() > 0) {
            return (AbstractEntity) list.get(0);
        }
        return null;
    }

}