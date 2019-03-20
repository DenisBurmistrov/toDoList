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
import java.text.ParseException;
import java.util.List;

@WebService
public interface IProjectEndpoint {

    @WebMethod
    void removeProjectById(@WebParam @NotNull Session session, @WebParam @NotNull String userId, @WebParam @NotNull String projectId) throws CloneNotSupportedException;

    @Nullable
    @WebMethod
    Project createProject(@WebParam @NotNull Session session, @WebParam @NotNull String userId, @WebParam @NotNull String name,
                    @WebParam @NotNull String description,
                    @WebParam @NotNull String dateEnd) throws ParseException, CloneNotSupportedException;

    @WebMethod
    void updateProjectById(@WebParam @NotNull Session session, @WebParam @NotNull String userId, @WebParam @NotNull String projectId,
                           @WebParam @NotNull String name, @WebParam @NotNull String description,
                           @WebParam @NotNull String dateEnd) throws ParseException, CloneNotSupportedException;

    @WebMethod
    void removeAllProjects(@WebParam @NotNull Session session, @WebParam @NotNull String userId) throws CloneNotSupportedException;

    @NotNull
    @WebMethod
    List<Project> findAllProjects(@WebParam @NotNull Session session, @WebParam @NotNull String userId) throws CloneNotSupportedException;

    @NotNull
    @WebMethod
    List<Project> findAllProjectsSortByDateBegin(@WebParam @NotNull Session session, @WebParam @NotNull String userId) throws CloneNotSupportedException;

    @NotNull
    @WebMethod
    List<Project> findAllProjectsSortByDateEnd(@WebParam @NotNull Session session, @WebParam @NotNull String userId) throws CloneNotSupportedException;

    @NotNull
    @WebMethod
    List<Project> findAllProjectsSortByStatus(@WebParam @NotNull Session session, @WebParam @NotNull String userId) throws CloneNotSupportedException;

    @Nullable
    @WebMethod
    Project findProjectByName(@WebParam @NotNull Session session, @WebParam @NotNull String userId, @WebParam @NotNull String name) throws CloneNotSupportedException;

    @Nullable
    @WebMethod
    Project findProjectByDescription(@WebParam @NotNull Session session, @WebParam @NotNull String userId, @WebParam @NotNull String description) throws CloneNotSupportedException;

}
