package ru.burmistrov.tm.endpoint;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.burmistrov.tm.api.endpoint.IProjectEndpoint;
import ru.burmistrov.tm.api.loader.ServiceLocator;
import ru.burmistrov.tm.entity.Project;
import ru.burmistrov.tm.entity.Session;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;

@WebService
public class ProjectEndpoint implements IProjectEndpoint {

    private ServiceLocator serviceLocator;

    public ProjectEndpoint() {
    }

    public ProjectEndpoint(ServiceLocator serviceLocator) {
        this.serviceLocator = serviceLocator;
    }

    @WebMethod
    public void removeProjectById(@NotNull Session session, @NotNull String userId, @NotNull String projectId) throws CloneNotSupportedException {
        if (serviceLocator.getSessionService().validate(session)) {
            serviceLocator.getProjectService().remove(userId, projectId);
        }
    }

    @WebMethod
    @Nullable
    public Project createProject(@NotNull Session session, @NotNull String userId, @NotNull String name,
                                  @NotNull String description, @NotNull String dateEnd) throws ParseException, CloneNotSupportedException, IOException {
        if (serviceLocator.getSessionService().validate(session)) {
            return serviceLocator.getProjectService().persist(userId, name, description, dateEnd);
        }
        return null;
    }

    @WebMethod
    public void updateProjectById(@NotNull Session session, @NotNull String userId, @NotNull String projectId,
                                  @NotNull String name, @NotNull String description, @NotNull String dateEnd) throws ParseException, CloneNotSupportedException {
        if (serviceLocator.getSessionService().validate(session)) {
            serviceLocator.getProjectService().merge(userId, projectId, name, description, dateEnd);
        }
    }

    @WebMethod
    public void removeAllProjects(@NotNull Session session, @NotNull String userId) throws CloneNotSupportedException {
        if (serviceLocator.getSessionService().validate(session)) {
            serviceLocator.getProjectService().removeAll(userId);
        }
    }

    @WebMethod
    @NotNull
    public List<Project> findAllProjects(@NotNull Session session, @NotNull String userId) throws CloneNotSupportedException {
        if (serviceLocator.getSessionService().validate(session)) {
            return serviceLocator.getProjectService().findAll(userId);
        }
        return null;
    }

    @WebMethod
    @NotNull
    public List<Project> findAllProjectsSortByDateBegin(@NotNull Session session, @NotNull String userId) throws CloneNotSupportedException {
        if (serviceLocator.getSessionService().validate(session)) {
            return serviceLocator.getProjectService().findAllSortByDateBegin(userId);
        }
        return null;
    }

    @WebMethod
    @NotNull
    public List<Project> findAllProjectsSortByDateEnd(@NotNull Session session, @NotNull String userId) throws CloneNotSupportedException {
        if (serviceLocator.getSessionService().validate(session)) {
            return serviceLocator.getProjectService().findAllSortByDateEnd(userId);
        }
        return null;
    }

    @WebMethod
    @NotNull
    public List<Project> findAllProjectsSortByStatus(@NotNull Session session, @NotNull String userId) throws CloneNotSupportedException {
        if (serviceLocator.getSessionService().validate(session)) {
            return serviceLocator.getProjectService().findAllSortByStatus(userId);
        }
        return null;
    }

    @WebMethod
    @Nullable
    public Project findProjectByName(@NotNull Session session, @NotNull String userId, @NotNull String name) throws CloneNotSupportedException {
        if (serviceLocator.getSessionService().validate(session)) {
            return serviceLocator.getProjectService().findOneByName(userId, name);
        }
        return null;
    }

    @WebMethod
    @Nullable
    public Project findProjectByDescription(@NotNull Session session, @NotNull String userId, @NotNull String description) throws CloneNotSupportedException {
        if (serviceLocator.getSessionService().validate(session)) {
            return serviceLocator.getProjectService().findOneByDescription(userId, description);
        }
        return null;
    }
}
