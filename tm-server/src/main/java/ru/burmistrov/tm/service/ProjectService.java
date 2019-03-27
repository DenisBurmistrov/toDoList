package ru.burmistrov.tm.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.burmistrov.tm.api.repository.IProjectRepository;
import ru.burmistrov.tm.api.repository.ITaskRepository;
import ru.burmistrov.tm.api.service.IProjectService;
import ru.burmistrov.tm.entity.AbstractEntity;
import ru.burmistrov.tm.entity.Project;
import ru.burmistrov.tm.entity.enumerated.Status;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public final class ProjectService implements IProjectService {

    @NotNull
    private final IProjectRepository projectRepository;

    @NotNull
    private final ITaskRepository taskRepository;

    public ProjectService(@NotNull final IProjectRepository projectRepository, @NotNull final ITaskRepository taskRepository) {
        this.projectRepository = projectRepository;
        this.taskRepository = taskRepository;
    }

    @Override
    public void remove(@NotNull final String userId, @NotNull final String projectId) throws NullPointerException {
        @Nullable final AbstractEntity abstractEntity = projectRepository.findOne(projectId, userId);
        if(abstractEntity != null) {
            taskRepository.removeAllInProject(userId, projectId);
            projectRepository.remove(userId, projectId);
        }
    }

    @Override
    public Project persist(@NotNull final String userId, @NotNull final String name, @NotNull final String description,
                           @NotNull final String dateEndString, @NotNull final String status) throws NullPointerException, ParseException {
        @NotNull final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");
        @NotNull final Date dateEnd = simpleDateFormat.parse(dateEndString);
        @Nullable final AbstractEntity abstractEntity = projectRepository.findOneByName(userId, name);
        if (abstractEntity == null)
            return projectRepository.persist(userId, new Date(), dateEnd, description, name, Objects.requireNonNull(createStatus(status)));
        return null;

    }

    @Override
    public void merge(@NotNull final String userId, @NotNull final String projectId, @NotNull final String name,
                      @NotNull final String description, @NotNull final String dateEndString, @NotNull final String status) throws NullPointerException, ParseException {
        @NotNull final Project project = new Project();
        project.setId(projectId);
        project.setUserId(userId);
        project.setName(name);
        project.setDescription(description);
        project.setStatus(createStatus(status));

        @NotNull final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy"); //dd-MM-yyyy
        @NotNull final Date dateEnd = simpleDateFormat.parse(dateEndString);
        project.setDateEnd(dateEnd);
        @Nullable final AbstractEntity abstractEntity =
                projectRepository.findOne(project.getId(), Objects.requireNonNull(project.getUserId()));
        if(abstractEntity != null) {
            projectRepository.merge(project);
        }
    }

    @Override
    public void removeAll(@Nullable final String userId) throws SQLException {
        taskRepository.removeAll(Objects.requireNonNull(userId));
        projectRepository.removeAll(userId);
    }

    @Override
    @NotNull
    public List<Project> findAll(@NotNull final String userId) throws SQLException {
        return projectRepository.findAll(userId);
    }

    @NotNull
    @Override
    public List<Project> findAllSortByDateBegin(@Nullable final String userId) throws SQLException {
        return projectRepository.findAllSortByDateBegin(Objects.requireNonNull(userId));
    }

    @NotNull
    @Override
    public List<Project> findAllSortByDateEnd(@Nullable final String userId) throws SQLException {
        return projectRepository.findAllSortByDateEnd(Objects.requireNonNull(userId));
    }

    @NotNull
    @Override
    public List<Project> findAllSortByStatus(@NotNull final String userId) throws SQLException {
        return projectRepository.findAllSortByStatus(userId);
    }

    @Nullable
    @Override
    public Project findOneByName(@Nullable final String userId, @NotNull final String name) throws SQLException {
        return projectRepository.findOneByName(Objects.requireNonNull(userId), name);
    }

    @Nullable
    @Override
    public Project findOneByDescription(@Nullable final String userId, @NotNull final String description) throws SQLException {
        return projectRepository.findOneByDescription(Objects.requireNonNull(userId), description);
    }

    @Nullable
    private Status createStatus(String string) {
        switch (string) {
            case "Запланировано":
                return Status.SCHEDULED;
            case "В процессе":
                return Status.IN_PROCESS;
            case "Готово":
                return Status.COMPLETE;
        }
        return null;
    }
}
