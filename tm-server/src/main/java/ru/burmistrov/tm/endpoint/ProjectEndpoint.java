package ru.burmistrov.tm.endpoint;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.burmistrov.tm.api.loader.ServiceLocator;
import ru.burmistrov.tm.entity.Project;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.text.ParseException;
import java.util.List;

@WebService(endpointInterface = "ru.burmistrov.tm.api.service.IProjectService")
public class ProjectEndpoint{

    private ServiceLocator serviceLocator;

    public ProjectEndpoint() {
    }

    public ProjectEndpoint(ServiceLocator serviceLocator) {
        this.serviceLocator = serviceLocator;
    }

    @WebMethod
    public void removeProjectById(@NotNull String userId, @NotNull String projectId) {
        serviceLocator.getProjectService().remove(userId, projectId);
    }

    @WebMethod
    @Nullable
    public Project createProject(@NotNull String userId, @NotNull String name,
                                  @NotNull String description, @NotNull String dateEnd) throws ParseException {
        return serviceLocator.getProjectService().persist(userId, name, description, dateEnd);
    }

    @WebMethod
    public void updateProjectById(@NotNull String userId, @NotNull String projectId,
                                  @NotNull String name, @NotNull String description, @NotNull String dateEnd) throws ParseException {
        serviceLocator.getProjectService().merge(userId, projectId, name, description, dateEnd);

    }

    @WebMethod
    public void removeAllProjects(@NotNull String userId) {
        serviceLocator.getProjectService().removeAll(userId);
    }

    @WebMethod
    @NotNull
    public List<Project> findAllProjects(@NotNull String userId) {
        return serviceLocator.getProjectService().findAll(userId);
    }

    @WebMethod
    @NotNull
    public List<Project> findAllProjectsSortByDateBegin(@NotNull String userId) {
        return serviceLocator.getProjectService().findAllSortByDateBegin(userId);
    }

    @WebMethod
    @NotNull
    public List<Project> findAllProjectsSortByDateEnd(@NotNull String userId) {
        return serviceLocator.getProjectService().findAllSortByDateEnd(userId);
    }

    @WebMethod
    @NotNull
    public List<Project> findAllProjectsSortByStatus(@NotNull String userId) {
        return serviceLocator.getProjectService().findAllSortByStatus(userId);
    }

    @WebMethod
    @Nullable
    public Project findProjectByName(@NotNull String userId, @NotNull String name) {
        return serviceLocator.getProjectService().findOneByName(userId, name);
    }

    @WebMethod
    @Nullable
    public Project findProjectByDescription(@NotNull String userId, @NotNull String description) {
        return serviceLocator.getProjectService().findOneByDescription(userId, description);
    }
}
