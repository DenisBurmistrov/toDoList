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
    void updateTaskById(@WebParam(name = "userId") @NotNull String userId, @WebParam(name = "projectId") @NotNull String projectId,
                        @WebParam(name = "taskId") @NotNull String taskId, @WebParam(name = "newName") @NotNull String newName,
                        @WebParam(name = "description") @NotNull String description,
                        @WebParam(name = "dateEnd") @NotNull String dateEnd) throws ParseException;

    @WebMethod
    @Nullable
    T createTask(@NotNull @WebParam(name = "userId") String userId, @WebParam(name = "projectId") @NotNull String projectId,
                 @WebParam(name = "name") @NotNull String name, @WebParam(name = "description") @NotNull String description,
                 @WebParam(name = "dateEnd") @NotNull String dateEnd) throws ParseException;

    @WebMethod
    @NotNull
    List<T> findAllTasks(@WebParam(name = "userId") @NotNull String userId);

    @WebMethod
    void removeAllTasksInProject(@WebParam(name = "userId") @NotNull String userId,
                                 @WebParam(name = "projectId") @NotNull String projectId);

    @WebMethod
    void removeTaskById(@WebParam(name = "userId") @NotNull String userId, @WebParam(name = "taskId") @NotNull String taskId);

    @WebMethod
    void removeAllTasks(@WebParam(name = "userId") @NotNull String userId);

    @WebMethod
    @NotNull
    List<T> findAllTasksSortByDateBegin(@WebParam(name = "userId") @NotNull String userId);

    @WebMethod
    @NotNull
    List<T> findAllTasksSortByDateEnd(@WebParam(name = "userId") @NotNull String userId);

    @WebMethod
    @NotNull
    List<T> findAllTasksSortByStatus(@WebParam(name = "userId") @NotNull String userId);

    @WebMethod
    @Nullable
    T findTaskByName(@WebParam(name = "userId") @NotNull String userId, String name);

    @WebMethod
    @Nullable
    T findTaskByDescription(@WebParam(name = "userId") @NotNull String userId, String description);

    @WebMethod
    @NotNull
    List<T> findAllTasksInProject(@WebParam(name = "userId") @NotNull String userId, @WebParam @NotNull String projectId);
}
