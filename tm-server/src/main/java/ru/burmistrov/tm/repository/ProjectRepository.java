package ru.burmistrov.tm.repository;

import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import org.apache.ibatis.session.SqlSessionFactory;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.burmistrov.tm.api.repository.IProjectRepository;
import ru.burmistrov.tm.entity.AbstractEntity;
import ru.burmistrov.tm.entity.Project;
import ru.burmistrov.tm.entity.enumerated.FieldConst;
import ru.burmistrov.tm.mapper.IProjectMapper;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

@NoArgsConstructor
public final class ProjectRepository extends AbstractRepository<Project> implements IProjectRepository {

    @Nullable
    private IProjectMapper projectMapper;

    public ProjectRepository(@Nullable IProjectMapper projectMapper) {
        this.projectMapper = projectMapper;
    }

    @NotNull
    @Override
    public Project persist(@NotNull final String userId, @NotNull final Date dateBegin,
                           @NotNull final Date dateEnd, @NotNull final String description,
                           @NotNull final String name) {
        @NotNull final Project project = new Project();
        project.setUserId(userId);
        project.setDateBegin(dateBegin);
        project.setDateEnd(dateEnd);
        project.setName(name);
        project.setDescription(description);

        Objects.requireNonNull(projectMapper).persist(project.getId(), userId, dateBegin, dateEnd, description, name);
        return project;
    }

    @Override
    public void merge(@NotNull final Project project) {
        Objects.requireNonNull(projectMapper).merge(project);
    }

    @Override
    public void remove(@NotNull final String id, @NotNull final String userId) {
        Objects.requireNonNull(projectMapper).remove(id, userId);
    }

    @Override
    public void removeAll(@NotNull final String userId) {
        Objects.requireNonNull(projectMapper).removeAll(userId);
    }

    @NotNull
    @Override
    public List<Project> findAll(@NotNull final String userId) {
        return Objects.requireNonNull(projectMapper).findAll(userId);
    }

    @Nullable
    @Override
    public Project findOne(@NotNull final String id, @NotNull final String userId) {
        return Objects.requireNonNull(projectMapper).findOne(id, userId);
    }

    @NotNull
    @Override
    public List<Project> findAllSortByDateBegin(@NotNull final String userId) {
        @NotNull final List<Project> result = Objects.requireNonNull(findAll(userId));
        result.sort((s1, s2) -> {
            @NotNull final boolean firstDateMoreThanSecond = s1.getDateBegin().getTime() - s2.getDateBegin().getTime() < 0;
            @NotNull final boolean secondDateMareFirst = s1.getDateBegin().getTime() - s2.getDateBegin().getTime() > 0;

            if (firstDateMoreThanSecond) {
                return 1;
            } else if (secondDateMareFirst) {
                return -1;
            } else {
                return 0;
            }
        });
        return result;
    }

    @NotNull
    @Override
    public List<Project> findAllSortByDateEnd(@NotNull final String userId) {
        @NotNull final List<Project> result = Objects.requireNonNull(findAll(userId));
        result.sort((s1, s2) -> {
            boolean firstDateMoreThanSecond = Objects.requireNonNull(s1.getDateEnd()).getTime() - Objects.requireNonNull(s2.getDateEnd()).getTime() > 0;
            boolean secondDateMoreThanFirst = Objects.requireNonNull(s1.getDateEnd()).getTime() - Objects.requireNonNull(s2.getDateEnd()).getTime() < 0;

            if (firstDateMoreThanSecond) {
                return 1;
            } else if (secondDateMoreThanFirst) {
                return -1;
            } else {
                return 0;
            }
        });
        return result;
    }

    @NotNull
    @Override
    public List<Project> findAllSortByStatus(@NotNull final String userId) {
        @NotNull final List<Project> result = Objects.requireNonNull(findAll(userId));
        result.sort((s1, s2) -> Integer.compare(0, s1.getStatus().ordinal() - s2.getStatus().ordinal()));
        return result;
    }

    @Nullable
    @Override
    public Project findOneByName(@NotNull final String userId, @NotNull final String name) {
        return Objects.requireNonNull(projectMapper).findOneByName(userId, name);
    }

    @Nullable
    @Override
    public Project findOneByDescription(@NotNull final String userId, @NotNull final String description) {
        return Objects.requireNonNull(projectMapper).findOneByDescription(userId, description);
    }
}