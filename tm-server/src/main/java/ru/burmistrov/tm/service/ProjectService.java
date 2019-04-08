package ru.burmistrov.tm.service;

import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.burmistrov.tm.repository.IProjectRepository;
import ru.burmistrov.tm.repository.ITaskRepository;
import ru.burmistrov.tm.api.service.IProjectService;
import ru.burmistrov.tm.entity.Project;
import ru.burmistrov.tm.util.DateUtil;

import javax.persistence.NoResultException;
import java.text.ParseException;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import static ru.burmistrov.tm.entity.enumerated.Status.createStatus;


@Transactional
@NoArgsConstructor
@Component
public class ProjectService implements IProjectService {

    @Autowired
    private IProjectRepository projectRepository;

    @Autowired
    private ITaskRepository taskRepository;

    @Override
    public void remove(@NotNull final String userId, @NotNull final String projectId) {
        try {
            @Nullable final Project project = projectRepository.findOne(projectId, userId);
            if (project != null) {
                Objects.requireNonNull(projectRepository).delete(project);
            }
        } catch (NoResultException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Project persist
            (@NotNull final String userId, @NotNull final String name, @NotNull final String description,
             @NotNull final String dateEndString, @NotNull final String status) throws ParseException {
            projectRepository.findOneByName(userId, name);
            @NotNull final Project project = new Project();
            project.setUserId(userId);
            project.setDateBegin(new Date());
            project.setDateEnd(DateUtil.parseDate(dateEndString));
            project.setName(name);
            project.setDescription(description);
            project.setStatus(createStatus(status));
            return projectRepository.save(project);
    }

    @Override
    public void merge
            (@NotNull final String userId, @NotNull final String projectId, @NotNull final String name,
             @NotNull final String description, @NotNull final String dateEndString, @NotNull final String status) throws ParseException {
        try {
            @NotNull final Project project = Objects.requireNonNull(projectRepository).findOne(projectId, userId);
            project.setName(name);
            project.setDescription(description);
            project.setStatus(createStatus(status));
            project.setDateEnd(DateUtil.parseDate(dateEndString));
            projectRepository.save(project);
        } catch (NoResultException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeAll(@Nullable final String userId) {
            Objects.requireNonNull(taskRepository).removeAll(Objects.requireNonNull(userId));
            Objects.requireNonNull(projectRepository).removeAll(userId);
    }

    @Nullable
    @Override
    public List<Project> findAll(@NotNull final String userId) {
        return projectRepository.findAll(userId);
    }

    @NotNull
    @Override
    public List<Project> findAllSortByDateBegin(@Nullable final String userId) {
        @NotNull final List<Project> result = Objects.requireNonNull(findAll(Objects.requireNonNull(userId)));
        result.sort(Comparator.comparingLong(s -> s.getDateBegin().getTime()));
        return result;
    }

    @NotNull
    @Override
    public List<Project> findAllSortByDateEnd(@Nullable final String userId) {
        @NotNull final List<Project> result = Objects.requireNonNull(findAll(Objects.requireNonNull(userId)));
        result.sort((s1, s2) -> Long.compare(s2.getDateBegin().getTime(), s1.getDateBegin().getTime()));
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
