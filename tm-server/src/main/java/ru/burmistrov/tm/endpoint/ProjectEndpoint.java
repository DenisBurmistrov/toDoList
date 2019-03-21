package ru.burmistrov.tm.endpoint;

import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.burmistrov.tm.api.endpoint.IProjectEndpoint;
import ru.burmistrov.tm.api.loader.ServiceLocator;
import ru.burmistrov.tm.entity.Project;
import ru.burmistrov.tm.entity.Session;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;

@NoArgsConstructor
@WebService
public class ProjectEndpoint implements IProjectEndpoint {

    private ServiceLocator serviceLocator;

    public ProjectEndpoint(@NotNull final ServiceLocator serviceLocator) {
        this.serviceLocator = serviceLocator;
    }

    @WebMethod
    public void removeProjectById
            (@WebParam(name = "session") @NotNull final Session session, @WebParam(name = "userId") @NotNull final String userId,
             @WebParam(name = "projectId") @NotNull final String projectId) throws Exception {
        if (serviceLocator.getSessionService().validate(session)) {
            serviceLocator.getProjectService().remove(userId, projectId);
        }
    }

    @WebMethod
    @Nullable
    public Project createProject
            (@WebParam(name = "session") @NotNull final Session session, @WebParam(name = "userId") @NotNull final String userId,
             @WebParam(name = "name") @NotNull final String name, @WebParam(name = "description") @NotNull final String description,
             @WebParam(name = "dateEnd") @NotNull final String dateEnd) throws Exception {
        if (serviceLocator.getSessionService().validate(session)) {
            return serviceLocator.getProjectService().persist(userId, name, description, dateEnd);
        }
        return null;
    }

    @WebMethod
    public void updateProjectById
            (@WebParam(name = "session") @NotNull final Session session, @WebParam(name = "userId") @NotNull final String userId,
             @WebParam(name = "projectId") @NotNull final String projectId, @WebParam(name = "name") @NotNull final String name,
             @WebParam(name = "description") @NotNull final String description, @WebParam(name = "dateEnd") @NotNull final String dateEnd) throws Exception {
        if (serviceLocator.getSessionService().validate(session)) {
            serviceLocator.getProjectService().merge(userId, projectId, name, description, dateEnd);
        }
    }

    @WebMethod
    public void removeAllProjects
            (@WebParam(name = "session") @NotNull final Session session, @WebParam(name = "userId") @NotNull final String userId) throws Exception {
        if (serviceLocator.getSessionService().validate(session)) {
            serviceLocator.getProjectService().removeAll(userId);
        }
    }

    @WebMethod
    @Nullable
    public List<Project> findAllProjects
            (@WebParam(name = "session") @NotNull final Session session, @WebParam(name = "userId") @NotNull final String userId) throws Exception {
        if (serviceLocator.getSessionService().validate(session)) {
            return serviceLocator.getProjectService().findAll(userId);
        }
        return null;
    }

    @WebMethod
    @Nullable
    public List<Project> findAllProjectsSortByDateBegin
            (@WebParam(name = "session") @NotNull final Session session, @WebParam(name = "userId") @NotNull final String userId) throws Exception {
        if (serviceLocator.getSessionService().validate(session)) {
            return serviceLocator.getProjectService().findAllSortByDateBegin(userId);
        }
        return null;
    }

    @WebMethod
    @Nullable
    public List<Project> findAllProjectsSortByDateEnd
            (@WebParam(name = "session") @NotNull final Session session, @WebParam(name = "userId") @NotNull final String userId) throws Exception {
        if (serviceLocator.getSessionService().validate(session)) {
            return serviceLocator.getProjectService().findAllSortByDateEnd(userId);
        }
        return null;
    }

    @WebMethod
    @Nullable
    public List<Project> findAllProjectsSortByStatus
            (@WebParam(name = "session") @NotNull final Session session, @WebParam(name = "userId") @NotNull final String userId) throws Exception {
        if (serviceLocator.getSessionService().validate(session)) {
            return serviceLocator.getProjectService().findAllSortByStatus(userId);
        }
        return null;
    }

    @WebMethod
    @Nullable
    public Project findProjectByName
            (@WebParam(name = "session") @NotNull final Session session, @WebParam(name = "userId") @NotNull final String userId,
             @WebParam(name = "name") @NotNull final String name) throws Exception {
        if (serviceLocator.getSessionService().validate(session)) {
            return serviceLocator.getProjectService().findOneByName(userId, name);
        }
        return null;
    }

    @WebMethod
    @Nullable
    public Project findProjectByDescription
            (@WebParam(name = "session") @NotNull final Session session, @WebParam(name = "userId") @NotNull final String userId,
             @WebParam(name = "description") @NotNull final String description) throws Exception {
        if (serviceLocator.getSessionService().validate(session)) {
            return serviceLocator.getProjectService().findOneByDescription(userId, description);
        }
        return null;
    }
}
