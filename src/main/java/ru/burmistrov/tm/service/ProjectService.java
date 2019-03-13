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

public final class ProjectService extends AbstractService implements IProjectService {

    @Nullable
    private final IProjectRepository<AbstractEntity> projectRepository;

    @Nullable
    private final ITaskRepository<AbstractEntity> taskRepository;

    public ProjectService(@Nullable IProjectRepository<AbstractEntity> projectRepository, @Nullable ITaskRepository<AbstractEntity> taskRepository) {
        this.projectRepository = projectRepository;
        this.taskRepository = taskRepository;
    }

    public void remove(@Nullable String userId, @Nullable String projectId) throws NullPointerException {
        Project project = new Project();
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

    public Project persist(@Nullable String userId, @Nullable String name, @Nullable String description, @Nullable String dateEndString) throws NullPointerException, ParseException {
        Project project = new Project();
        project.setUserId(userId);
        project.setName(name);
        project.setDescription(description);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");
        Date dateEnd = simpleDateFormat.parse(dateEndString);
        project.setDateEnd(dateEnd);
        AbstractEntity abstractEntity = projectRepository.findOne(project);
        return (Project) projectRepository.persist(abstractEntity);
    }


    public void merge(@Nullable String userId, @Nullable String projectId, @Nullable String name, @Nullable String description, @Nullable String dateEndString) throws NullPointerException, ParseException {
        Project project = new Project();
        project.setId(projectId);
        project.setUserId(userId);
        project.setName(name);
        project.setDescription(description);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy"); //dd-MM-yyyy
        Date dateEnd = simpleDateFormat.parse(dateEndString);
        project.setDateEnd(dateEnd);
        AbstractEntity abstractEntity = projectRepository.findOne(project);
        projectRepository.merge(abstractEntity);
    }

    public void removeAll(@Nullable String userId) {
        Project project = new Project();
        project.setUserId(userId);
        projectRepository.removeAll(project);
        taskRepository.removeAll(project);
    }

    @Nullable
    public List<AbstractEntity> findAll(String userId) {
        Project project = new Project();
        project.setUserId(userId);
        return projectRepository.findAll(project);
    }

    @NotNull
    @Override
    public List<AbstractEntity> findAllSortByDateBegin(@Nullable String userId) {
        Project project = new Project();
        project.setUserId(userId);
        return projectRepository.findAllSortByDateBegin(project);
    }

    @NotNull
    @Override
    public List findAllSortByDateEnd(@Nullable String userId) {
        Project project = new Project();
        project.setUserId(userId);
        return projectRepository.findAllSortByDateEnd(project);
    }

    @NotNull
    @Override
    public List findAllSortByStatus(@NotNull String userId) {
        Project project = new Project();
        project.setUserId(userId);
        return projectRepository.findAllSortByStatus(project);
    }

    @Nullable
    @Override
    public AbstractEntity findOneByName(@Nullable String userId, String name) {
        Project project = new Project();
        project.setUserId(userId);
        project.setName(name);
        return projectRepository.findOneByName(project);
    }

    @Nullable
    @Override
    public AbstractEntity findOneByDescription(@Nullable String userId, String description) {
        Project project = new Project();
        project.setUserId(userId);
        project.setDescription(description);
        return projectRepository.findOneByDescription(project);
    }
}
