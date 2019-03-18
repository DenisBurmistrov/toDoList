package ru.burmistrov.tm.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.burmistrov.tm.api.repository.IProjectRepository;
import ru.burmistrov.tm.api.repository.ITaskRepository;
import ru.burmistrov.tm.api.service.IProjectService;
import ru.burmistrov.tm.entity.AbstractEntity;
import ru.burmistrov.tm.entity.Project;
import ru.burmistrov.tm.entity.Task;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public final class ProjectService implements IProjectService {

    @NotNull
    private final IProjectRepository projectRepository;

    @NotNull
    private final ITaskRepository taskRepository;

    public ProjectService(@NotNull IProjectRepository projectRepository, @NotNull ITaskRepository taskRepository) {
        this.projectRepository = projectRepository;
        this.taskRepository = taskRepository;
    }

    public void removeProjectById(@NotNull String userId, @NotNull String projectId) throws NullPointerException {
        @NotNull final Project project = new Project();
        project.setUserId(userId);
        project.setId(projectId);
        AbstractEntity abstractEntity = projectRepository.findOne(project);
        System.out.println(abstractEntity);
        projectRepository.remove(project);
        Task task = new Task();
        task.setUserId(userId);
        task.setProjectId(projectId);
        taskRepository.removeAllInProject(task);
    }

    public Project createProject(@NotNull String userId, @NotNull String name, @NotNull String description, @NotNull String dateEndString) throws NullPointerException, ParseException {
        @NotNull final Project project = new Project();
        project.setUserId(userId);
        project.setName(name);
        project.setDescription(description);
        @NotNull final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");
        @NotNull final Date dateEnd = simpleDateFormat.parse(dateEndString);
        project.setDateEnd(dateEnd);
        AbstractEntity abstractEntity = projectRepository.findOne(project);
        if (abstractEntity == null)
            return projectRepository.persist(project);
        return null;

    }


    public void updateProjectById(@NotNull String userId, @NotNull String projectId, @NotNull String name, @NotNull String description,
                                  @NotNull String dateEndString) throws NullPointerException, ParseException {
        @NotNull final Project project = new Project();
        project.setId(projectId);
        project.setUserId(userId);
        project.setName(name);
        project.setDescription(description);
        @NotNull final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy"); //dd-MM-yyyy
        @NotNull final Date dateEnd = simpleDateFormat.parse(dateEndString);
        project.setDateEnd(dateEnd);
        AbstractEntity abstractEntity = projectRepository.findOne(project);
        if(abstractEntity != null) {
            projectRepository.merge(project);
        }
    }

    public void removeAllProjects(@Nullable String userId) {
        @NotNull final Project project = new Project();
        @NotNull final Task task = new Task();
        task.setUserId(userId);
        project.setUserId(userId);
        projectRepository.removeAll(project);
        taskRepository.removeAll(task);
    }

    @NotNull
    public List<Project> findAllProjects(@NotNull String userId) {
        @NotNull final Project project = new Project();
        project.setUserId(userId);
        return projectRepository.findAll(project);
    }

    @NotNull
    @Override
    public List<Project> findAllProjectsSortByDateBegin(@Nullable String userId) {
        Project project = new Project();
        project.setUserId(userId);
        return projectRepository.findAllSortByDateBegin(project);
    }

    @NotNull
    @Override
    public List<Project> findAllProjectsSortByDateEnd(@Nullable String userId) {
        @NotNull final Project project = new Project();
        project.setUserId(userId);
        return projectRepository.findAllSortByDateEnd(project);
    }

    @NotNull
    @Override
    public List<Project> findAllProjectsSortByStatus(@NotNull String userId) {
        @NotNull final Project project = new Project();
        project.setUserId(userId);
        return projectRepository.findAllSortByStatus(project);
    }

    @Nullable
    @Override
    public Project findProjectByName(@Nullable String userId, @NotNull String name) {
        @NotNull final Project project = new Project();
        project.setUserId(userId);
        project.setName(name);
        return projectRepository.findOneByName(project);
    }

    @Nullable
    @Override
    public Project findProjectByDescription(@Nullable String userId, @NotNull String description) {
        @NotNull final Project project = new Project();
        project.setUserId(userId);
        project.setDescription(description);
        return projectRepository.findOneByDescription(project);
    }
}