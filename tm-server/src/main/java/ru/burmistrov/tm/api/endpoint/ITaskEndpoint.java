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
import java.text.ParseException;
import java.util.List;

@WebService
public interface ITaskEndpoint {

    @WebMethod
    void updateTaskById(@WebParam @NotNull Session session, @WebParam @NotNull String userId, @WebParam @NotNull String projectId,
                        @WebParam @NotNull String taskId, @WebParam @NotNull String newName,
                        @WebParam @NotNull String description,
                        @WebParam @NotNull String dateEnd) throws ParseException, CloneNotSupportedException;

    @WebMethod
    @Nullable
    Task createTask(@WebParam @NotNull Session session, @NotNull @WebParam String userId, @WebParam @NotNull String projectId,
                 @WebParam @NotNull String name, @WebParam @NotNull String description,
                 @WebParam @NotNull String dateEnd) throws ParseException, CloneNotSupportedException;

    @WebMethod
    @NotNull
    List<Task> findAllTasks(@WebParam @NotNull Session session, @WebParam @NotNull String userId) throws CloneNotSupportedException;

    @WebMethod
    void removeAllTasksInProject(@WebParam @NotNull Session session, @WebParam @NotNull String userId,
                                 @WebParam @NotNull String projectId) throws CloneNotSupportedException;

    @WebMethod
    void removeTaskById(@WebParam @NotNull Session session, @WebParam @NotNull String userId, @WebParam @NotNull String taskId) throws CloneNotSupportedException;

    @WebMethod
    void removeAllTasks(@WebParam @NotNull Session session, @WebParam @NotNull String userId) throws CloneNotSupportedException;

    @WebMethod
    @NotNull
    List<Task> findAllTasksSortByDateBegin(@WebParam @NotNull Session session, @WebParam @NotNull String userId) throws CloneNotSupportedException;

    @WebMethod
    @NotNull
    List<Task> findAllTasksSortByDateEnd(@WebParam @NotNull Session session, @WebParam @NotNull String userId) throws CloneNotSupportedException;

    @WebMethod
    @NotNull
    List<Task> findAllTasksSortByStatus(@WebParam @NotNull Session session, @WebParam @NotNull String userId) throws CloneNotSupportedException;

    @WebMethod
    @Nullable
    Task findTaskByName(@WebParam @NotNull Session session, @WebParam @NotNull String userId, String name) throws CloneNotSupportedException;

    @WebMethod
    @Nullable
    Task findTaskByDescription(@WebParam @NotNull Session session, @WebParam @NotNull String userId, String description) throws CloneNotSupportedException;

    @WebMethod
    @NotNull
    List<Task> findAllTasksInProject(@WebParam @NotNull Session session, @WebParam @NotNull String userId, @WebParam @NotNull String projectId) throws CloneNotSupportedException;
}
