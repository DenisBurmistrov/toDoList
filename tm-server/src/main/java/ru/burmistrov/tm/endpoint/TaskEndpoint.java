package ru.burmistrov.tm.endpoint;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.burmistrov.tm.api.loader.ServiceLocator;
import ru.burmistrov.tm.entity.Task;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.text.ParseException;
import java.util.List;

@WebService(endpointInterface = "ru.burmistrov.tm.api.service.ITaskService")
public class TaskEndpoint {

    private ServiceLocator serviceLocator;

    public TaskEndpoint(ServiceLocator serviceLocator) {
        this.serviceLocator = serviceLocator;
    }

    @WebMethod
    public void updateTaskById(@NotNull String userId, @NotNull String projectId, @NotNull String taskId,
                      @NotNull String newName, @NotNull String description, @NotNull String dateEnd) throws ParseException {
        serviceLocator.getTaskService().merge(userId, projectId, taskId, newName, description, dateEnd);
    }

    @WebMethod
    @Nullable
    public Task createTask(@NotNull String userId, @NotNull String projectId, @NotNull String name,
                           @NotNull String description, @NotNull String dateEnd) throws ParseException {
        return serviceLocator.getTaskService().persist(userId, projectId, name, description, dateEnd);
    }

    @WebMethod
    @NotNull
    public List<Task> findAllTasks(@NotNull String userId) {
        return serviceLocator.getTaskService().findAll(userId);
    }

    @WebMethod
    public void removeAllTasksInProject(@NotNull String userId, @NotNull String projectId) {
        serviceLocator.getTaskService().removeAllInProject(userId, projectId);
    }

    @WebMethod
    public void removeTaskById(@NotNull String userId, @NotNull String taskId) {
        serviceLocator.getTaskService().remove(userId, taskId);
    }

    @WebMethod
    public void removeAllTasks(@NotNull String userId) {
        serviceLocator.getTaskService().removeAll(userId);
    }

    @WebMethod
    @NotNull
    public List<Task> findAllTasksSortByDateBegin(@NotNull String userId) {
        return serviceLocator.getTaskService().findAllSortByDateBegin(userId);
    }

    @WebMethod
    @NotNull
    public List<Task> findAllTasksSortByDateEnd(@NotNull String userId) {
        return serviceLocator.getTaskService().findAllSortByDateEnd(userId);
    }

    @NotNull
    public List<Task> findAllTasksSortByStatus(@NotNull String userId) {
        return serviceLocator.getTaskService().findAllSortByStatus(userId);
    }

    @WebMethod
    @Nullable
    public Task findTaskByName(@NotNull String userId, String name) {
        return serviceLocator.getTaskService().findOneByName(userId, name);
    }

    @WebMethod
    @Nullable
    public Task findTaskByDescription(@NotNull String userId, String description) {
        return serviceLocator.getTaskService().findOneByDescription(userId, description);
    }

    @WebMethod
    @NotNull
    public List<Task> findAllTasksInProject(@NotNull String userId, @NotNull String projectId) {
        return serviceLocator.getTaskService().findAllInProject(userId, projectId);
    }
}
