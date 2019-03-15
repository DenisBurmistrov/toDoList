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
public interface IProjectService<T extends AbstractEntity> {

    @WebMethod
    void removeProjectById(@WebParam @NotNull String userId, @WebParam @NotNull String projectId);

    @Nullable
    @WebMethod
    T createProject(@WebParam(name = "userId") @NotNull String userId, @WebParam(name = "name") @NotNull String name,
                    @WebParam(name = "description") @NotNull String description,
                    @WebParam(name = "dateEnd") @NotNull String dateEnd) throws ParseException;

    @WebMethod
    void updateProjectById(@WebParam(name = "userId") @NotNull String userId, @WebParam(name = "projectId") @NotNull String projectId,
                           @WebParam(name = "name") @NotNull String name, @WebParam(name = "name") @NotNull String description,
                           @WebParam(name = "dateEnd") @NotNull String dateEnd) throws ParseException;

    @WebMethod
    void removeAllProjects(@WebParam(name = "userId") @NotNull String userId);

    @NotNull
    @WebMethod
    List<T> findAllProjects(@WebParam(name = "userId") @NotNull String userId);

    @NotNull
    @WebMethod
    List<T> findAllProjectsSortByDateBegin(@WebParam(name = "userId") @NotNull String userId);

    @NotNull
    @WebMethod
    List<T> findAllProjectsSortByDateEnd(@WebParam(name = "userId") @NotNull String userId);

    @NotNull
    @WebMethod
    List<T> findAllProjectsSortByStatus(@WebParam(name = "userId") @NotNull String userId);

    @Nullable
    @WebMethod
    T findProjectByName(@WebParam(name = "userId") @NotNull String userId, @WebParam(name = "userId") @NotNull String name);

    @Nullable
    @WebMethod
    T findProjectByDescription(@WebParam(name = "userId") @NotNull String userId, @WebParam(name = "description") @NotNull String description);

}
