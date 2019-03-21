package ru.burmistrov.tm.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.burmistrov.tm.api.repository.IProjectRepository;
import ru.burmistrov.tm.api.repository.ITaskRepository;
import ru.burmistrov.tm.api.endpoint.IProjectEndpoint;
import ru.burmistrov.tm.api.service.IProjectService;
import ru.burmistrov.tm.entity.AbstractEntity;
import ru.burmistrov.tm.entity.Project;
import ru.burmistrov.tm.entity.Task;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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
        @NotNull final Project project = new Project();
        project.setUserId(userId);
        project.setId(projectId);
        @Nullable final AbstractEntity abstractEntity = projectRepository.findOne(project);
        if(abstractEntity != null) {
            projectRepository.remove(project);
            Task task = new Task();
            task.setUserId(userId);
            task.setProjectId(projectId);
            taskRepository.removeAllInProject(task);
        }
    }

    @Override
    public Project persist(@NotNull final String userId, @NotNull final String name, @NotNull final String description,
                           @NotNull final String dateEndString) throws NullPointerException, ParseException, IOException, NoSuchAlgorithmException {
        @NotNull final Project project = new Project();
        project.setUserId(userId);
        project.setName(name);
        project.setDescription(description);
        @NotNull final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");
        @NotNull final Date dateEnd = simpleDateFormat.parse(dateEndString);
        project.setDateEnd(dateEnd);
        @Nullable final AbstractEntity abstractEntity = projectRepository.findOne(project);
        if (abstractEntity == null)
            return projectRepository.persist(project);
        return null;

    }

    @Override
    public void merge(@NotNull final String userId, @NotNull final String projectId, @NotNull final String name,
                      @NotNull final String description, @NotNull final String dateEndString) throws NullPointerException, ParseException {
        @NotNull final Project project = new Project();
        project.setId(projectId);
        project.setUserId(userId);
        project.setName(name);
        project.setDescription(description);
        @NotNull final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy"); //dd-MM-yyyy
        @NotNull final Date dateEnd = simpleDateFormat.parse(dateEndString);
        project.setDateEnd(dateEnd);
        @Nullable final AbstractEntity abstractEntity = projectRepository.findOne(project);
        if(abstractEntity != null) {
            projectRepository.merge(project);
        }
    }

    @Override
    public void removeAll(@Nullable final String userId) {
        @NotNull final Project project = new Project();
        @NotNull final Task task = new Task();
        task.setUserId(userId);
        project.setUserId(userId);
        projectRepository.removeAll(project);
        taskRepository.removeAll(task);
    }

    @Override
    @NotNull
    public List<Project> findAll(@NotNull final String userId) {
        @NotNull final Project project = new Project();
        project.setUserId(userId);
        return projectRepository.findAll(project);
    }

    @NotNull
    @Override
    public List<Project> findAllSortByDateBegin(@Nullable final String userId) {
        Project project = new Project();
        project.setUserId(userId);
        return projectRepository.findAllSortByDateBegin(project);
    }

    @NotNull
    @Override
    public List<Project> findAllSortByDateEnd(@Nullable final String userId) {
        @NotNull final Project project = new Project();
        project.setUserId(userId);
        return projectRepository.findAllSortByDateEnd(project);
    }

    @NotNull
    @Override
    public List<Project> findAllSortByStatus(@NotNull final String userId) {
        @NotNull final Project project = new Project();
        project.setUserId(userId);
        return projectRepository.findAllSortByStatus(project);
    }

    @Nullable
    @Override
    public Project findOneByName(@Nullable final String userId, @NotNull final String name) {
        @NotNull final Project project = new Project();
        project.setUserId(userId);
        project.setName(name);
        return projectRepository.findOneByName(project);
    }

    @Nullable
    @Override
    public Project findOneByDescription(@Nullable final String userId, @NotNull final String description) {
        @NotNull final Project project = new Project();
        project.setUserId(userId);
        project.setDescription(description);
        return projectRepository.findOneByDescription(project);
    }
}
