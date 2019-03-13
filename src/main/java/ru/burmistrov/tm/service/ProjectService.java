package ru.burmistrov.tm.service;

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

    public ProjectService(@Nullable IProjectRepository<AbstractEntity> projectRepository,@Nullable ITaskRepository<AbstractEntity> taskRepository) {
        this.projectRepository = projectRepository;
        this.taskRepository = taskRepository;
    }

    public void remove(@Nullable String userId, @Nullable String projectId) {
            Project project = new Project();
            project.setUserId(userId);
            project.setId(projectId);
            if(projectRepository != null && taskRepository != null) {
                AbstractEntity abstractEntity = projectRepository.findOne(project);
                if (abstractEntity != null) {
                    projectRepository.remove(project);
                    Task task = new Task();
                    task.setUserId(userId);
                    task.setProjectId(projectId);
                    taskRepository.removeAllInProject(task);
                }
            }

    }

    public Project persist(@Nullable String userId, @Nullable String name, @Nullable String description, @Nullable String dateEndString) {
        try {
            Project project = new Project();
            project.setUserId(userId);
            project.setName(name);
            project.setDescription(description);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy"); //dd-MM-yyyy
            Date dateEnd = simpleDateFormat.parse(dateEndString);
            project.setDateEnd(dateEnd);
            if(projectRepository != null) {
                AbstractEntity abstractEntity = projectRepository.findOne(project);
                if (abstractEntity == null) {
                    return (Project) projectRepository.persist(project);
                }
            }
            return null;
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }

    }

    public void merge(@Nullable String userId, @Nullable String projectId, @Nullable String name, @Nullable String description, @Nullable String dateEndString) {
       try {
           Project project = new Project();
           project.setId(projectId);
           project.setUserId(userId);
           project.setName(name);
           project.setDescription(description);
           SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy"); //dd-MM-yyyy
           Date dateEnd = simpleDateFormat.parse(dateEndString);
           project.setDateEnd(dateEnd);
           if (projectRepository != null) {
               AbstractEntity abstractEntity = projectRepository.findOne(project);
               if (abstractEntity != null) {
                   projectRepository.merge(project);
               }
           }
       }catch (ParseException e) {
           e.printStackTrace();
       }
    }

    public void removeAll(@Nullable String userId) {
        Project project = new Project();
        project.setUserId(userId);
        if(projectRepository != null && taskRepository != null) {
            projectRepository.removeAll(project);
            taskRepository.removeAll(project);
        }
    }

    @Nullable
    public List<AbstractEntity> findAll(String userId) {
        Project project = new Project();
        project.setUserId(userId);
        if (projectRepository != null) {
            return projectRepository.findAll(project);
        }
        return null;
    }

    @Nullable
    @Override
    public List<AbstractEntity> findAllSortByDateBegin(@Nullable String userId) {
        Project project = new Project();
        project.setUserId(userId);
        if (projectRepository != null) {
            return projectRepository.findAllSortByDateBegin(project);
        }
        return null;
    }


}
