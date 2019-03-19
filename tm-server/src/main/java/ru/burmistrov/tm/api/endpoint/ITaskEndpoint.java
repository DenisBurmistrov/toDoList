package ru.burmistrov.tm.api.endpoint;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.burmistrov.tm.entity.AbstractEntity;
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
    void updateTaskById(@WebParam @NotNull String userId, @WebParam @NotNull String projectId,
                        @WebParam @NotNull String taskId, @WebParam @NotNull String newName,
                        @WebParam @NotNull String description,
                        @WebParam @NotNull String dateEnd) throws ParseException;

    @WebMethod
    @Nullable
    Task createTask(@NotNull @WebParam String userId, @WebParam @NotNull String projectId,
                 @WebParam @NotNull String name, @WebParam @NotNull String description,
                 @WebParam @NotNull String dateEnd) throws ParseException;

    @WebMethod
    @NotNull
    List<Task> findAllTasks(@WebParam @NotNull String userId);

    @WebMethod
    void removeAllTasksInProject(@WebParam @NotNull String userId,
                                 @WebParam @NotNull String projectId);

    @WebMethod
    void removeTaskById(@WebParam @NotNull String userId, @WebParam @NotNull String taskId);

    @WebMethod
    void removeAllTasks(@WebParam @NotNull String userId);

    @WebMethod
    @NotNull
    List<Task> findAllTasksSortByDateBegin(@WebParam @NotNull String userId);

    @WebMethod
    @NotNull
    List<Task> findAllTasksSortByDateEnd(@WebParam @NotNull String userId);

    @WebMethod
    @NotNull
    List<Task> findAllTasksSortByStatus(@WebParam @NotNull String userId);

    @WebMethod
    @Nullable
    Task findTaskByName(@WebParam(name = "userId") @NotNull String userId, String name);

    @WebMethod
    @Nullable
    Task findTaskByDescription(@WebParam(name = "userId") @NotNull String userId, String description);

    @WebMethod
    @NotNull
    List<Task> findAllTasksInProject(@WebParam(name = "userId") @NotNull String userId, @WebParam @NotNull String projectId);
}
