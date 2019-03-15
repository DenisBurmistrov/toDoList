package ru.burmistrov.tm.api.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.burmistrov.tm.entity.AbstractEntity;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import java.text.ParseException;
import java.util.List;

@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface ITaskService<T extends AbstractEntity> {

    @WebMethod
    void updateTaskById(@WebParam @NotNull String userId, @WebParam @NotNull String projectId, @WebParam @NotNull String taskId,
                        @WebParam @NotNull String newName, @WebParam @NotNull String description, @WebParam @NotNull String dateEnd) throws ParseException;

    @WebMethod
    @Nullable
    T createTask(@NotNull @WebParam String userId, @WebParam @NotNull String projectId, @WebParam @NotNull String name,
                 @WebParam @NotNull String description, @WebParam @NotNull String dateEnd) throws ParseException;

    @WebMethod
    @NotNull
    List<T> findAllTasks(@WebParam @NotNull String userId);

    @WebMethod
    void removeAllTasksInProject(@WebParam @NotNull String userId, @WebParam @NotNull String projectId);

    @WebMethod
    void removeTaskById(@WebParam @NotNull String userId, @WebParam @NotNull String taskId);

    @WebMethod
    void removeAllTasks(@WebParam @NotNull String userId);

    @WebMethod
    @NotNull
    List<T> findAllTasksSortByDateBegin(@WebParam @NotNull String userId);

    @WebMethod
    @NotNull
    List<T> findAllTasksSortByDateEnd(@WebParam @NotNull String userId);

    @WebMethod
    @NotNull
    List<T> findAllTasksSortByStatus(@WebParam @NotNull String userId);

    @WebMethod
    @Nullable
    T findTaskByName(@WebParam @NotNull String userId, String name);

    @WebMethod
    @Nullable
    T findTaskByDescription(@WebParam @NotNull String userId, String description);

    @WebMethod
    @NotNull
    List<T> findAllTasksInProject(@WebParam @NotNull String userId, @WebParam @NotNull String projectId);
}
