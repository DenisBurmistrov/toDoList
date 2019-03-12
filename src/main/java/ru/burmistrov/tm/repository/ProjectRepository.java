package ru.burmistrov.tm.repository;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.burmistrov.tm.api.repository.IProjectRepository;
import ru.burmistrov.tm.entity.AbstractEntity;
import ru.burmistrov.tm.entity.Project;

import java.util.*;
import java.util.stream.Collectors;

public final class ProjectRepository extends AbstractRepository implements IProjectRepository<AbstractEntity> {

    private final Map<String, Project> projects = new LinkedHashMap<>();

    @NotNull
    @Override
    public AbstractEntity persist(@NotNull AbstractEntity entity) {
        projects.put(entity.getId(), (Project) entity);
        return entity;
    }

    @Override
    public void merge(@NotNull AbstractEntity entity) {
        projects.put(entity.getId(), (Project) entity);
    }

    @Override
    public void remove(@NotNull AbstractEntity abstractEntity) {
        Project project = (Project) abstractEntity;
        projects.remove(project.getId());
    }

    @Override
    public void removeAll(@NotNull AbstractEntity abstractEntity) {
        Project project = (Project) abstractEntity;
        projects.entrySet().removeIf(e -> e.getValue().getUserId().equals(project.getUserId()));
    }

    @NotNull
    @Override
    public List<AbstractEntity> findAll(@NotNull AbstractEntity abstractEntity) {
        Project project = (Project) abstractEntity;
        List<AbstractEntity> result = new LinkedList<>();
        projects.entrySet()
                .stream().filter(e -> e.getValue().getUserId().equals(project.getUserId()))
                .forEach(e -> result.add(e.getValue()));
        return result;
    }

    @Nullable
    @Override
    public AbstractEntity findOne(@NotNull AbstractEntity abstractEntity) {
        Project project = (Project) abstractEntity;
        List list = projects.entrySet().stream().filter(e -> project.equals(e.getValue())).collect(Collectors.toList());
        if(list.size() > 0) {
            return (AbstractEntity) list.get(0);
        }
        return null;
    }

    @NotNull
    @Override
    public List<AbstractEntity> findAllSortByDateBegin(@NotNull AbstractEntity abstractEntity) {
        List<AbstractEntity> result = findAll(abstractEntity);
        result.sort((s1, s2) -> {
            if(((Project) s1).getDateBegin().getTime() - ((Project) s2).getDateBegin().getTime() < 0){
                return 1;
            }
            else if(((Project) s1).getDateBegin().getTime() - ((Project) s2).getDateBegin().getTime() > 0){
                return -1;
            }
            else {
                return 0;
            }
        });
        return result;
    }

}