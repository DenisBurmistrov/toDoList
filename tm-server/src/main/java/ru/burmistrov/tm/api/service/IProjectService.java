package ru.burmistrov.tm.api.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.burmistrov.tm.entity.AbstractEntity;
import ru.burmistrov.tm.entity.Project;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import java.text.ParseException;
import java.util.List;

@WebService
public interface IProjectService {

    @WebMethod
    void removeProjectById(@WebParam @NotNull String userId, @WebParam @NotNull String projectId);

    @Nullable
    @WebMethod
    Project createProject(@WebParam @NotNull String userId, @WebParam @NotNull String name,
                    @WebParam @NotNull String description,
                    @WebParam @NotNull String dateEnd) throws ParseException;

    @WebMethod
    void updateProjectById(@WebParam @NotNull String userId, @WebParam @NotNull String projectId,
                           @WebParam @NotNull String name, @WebParam @NotNull String description,
                           @WebParam @NotNull String dateEnd) throws ParseException;

    @WebMethod
    void removeAllProjects(@WebParam @NotNull String userId);

    @NotNull
    @WebMethod
    List<Project> findAllProjects(@WebParam @NotNull String userId);

    @NotNull
    @WebMethod
    List<Project> findAllProjectsSortByDateBegin(@WebParam @NotNull String userId);

    @NotNull
    @WebMethod
    List<Project> findAllProjectsSortByDateEnd(@WebParam @NotNull String userId);

    @NotNull
    @WebMethod
    List<Project> findAllProjectsSortByStatus(@WebParam @NotNull String userId);

    @Nullable
    @WebMethod
    Project findProjectByName(@WebParam @NotNull String userId, @WebParam @NotNull String name);

    @Nullable
    @WebMethod
    Project findProjectByDescription(@WebParam @NotNull String userId, @WebParam @NotNull String description);

}
