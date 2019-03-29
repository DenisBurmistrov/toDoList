package ru.burmistrov.tm.endpoint;

import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.burmistrov.tm.api.endpoint.IProjectEndpoint;
import ru.burmistrov.tm.api.loader.ServiceLocator;
import ru.burmistrov.tm.api.service.IProjectService;
import ru.burmistrov.tm.api.service.ISessionService;
import ru.burmistrov.tm.dto.ProjectDto;
import ru.burmistrov.tm.entity.Project;
import ru.burmistrov.tm.entity.Session;
import ru.burmistrov.tm.entity.User;

import javax.inject.Inject;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import java.io.IOException;
import java.text.ParseException;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

@NoArgsConstructor
@WebService
public class ProjectEndpoint implements IProjectEndpoint {

    @Inject
    private IProjectService projectService;

    @Inject
    private ISessionService sessionService;

    @WebMethod
    public void removeProjectById
            (@WebParam(name = "session") @NotNull final Session session, @WebParam(name = "userId") @NotNull final String userId,
             @WebParam(name = "projectId") @NotNull final String projectId) throws Exception {
        if (sessionService.validate(session)) {
            projectService.remove(userId, projectId);
        }
    }

    @WebMethod
    @Nullable
    public ProjectDto createProject
            (@WebParam(name = "session") @NotNull final Session session, @WebParam(name = "userId") @NotNull final String userId,
             @WebParam(name = "name") @NotNull final String name, @WebParam(name = "description") @NotNull final String description,
             @WebParam(name = "dateEnd") @NotNull final String dateEnd, @WebParam(name = "status") @NotNull final String status) throws Exception {
        if (sessionService.validate(session)) {
            Project project = projectService.persist(userId, name, description, dateEnd, status);
            if (project != null) {
                ProjectDto projectDto = new ProjectDto();
                projectDto.setId(Objects.requireNonNull(project).getId());
                projectDto.setName(Objects.requireNonNull(project).getName());
                projectDto.setDescription(Objects.requireNonNull(project).getDescription());
                projectDto.setDateBegin(Objects.requireNonNull(project).getDateBegin());
                projectDto.setDateEnd(Objects.requireNonNull(project).getDateEnd());
                projectDto.setStatus(Objects.requireNonNull(project).getStatus());
                return projectDto;
            }
        }
        return null;
    }

    @WebMethod
    public void updateProjectById
            (@WebParam(name = "session") @NotNull final Session session, @WebParam(name = "userId") @NotNull final String userId,
             @WebParam(name = "projectId") @NotNull final String projectId, @WebParam(name = "name") @NotNull final String name,
             @WebParam(name = "description") @NotNull final String description, @WebParam(name = "dateEnd") @NotNull final String dateEnd,
             @WebParam(name = "status") @NotNull final String status) throws Exception {
        if (sessionService.validate(session)) {
            projectService.merge(userId, projectId, name, description, dateEnd, status);
        }
    }

    @WebMethod
    public void removeAllProjects
            (@WebParam(name = "session") @NotNull final Session session, @WebParam(name = "userId") @NotNull final String userId) throws Exception {
        if (sessionService.validate(session)) {
            projectService.removeAll(userId);
        }
    }

    @WebMethod
    @Nullable
    public List<ProjectDto> findAllProjects
            (@WebParam(name = "session") @NotNull final Session session, @WebParam(name = "userId") @NotNull final String userId) throws Exception {
        if (sessionService.validate(session)) {

            @Nullable final List<Project> projects = projectService.findAll(userId);
            @NotNull final List<ProjectDto> projectDtos = new LinkedList<>();

            for (Project project : Objects.requireNonNull(projects)) {
                ProjectDto projectDto = new ProjectDto();
                projectDto.setId(Objects.requireNonNull(project).getId());
                projectDto.setName(Objects.requireNonNull(project).getName());
                projectDto.setDescription(Objects.requireNonNull(project).getDescription());
                projectDto.setDateBegin(Objects.requireNonNull(project).getDateBegin());
                projectDto.setDateEnd(Objects.requireNonNull(project).getDateEnd());
                projectDto.setStatus(Objects.requireNonNull(project).getStatus());
                projectDtos.add(projectDto);
            }
            return projectDtos;
        }
        return null;
    }

    @WebMethod
    @Nullable
    public List<ProjectDto> findAllProjectsSortByDateBegin
            (@WebParam(name = "session") @NotNull final Session session, @WebParam(name = "userId") @NotNull final String userId) throws Exception {
        if (sessionService.validate(session)) {

            @Nullable final List<Project> projects = projectService.findAllSortByDateBegin(userId);
            @NotNull final List<ProjectDto> projectDtos = new LinkedList<>();

            for (Project project : Objects.requireNonNull(projects)) {
                ProjectDto projectDto = new ProjectDto();
                projectDto.setId(Objects.requireNonNull(project).getId());
                projectDto.setName(Objects.requireNonNull(project).getName());
                projectDto.setDescription(Objects.requireNonNull(project).getDescription());
                projectDto.setDateBegin(Objects.requireNonNull(project).getDateBegin());
                projectDto.setDateEnd(Objects.requireNonNull(project).getDateEnd());
                projectDto.setStatus(Objects.requireNonNull(project).getStatus());
                projectDtos.add(projectDto);
            }
            return projectDtos;
        }
        return null;
    }

    @WebMethod
    @Nullable
    public List<ProjectDto> findAllProjectsSortByDateEnd
            (@WebParam(name = "session") @NotNull final Session session, @WebParam(name = "userId") @NotNull final String userId) throws Exception {
        if (sessionService.validate(session)) {
            @Nullable final List<Project> projects = projectService.findAllSortByDateEnd(userId);
            @NotNull final List<ProjectDto> projectDtos = new LinkedList<>();

            for (Project project : Objects.requireNonNull(projects)) {
                ProjectDto projectDto = new ProjectDto();
                projectDto.setId(Objects.requireNonNull(project).getId());
                projectDto.setName(Objects.requireNonNull(project).getName());
                projectDto.setDescription(Objects.requireNonNull(project).getDescription());
                projectDto.setDateBegin(Objects.requireNonNull(project).getDateBegin());
                projectDto.setDateEnd(Objects.requireNonNull(project).getDateEnd());
                projectDto.setStatus(Objects.requireNonNull(project).getStatus());
                projectDtos.add(projectDto);
            }
            return projectDtos;
        }
        return null;
    }

    @WebMethod
    @Nullable
    public List<ProjectDto> findAllProjectsSortByStatus
            (@WebParam(name = "session") @NotNull final Session session, @WebParam(name = "userId") @NotNull final String userId) throws Exception {
        if (sessionService.validate(session)) {
            @Nullable final List<Project> projects = projectService.findAllSortByStatus(userId);
            @NotNull final List<ProjectDto> projectDtos = new LinkedList<>();

            for (Project project : Objects.requireNonNull(projects)) {
                ProjectDto projectDto = new ProjectDto();
                projectDto.setId(Objects.requireNonNull(project).getId());
                projectDto.setName(Objects.requireNonNull(project).getName());
                projectDto.setDescription(Objects.requireNonNull(project).getDescription());
                projectDto.setDateBegin(Objects.requireNonNull(project).getDateBegin());
                projectDto.setDateEnd(Objects.requireNonNull(project).getDateEnd());
                projectDto.setStatus(Objects.requireNonNull(project).getStatus());
                projectDtos.add(projectDto);
            }
            return projectDtos;

        }
        return null;
    }

    @WebMethod
    @Nullable
    public ProjectDto findProjectByName
            (@WebParam(name = "session") @NotNull final Session session, @WebParam(name = "userId") @NotNull final String userId,
             @WebParam(name = "name") @NotNull final String name) throws Exception {
        if (sessionService.validate(session)) {
            Project project = projectService.findOneByName(userId, name);
            if (project != null) {
                ProjectDto projectDto = new ProjectDto();
                projectDto.setId(Objects.requireNonNull(project).getId());
                projectDto.setName(Objects.requireNonNull(project).getName());
                projectDto.setDescription(Objects.requireNonNull(project).getDescription());
                projectDto.setDateBegin(Objects.requireNonNull(project).getDateBegin());
                projectDto.setDateEnd(Objects.requireNonNull(project).getDateEnd());
                projectDto.setStatus(Objects.requireNonNull(project).getStatus());
                return projectDto;
            }
        }
        return null;
    }

    @WebMethod
    @Nullable
    public ProjectDto findProjectByDescription
            (@WebParam(name = "session") @NotNull final Session session, @WebParam(name = "userId") @NotNull final String userId,
             @WebParam(name = "description") @NotNull final String description) throws Exception {
        if (sessionService.validate(session)) {
            Project project = projectService.findOneByDescription(userId, description);
            if (project != null) {
                ProjectDto projectDto = new ProjectDto();
                projectDto.setId(Objects.requireNonNull(project).getId());
                projectDto.setName(Objects.requireNonNull(project).getName());
                projectDto.setDescription(Objects.requireNonNull(project).getDescription());
                projectDto.setDateBegin(Objects.requireNonNull(project).getDateBegin());
                projectDto.setDateEnd(Objects.requireNonNull(project).getDateEnd());
                projectDto.setStatus(Objects.requireNonNull(project).getStatus());
                return projectDto;
            }
        }
        return null;
    }
}
