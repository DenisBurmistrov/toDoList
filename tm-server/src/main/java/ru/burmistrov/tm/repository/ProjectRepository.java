package ru.burmistrov.tm.repository;

import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.burmistrov.tm.api.repository.IProjectRepository;
import ru.burmistrov.tm.entity.AbstractEntity;
import ru.burmistrov.tm.entity.Project;

import java.util.*;

@NoArgsConstructor
public final class ProjectRepository extends AbstractRepository<Project> implements IProjectRepository {

    @NotNull
    @Override
    public List<Project> findAllSortByDateBegin(@NotNull final Project abstractEntity){
        @NotNull final List<Project> result = Objects.requireNonNull(findAll(abstractEntity));
        result.sort((s1, s2) -> {
            boolean firstDateMoreThanSecond = s1.getDateBegin().getTime() - s2.getDateBegin().getTime() < 0;
            boolean secondDateMareFirst = s1.getDateBegin().getTime() - s2.getDateBegin().getTime() > 0;

            if(firstDateMoreThanSecond){
                return 1;
            }
            else if(secondDateMareFirst){
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
    public List<Project> findAllSortByDateEnd(@NotNull final Project abstractEntity){
        @NotNull final List<Project> result = Objects.requireNonNull(findAll(abstractEntity));
        result.sort((s1, s2) -> {
            boolean firstDateMoreThanSecond = Objects.requireNonNull(s1.getDateEnd()).getTime() - Objects.requireNonNull(s2.getDateEnd()).getTime() > 0;
            boolean secondDateMoreThanFirst = Objects.requireNonNull(s1.getDateEnd()).getTime() - Objects.requireNonNull(s2.getDateEnd()).getTime() < 0;

            if(firstDateMoreThanSecond){
                return 1;
            }
            else if(secondDateMoreThanFirst){
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
    public List<Project> findAllSortByStatus(@NotNull final Project abstractEntity) {
        @NotNull final List<Project> result = Objects.requireNonNull(findAll(abstractEntity));
        result.sort((s1, s2) -> Integer.compare(0, s1.getStatus().ordinal() - s2.getStatus().ordinal()));
        return result;
    }

    @Nullable
    @Override
    public Project findOneByName(@NotNull final Project abstractEntity) {
        @NotNull final Project project = abstractEntity;
        @NotNull final List<Project> result = new ArrayList<>();
        map.forEach((k, v) -> {
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
    public Project findOneByDescription(@NotNull final Project abstractEntity) {
        @NotNull final Project project = abstractEntity;
        @NotNull final List<Project> result = new ArrayList<>();
        map.forEach((k, v) -> {
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