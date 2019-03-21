package ru.burmistrov.tm.endpoint;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.burmistrov.tm.api.endpoint.ITaskEndpoint;
import ru.burmistrov.tm.api.loader.ServiceLocator;
import ru.burmistrov.tm.entity.Session;
import ru.burmistrov.tm.entity.Task;

import javax.jws.WebMethod;
import javax.jws.WebParam;
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
    public void updateTaskById
            (@WebParam(name = "session") @NotNull Session session, @WebParam(name = "userId") @NotNull String userId,
             @WebParam(name = "projectId") @NotNull String projectId, @WebParam(name = "taskId") @NotNull String taskId,
             @WebParam(name = "newName") @NotNull String newName, @WebParam(name = "description") @NotNull String description,
             @WebParam(name = "dateEnd") @NotNull String dateEnd) throws Exception {
        if (serviceLocator.getSessionService().validate(session)) {
            serviceLocator.getTaskService().merge(userId, projectId, taskId, newName, description, dateEnd);
        }
    }

    @WebMethod
    @Nullable
    public Task createTask
            (@WebParam(name = "session") @NotNull Session session, @NotNull @WebParam(name = "userId") String userId,
             @WebParam(name = "projectId") @NotNull String projectId, @WebParam(name = "name") @NotNull String name,
             @WebParam(name = "description") @NotNull String description, @WebParam(name = "dateEnd") @NotNull String dateEnd) throws Exception {
        if (serviceLocator.getSessionService().validate(session)) {
            return serviceLocator.getTaskService().persist(userId, projectId, name, description, dateEnd);
        }
        return null;
    }

    @WebMethod
    @Nullable
    public List<Task> findAllTasks
            (@WebParam(name = "session") @NotNull Session session, @WebParam(name = "userId") @NotNull String userId) throws Exception {
        if (serviceLocator.getSessionService().validate(session)) {
            return serviceLocator.getTaskService().findAll(userId);
        }
        return null;
    }

    @WebMethod
    public void removeAllTasksInProject
            (@WebParam(name = "session") @NotNull Session session, @WebParam(name = "userId") @NotNull String userId,
             @WebParam(name = "projectId") @NotNull String projectId) throws Exception {
        if (serviceLocator.getSessionService().validate(session)) {
            serviceLocator.getTaskService().removeAllInProject(userId, projectId);
        }
    }

    @WebMethod
    public void removeTaskById
            (@WebParam(name = "session") @NotNull Session session, @WebParam(name = "userId") @NotNull String userId,
             @WebParam(name = "taskId") @NotNull String taskId) throws Exception {
        if (serviceLocator.getSessionService().validate(session)) {
            serviceLocator.getTaskService().remove(userId, taskId);
        }
    }

    @WebMethod
    public void removeAllTasks
            (@WebParam(name = "session") @NotNull Session session, @WebParam(name = "userId") @NotNull String userId) throws Exception {
        if (serviceLocator.getSessionService().validate(session)) {
            serviceLocator.getTaskService().removeAll(userId);
        }
    }

    @WebMethod
    @Nullable
    public List<Task> findAllTasksSortByDateBegin
            (@WebParam(name = "session") @NotNull Session session, @WebParam(name = "userId") @NotNull String userId) throws Exception {
        if (serviceLocator.getSessionService().validate(session)) {
            return serviceLocator.getTaskService().findAllSortByDateBegin(userId);
        }
        return null;
    }

    @WebMethod
    @Nullable
    public List<Task> findAllTasksSortByDateEnd
            (@WebParam(name = "session") @NotNull Session session, @WebParam(name = "userId") @NotNull String userId) throws Exception {
        if (serviceLocator.getSessionService().validate(session)) {
            return serviceLocator.getTaskService().findAllSortByDateEnd(userId);
        }
        return null;
    }

    @Nullable
    public List<Task> findAllTasksSortByStatus
            (@WebParam(name = "session") @NotNull Session session, @WebParam(name = "userId") @NotNull String userId) throws Exception {
        if (serviceLocator.getSessionService().validate(session)) {
            return serviceLocator.getTaskService().findAllSortByStatus(userId);
        }
        return null;
    }

    @WebMethod
    @Nullable
    public Task findTaskByName
            (@WebParam(name = "session") @NotNull Session session, @WebParam(name = "userId") @NotNull String userId,
             @WebParam(name = "name") @NotNull String name) throws Exception {
        if (serviceLocator.getSessionService().validate(session)) {
            return serviceLocator.getTaskService().findOneByName(userId, name);
        }
        return null;
    }

    @WebMethod
    @Nullable
    public Task findTaskByDescription
            (@WebParam(name = "session") @NotNull Session session, @WebParam @NotNull String userId, String description) throws Exception {
        if (serviceLocator.getSessionService().validate(session)) {
            return serviceLocator.getTaskService().findOneByDescription(userId, description);
        }
        return null;
    }

    @WebMethod
    @Nullable
    public List<Task> findAllTasksInProject
            (@WebParam(name = "session") @NotNull Session session, @WebParam @NotNull String userId, @WebParam @NotNull String projectId) throws Exception {
        if (serviceLocator.getSessionService().validate(session)) {
            return serviceLocator.getTaskService().findAllInProject(userId, projectId);
        }
        return null;
    }
}
