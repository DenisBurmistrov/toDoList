package ru.burmistrov.tm.service;

import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.burmistrov.tm.api.repository.IProjectRepository;
import ru.burmistrov.tm.api.repository.ITaskRepository;
import ru.burmistrov.tm.api.service.IProjectService;
import ru.burmistrov.tm.entity.AbstractEntity;
import ru.burmistrov.tm.entity.Project;
import ru.burmistrov.tm.util.DateUtil;

import javax.inject.Inject;
import javax.persistence.NoResultException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import static ru.burmistrov.tm.entity.enumerated.Status.createStatus;

@NoArgsConstructor
public final class ProjectService implements IProjectService {

    @Inject
    private IProjectRepository projectRepository;

    @Inject
    private ITaskRepository taskRepository;

    @Override
    public void remove(@NotNull final String userId, @NotNull final String projectId) {

        @Nullable final Project project = projectRepository.findOne(projectId, userId);
        if (project != null) {
            try {
                projectRepository.getEntityManager().getTransaction().begin();
                Objects.requireNonNull(projectRepository).remove(project);
                projectRepository.getEntityManager().getTransaction().commit();
            } catch (Exception e) {
                projectRepository.getEntityManager().getTransaction().rollback();
            }
        }
    }

    @Override
    public Project persist
            (@NotNull final String userId, @NotNull final String name, @NotNull final String description,
             @NotNull final String dateEndString, @NotNull final String status) {
        try {
            projectRepository.findOneByName(userId, name);
        } catch (NoResultException e) {
            try {
                @NotNull final Project project = new Project();
                project.setUserId(userId);
                project.setDateBegin(new Date());
                project.setDateEnd(DateUtil.parseDate(dateEndString));
                project.setName(name);
                project.setDescription(description);
                project.setStatus(createStatus(status));
                projectRepository.getEntityManager().getTransaction().begin();
                projectRepository.persist(project);
                projectRepository.getEntityManager().getTransaction().commit();
                return project;
            } catch (Exception ex) {
                projectRepository.getEntityManager().getTransaction().rollback();
            }
        }
        return null;
    }

    @Override
    public void merge
            (@NotNull final String userId, @NotNull final String projectId, @NotNull final String name,
             @NotNull final String description, @NotNull final String dateEndString, @NotNull final String status) {
        try {
            @NotNull final Project project = new Project();
            project.setId(projectId);
            project.setUserId(userId);
            project.setName(name);
            project.setDescription(description);
            project.setStatus(createStatus(status));
            project.setDateEnd(DateUtil.parseDate(dateEndString));
            @Nullable final AbstractEntity abstractEntity =
                    Objects.requireNonNull(projectRepository).findOne(project.getId(), Objects.requireNonNull(project.getUserId()));
            if (abstractEntity != null) {
                projectRepository.getEntityManager().getTransaction().begin();
                Objects.requireNonNull(projectRepository).merge(project);
                projectRepository.getEntityManager().getTransaction().commit();
            }
        } catch (Exception e) {
            projectRepository.getEntityManager().getTransaction().rollback();
        }
    }

    @Override
    public void removeAll(@Nullable final String userId) {
        try {
            projectRepository.getEntityManager().getTransaction().begin();
            Objects.requireNonNull(taskRepository).removeAll(Objects.requireNonNull(userId));
            Objects.requireNonNull(projectRepository).removeAll(userId);
            projectRepository.getEntityManager().getTransaction().commit();
        } catch (Exception e) {
            projectRepository.getEntityManager().getTransaction().rollback();
        }
    }

    @Override
    @Nullable
    public List<Project> findAll(@NotNull final String userId) {
        return projectRepository.findAll(userId);
    }

    @NotNull
    @Override
    public List<Project> findAllSortByDateBegin(@Nullable final String userId) {
        @NotNull final List<Project> result = Objects.requireNonNull(findAll(Objects.requireNonNull(userId)));
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
    public List<Project> findAllSortByDateEnd(@Nullable final String userId) {
        @NotNull final List<Project> result = Objects.requireNonNull(findAll(Objects.requireNonNull(userId)));
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
        result.sort((s1, s2) -> Integer.compare(0, Objects.requireNonNull(s1.getStatus()).ordinal() - Objects.requireNonNull(s2.getStatus()).ordinal()));
        return result;
    }

    @Nullable
    @Override
    public Project findOneByName(@Nullable final String userId, @NotNull final String name) {
        try {
            return Objects.requireNonNull(projectRepository).findOneByName(Objects.requireNonNull(userId), name);
        } catch (NoResultException e) {
            return null;
        }
    }

    @Nullable
    @Override
    public Project findOneByDescription(@Nullable final String userId, @NotNull final String description) {
        try {
            return Objects.requireNonNull(projectRepository).findOneByDescription(Objects.requireNonNull(userId), description);
        } catch (NoResultException e) {
            return null;
        }
    }
}
