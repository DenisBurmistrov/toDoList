package ru.burmistrov.tm.endpoint;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.burmistrov.tm.api.loader.ServiceLocator;
import ru.burmistrov.tm.entity.AbstractEntity;

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
        serviceLocator.getTaskService().updateTaskById(userId, projectId, taskId, newName, description, dateEnd);
    }

    @WebMethod
    @Nullable
    public AbstractEntity createTask(@NotNull String userId, @NotNull String projectId, @NotNull String name,
                                  @NotNull String description, @NotNull String dateEnd) throws ParseException {
        return serviceLocator.getTaskService().createTask(userId, projectId, name, description, dateEnd);
    }

    @WebMethod
    @NotNull
    public List findAllTasks(@NotNull String userId) {
        return serviceLocator.getTaskService().findAllTasks(userId);
    }

    @WebMethod
    public void removeAllTasksInProject(@NotNull String userId, @NotNull String projectId) {
        serviceLocator.getTaskService().removeAllTasksInProject(userId, projectId);
    }

    @WebMethod
    public void removeTaskById(@NotNull String userId, @NotNull String taskId) {
        serviceLocator.getTaskService().removeTaskById(userId, taskId);
    }

    @WebMethod
    public void removeAllTasks(@NotNull String userId) {
        serviceLocator.getTaskService().removeAllTasks(userId);
    }

    @WebMethod
    @NotNull
    public List findAllTasksSortByDateBegin(@NotNull String userId) {
        return serviceLocator.getTaskService().findAllTasksSortByDateBegin(userId);
    }

    @WebMethod
    @NotNull
    public List findAllTasksSortByDateEnd(@NotNull String userId) {
        return serviceLocator.getTaskService().findAllTasksSortByDateEnd(userId);
    }

    @NotNull
    public List findAllTasksSortByStatus(@NotNull String userId) {
        return serviceLocator.getTaskService().findAllTasksSortByStatus(userId);
    }

    @WebMethod
    @Nullable
    public AbstractEntity findTaskByName(@NotNull String userId, String name) {
        return serviceLocator.getTaskService().findTaskByName(userId, name);
    }

    @WebMethod
    @Nullable
    public AbstractEntity findTaskByDescription(@NotNull String userId, String description) {
        return serviceLocator.getTaskService().findTaskByDescription(userId, description);
    }

    @WebMethod
    @NotNull
    public List findAllTasksInProject(@NotNull String userId, @NotNull String projectId) {
        return serviceLocator.getTaskService().findAllTasksInProject(userId, projectId);
    }
}
