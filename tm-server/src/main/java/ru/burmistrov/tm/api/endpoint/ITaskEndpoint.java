package ru.burmistrov.tm.api.endpoint;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.burmistrov.tm.entity.AbstractEntity;
import ru.burmistrov.tm.entity.Session;
import ru.burmistrov.tm.entity.Task;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;

@WebService
public interface ITaskEndpoint {

    @WebMethod
    void updateTaskById
            (@WebParam(name = "session") @NotNull final Session session, @WebParam(name = "userId") @NotNull final String userId,
             @WebParam(name = "projectId") @NotNull final String projectId, @WebParam(name = "taskId") @NotNull final String taskId,
             @WebParam(name = "newName") @NotNull final String newName, @WebParam(name = "description") @NotNull final String description,
             @WebParam(name = "dateEnd") @NotNull final String dateEnd) throws Exception;

    @WebMethod
    @Nullable
    Task createTask
            (@WebParam(name = "session") @NotNull final Session session, @WebParam(name = "userId") @NotNull final String userId,
             @WebParam(name = "projectId") @NotNull final String projectId, @WebParam(name = "name") @NotNull final String name,
             @WebParam(name = "description") @NotNull final String description, @WebParam(name = "dateEnd") @NotNull final String dateEnd) throws Exception;

    @WebMethod
    @Nullable
    List<Task> findAllTasks
            (@WebParam(name = "session") @NotNull final Session session, @WebParam(name = "userId") @NotNull final String userId) throws Exception;

    @WebMethod
    void removeAllTasksInProject
            (@WebParam(name = "session") @NotNull final Session session, @WebParam(name = "userId") @NotNull final String userId,
             @WebParam(name = "projectId") @NotNull final String projectId) throws Exception;

    @WebMethod
    void removeTaskById
            (@WebParam(name = "session") @NotNull final Session session, @WebParam(name = "userId") @NotNull final String userId,
             @WebParam(name = "taskId") @NotNull final String taskId) throws Exception;

    @WebMethod
    void removeAllTasks
            (@WebParam(name = "session") @NotNull final Session session, @WebParam(name = "userId") @NotNull final String userId) throws Exception;

    @WebMethod
    @Nullable
    List<Task> findAllTasksSortByDateBegin
            (@WebParam(name = "session") @NotNull final Session session, @WebParam(name = "userId") @NotNull final String userId) throws Exception;

    @WebMethod
    @Nullable
    List<Task> findAllTasksSortByDateEnd
            (@WebParam(name = "session") @NotNull final Session session, @WebParam(name = "userId") @NotNull final String userId) throws Exception;

    @WebMethod
    @Nullable
    List<Task> findAllTasksSortByStatus
            (@WebParam(name = "session") @NotNull final Session session, @WebParam(name = "userId") @NotNull final String userId) throws Exception;

    @WebMethod
    @Nullable
    Task findTaskByName
            (@WebParam(name = "session") @NotNull final Session session, @WebParam(name = "userId") @NotNull final String userId,
             @WebParam(name = "name") @NotNull final String name) throws Exception;

    @WebMethod
    @Nullable
    Task findTaskByDescription(@WebParam(name = "session") @NotNull final Session session, @WebParam @NotNull final String userId, String description) throws Exception;

    @WebMethod
    @Nullable
    List<Task> findAllTasksInProject(@WebParam(name = "session") @NotNull final Session session, @WebParam @NotNull final String userId,
                                     @WebParam @NotNull final String projectId) throws Exception;
}
