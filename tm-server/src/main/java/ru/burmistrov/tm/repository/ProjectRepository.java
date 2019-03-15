package ru.burmistrov.tm.repository;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.burmistrov.tm.api.repository.IProjectRepository;
import ru.burmistrov.tm.entity.AbstractEntity;
import ru.burmistrov.tm.entity.Project;

import java.util.*;

public final class ProjectRepository /*extends AbstractRepository<Project> */implements IProjectRepository {

    private final Map<String, Project> projects = new LinkedHashMap<>();

    @NotNull
    @Override
    public Project persist(@NotNull Project entity) {
        projects.put(entity.getId(), entity);
        return entity;
    }

    @Override
    public void merge(@NotNull Project entity) {
        projects.put(entity.getId(), entity);
    }

    @Override
    public void remove(@NotNull Project abstractEntity) {
        @NotNull final Project project = abstractEntity;
        projects.remove(project.getId());
    }

    @Override
    public void removeAll(@NotNull Project abstractEntity) {
        @NotNull final Project project = abstractEntity;
        projects.entrySet().removeIf(e -> Objects.requireNonNull(e.getValue().getUserId()).equals(project.getUserId()));
    }

    @NotNull
    @Override
    public List<Project> findAll(@NotNull Project abstractEntity){
        @NotNull final Project project = abstractEntity;
        @NotNull final List<Project> result = new LinkedList<>();
        projects.entrySet()
                .stream().filter(e -> Objects.requireNonNull(e.getValue().getUserId()).equals(project.getUserId()))
                .forEach(e -> result.add(e.getValue()));
        return result;
    }

    @Nullable
    @Override
    public Project findOne(@NotNull Project abstractEntity) {
        @NotNull final Project project = abstractEntity;
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
    public List<Project> findAllSortByDateBegin(@NotNull Project abstractEntity){
        @NotNull final List<Project> result = findAll(abstractEntity);
        result.sort((s1, s2) -> {
            if(s1.getDateBegin().getTime() - s2.getDateBegin().getTime() < 0){
                return 1;
            }
            else if(s1.getDateBegin().getTime() - s2.getDateBegin().getTime() > 0){
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
    public List<Project> findAllSortByDateEnd(@NotNull Project abstractEntity){
        @NotNull final List<Project> result = findAll(abstractEntity);
        result.sort((s1, s2) -> {
            if(Objects.requireNonNull(s1.getDateEnd()).getTime() - Objects.requireNonNull(s2.getDateEnd()).getTime() > 0){
                return 1;
            }
            else if(Objects.requireNonNull(s1.getDateEnd()).getTime() - Objects.requireNonNull(s2.getDateEnd()).getTime() < 0){
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
    public List<Project> findAllSortByStatus(@NotNull Project abstractEntity) {
        @NotNull final List<Project> result = findAll(abstractEntity);
        result.sort((s1, s2) -> Integer.compare(0, ((Project) s1).getStatus().ordinal() - ((Project) s2).getStatus().ordinal()));
        return result;
    }

    @Nullable
    @Override
    public Project findOneByName(@NotNull Project abstractEntity) {
        @NotNull final Project project = abstractEntity;
        @NotNull final List<Project> result = new ArrayList<>();
        projects.forEach((k, v) -> {
            if(Objects.requireNonNull(project.getUserId()).equals(v.getUserId()) &&
                    Objects.requireNonNull(project.getName()).equals(v.getName())){
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
    public Project findOneByDescription(@NotNull Project abstractEntity) {
        @NotNull final Project project = abstractEntity;
        @NotNull final List<Project> result = new ArrayList<>();
        projects.forEach((k, v) -> {
            if(Objects.requireNonNull(project.getUserId()).equals(v.getUserId()) &&
                    Objects.requireNonNull(project.getDescription()).equals(v.getDescription())){
                result.add(v);
            }
        });
        if(result.size() > 0) {
            return result.get(0);
        }
        return null;
    }

}