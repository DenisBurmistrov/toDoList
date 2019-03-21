package ru.burmistrov.tm.endpoint;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.burmistrov.tm.api.endpoint.ITaskEndpoint;
import ru.burmistrov.tm.api.loader.ServiceLocator;
import ru.burmistrov.tm.entity.Session;
import ru.burmistrov.tm.entity.Task;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;

@WebService
public class TaskEndpoint implements ITaskEndpoint {

    private ServiceLocator serviceLocator;

    public TaskEndpoint(ServiceLocator serviceLocator) {
        this.serviceLocator = serviceLocator;
    }

    @WebMethod
    public void updateTaskById(@NotNull Session session, @NotNull String userId, @NotNull String projectId, @NotNull String taskId,
                               @NotNull String newName, @NotNull String description, @NotNull String dateEnd) throws ParseException, CloneNotSupportedException {
        if (serviceLocator.getSessionService().validate(session)) {
            serviceLocator.getTaskService().merge(userId, projectId, taskId, newName, description, dateEnd);
        }
    }

    @WebMethod
    @Nullable
    public Task createTask(@NotNull Session session, @NotNull String userId, @NotNull String projectId, @NotNull String name,
                           @NotNull String description, @NotNull String dateEnd) throws ParseException, CloneNotSupportedException, IOException {
        if (serviceLocator.getSessionService().validate(session)) {
            return serviceLocator.getTaskService().persist(userId, projectId, name, description, dateEnd);
        }
        return null;
    }

    @WebMethod
    @NotNull
    public List<Task> findAllTasks(@NotNull Session session, @NotNull String userId) throws CloneNotSupportedException {
        if (serviceLocator.getSessionService().validate(session)) {
            return serviceLocator.getTaskService().findAll(userId);
        }
        return null;
    }

    @WebMethod
    public void removeAllTasksInProject(@NotNull Session session, @NotNull String userId, @NotNull String projectId) throws CloneNotSupportedException {
        if (serviceLocator.getSessionService().validate(session)) {
            serviceLocator.getTaskService().removeAllInProject(userId, projectId);
        }
    }

    @WebMethod
    public void removeTaskById(@NotNull Session session, @NotNull String userId, @NotNull String taskId) throws CloneNotSupportedException {
        if (serviceLocator.getSessionService().validate(session)) {
            serviceLocator.getTaskService().remove(userId, taskId);
        }
    }

    @WebMethod
    public void removeAllTasks(@NotNull Session session, @NotNull String userId) throws CloneNotSupportedException {
        if (serviceLocator.getSessionService().validate(session)) {
            serviceLocator.getTaskService().removeAll(userId);
        }
    }

    @WebMethod
    @NotNull
    public List<Task> findAllTasksSortByDateBegin(@NotNull Session session, @NotNull String userId) throws CloneNotSupportedException {
        if (serviceLocator.getSessionService().validate(session)) {
            return serviceLocator.getTaskService().findAllSortByDateBegin(userId);
        }
        return null;
    }

    @WebMethod
    @NotNull
    public List<Task> findAllTasksSortByDateEnd(@NotNull Session session, @NotNull String userId) throws CloneNotSupportedException {
        if (serviceLocator.getSessionService().validate(session)) {
            return serviceLocator.getTaskService().findAllSortByDateEnd(userId);
        }
        return null;
    }

    @NotNull
    public List<Task> findAllTasksSortByStatus(@NotNull Session session, @NotNull String userId) throws CloneNotSupportedException {
        if (serviceLocator.getSessionService().validate(session)) {
            return serviceLocator.getTaskService().findAllSortByStatus(userId);
        }
        return null;
    }

    @WebMethod
    @Nullable
    public Task findTaskByName(@NotNull Session session, @NotNull String userId, String name) throws CloneNotSupportedException {
        if (serviceLocator.getSessionService().validate(session)) {
            return serviceLocator.getTaskService().findOneByName(userId, name);
        }
        return null;
    }

    @WebMethod
    @Nullable
    public Task findTaskByDescription(@NotNull Session session, @NotNull String userId, String description) throws CloneNotSupportedException {
        if (serviceLocator.getSessionService().validate(session)) {
            return serviceLocator.getTaskService().findOneByDescription(userId, description);
        }
        return null;
    }

    @WebMethod
    @NotNull
    public List<Task> findAllTasksInProject(@NotNull Session session, @NotNull String userId, @NotNull String projectId) throws CloneNotSupportedException {
        if (serviceLocator.getSessionService().validate(session)) {
            return serviceLocator.getTaskService().findAllInProject(userId, projectId);
        }
        return null;
    }
}
