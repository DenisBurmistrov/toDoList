package ru.burmistrov.tm.api.endpoint;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.burmistrov.tm.entity.AbstractEntity;
import ru.burmistrov.tm.entity.Project;
import ru.burmistrov.tm.entity.Session;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;

@WebService
public interface IProjectEndpoint {

    @WebMethod
    void removeProjectById
            (@WebParam(name = "session") @NotNull Session session, @WebParam(name = "userId") @NotNull String userId,
             @WebParam(name = "projectId") @NotNull String projectId) throws Exception;

    @Nullable
    @WebMethod
    Project createProject
            (@WebParam(name = "session") @NotNull Session session, @WebParam(name = "userId") @NotNull String userId,
             @WebParam(name = "name") @NotNull String name, @WebParam(name = "description") @NotNull String description,
             @WebParam(name = "dateEnd") @NotNull String dateEnd) throws Exception;

    @WebMethod
    void updateProjectById
            (@WebParam(name = "session") @NotNull Session session, @WebParam(name = "userId") @NotNull String userId,
             @WebParam(name = "projectId") @NotNull String projectId, @WebParam(name = "name") @NotNull String name,
             @WebParam(name = "description") @NotNull String description, @WebParam(name = "dateEnd") @NotNull String dateEnd) throws Exception;

    @WebMethod
    void removeAllProjects
            (@WebParam(name = "session") @NotNull Session session, @WebParam(name = "userId") @NotNull String userId) throws Exception;

    @NotNull
    @WebMethod
    List<Project> findAllProjects
            (@WebParam(name = "session") @NotNull Session session, @WebParam(name = "userId") @NotNull String userId) throws Exception;

    @NotNull
    @WebMethod
    List<Project> findAllProjectsSortByDateBegin
            (@WebParam(name = "session") @NotNull Session session, @WebParam(name = "userId") @NotNull String userId) throws Exception;

    @NotNull
    @WebMethod
    List<Project> findAllProjectsSortByDateEnd
            (@WebParam(name = "session") @NotNull Session session, @WebParam(name = "userId") @NotNull String userId) throws Exception;

    @NotNull
    @WebMethod
    List<Project> findAllProjectsSortByStatus
            (@WebParam(name = "session") @NotNull Session session, @WebParam(name = "userId") @NotNull String userId) throws Exception;

    @Nullable
    @WebMethod
    Project findProjectByName
            (@WebParam(name = "session") @NotNull Session session, @WebParam(name = "userId") @NotNull String userId,
             @WebParam(name = "name") @NotNull String name) throws Exception;

    @Nullable
    @WebMethod
    Project findProjectByDescription
            (@WebParam(name = "session") @NotNull Session session, @WebParam(name = "userId") @NotNull String userId,
            @WebParam(name = "description") @NotNull String description) throws Exception;

}
