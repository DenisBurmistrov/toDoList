package ru.burmistrov.tm.repository;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.burmistrov.tm.api.repository.IProjectRepository;
import ru.burmistrov.tm.entity.AbstractEntity;
import ru.burmistrov.tm.entity.Project;

import java.util.*;

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
        @NotNull final Project project = (Project) abstractEntity;
        projects.remove(project.getId());
    }

    @Override
    public void removeAll(@NotNull AbstractEntity abstractEntity) {
        @NotNull final Project project = (Project) abstractEntity;
        projects.entrySet().removeIf(e -> e.getValue().getUserId().equals(project.getUserId()));
    }

    @NotNull
    @Override
    public List<AbstractEntity> findAll(@NotNull AbstractEntity abstractEntity){
        @NotNull final Project project = (Project) abstractEntity;
        @NotNull final List<AbstractEntity> result = new LinkedList<>();
        projects.entrySet()
                .stream().filter(e -> e.getValue().getUserId().equals(project.getUserId()))
                .forEach(e -> result.add(e.getValue()));
        return result;
    }

    @Nullable
    @Override
    public AbstractEntity findOne(@NotNull AbstractEntity abstractEntity) {
        @NotNull final Project project = (Project) abstractEntity;
        @NotNull final List<Project> result = new ArrayList<>();
        projects.forEach((k, v) -> {
            if(project.getId().equals(k)){
                result.add(v);
            }
        });
        if(result.size() > 0) {
            return result.get(0);
        }
        return null;
    }

    @NotNull
    @Override
    public List<AbstractEntity> findAllSortByDateBegin(@NotNull AbstractEntity abstractEntity){
        @NotNull final List<AbstractEntity> result = findAll(abstractEntity);
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

    @NotNull
    @Override
    public List<AbstractEntity> findAllSortByDateEnd(@NotNull AbstractEntity abstractEntity){
        @NotNull final List<AbstractEntity> result = findAll(abstractEntity);
        result.sort((s1, s2) -> {
            if(Objects.requireNonNull(((Project) s1).getDateEnd()).getTime() - ((Project) s2).getDateEnd().getTime() > 0){
                return 1;
            }
            else if(((Project) s1).getDateEnd().getTime() - ((Project) s2).getDateEnd().getTime() < 0){
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
        @NotNull final List<AbstractEntity> result = findAll(abstractEntity);
        result.sort((s1, s2) -> Integer.compare(0, ((Project) s1).getStatus().ordinal() - ((Project) s2).getStatus().ordinal()));
        return result;
    }

    @Nullable
    @Override
    public AbstractEntity findOneByName(@NotNull AbstractEntity abstractEntity) {
        @NotNull final Project project = (Project) abstractEntity;
        @NotNull final List<Project> result = new ArrayList<>();
        projects.forEach((k, v) -> {
            if(project.getUserId().equals(v.getUserId()) &&
                    project.getName().equals(v.getName())){
                result.add(v);
            }
        });
        if(result.size() > 0) {
            return result.get(0);
        }
        return null;
    }

    @Nullable
    @Override
    public AbstractEntity findOneByDescription(@NotNull AbstractEntity abstractEntity) {
        @NotNull final Project project = (Project) abstractEntity;
        @NotNull final List<Project> result = new ArrayList<>();
        projects.forEach((k, v) -> {
            if(project.getUserId().equals(v.getUserId()) &&
                    project.getDescription().equals(v.getDescription())){
                result.add(v);
            }
        });
        if(result.size() > 0) {
            return result.get(0);
        }
        return null;
    }

}