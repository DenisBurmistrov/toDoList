package ru.burmistrov.tm.api.endpoint;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.burmistrov.tm.dto.ProjectDto;
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
            (@WebParam(name = "session") @NotNull final Session session, @WebParam(name = "userId") @NotNull final String userId,
             @WebParam(name = "projectId") @NotNull String projectId) throws Exception;

    @Nullable
    @WebMethod
    ProjectDto createProject
            (@WebParam(name = "session") @NotNull final Session session, @WebParam(name = "userId") @NotNull final String userId,
             @WebParam(name = "name") @NotNull final String name, @WebParam(name = "description") @NotNull final String description,
             @WebParam(name = "dateEnd") @NotNull final String dateEnd, @WebParam(name = "status") @NotNull final String status) throws Exception;

    @WebMethod
    void updateProjectById
            (@WebParam(name = "session") @NotNull final Session session, @WebParam(name = "userId") @NotNull final String userId,
             @WebParam(name = "projectId") @NotNull final String projectId, @WebParam(name = "name") @NotNull final String name,
             @WebParam(name = "description") @NotNull final String description, @WebParam(name = "dateEnd") @NotNull final String dateEnd,
             @WebParam(name = "status") @NotNull final String status) throws Exception;

    @WebMethod
    void removeAllProjects
            (@WebParam(name = "session") @NotNull final Session session, @WebParam(name = "userId") @NotNull final String userId) throws Exception;

    @Nullable
    @WebMethod
    List<ProjectDto> findAllProjects
            (@WebParam(name = "session") @NotNull final Session session, @WebParam(name = "userId") @NotNull final String userId) throws Exception;

    @Nullable
    @WebMethod
    List<ProjectDto> findAllProjectsSortByDateBegin
            (@WebParam(name = "session") @NotNull final Session session, @WebParam(name = "userId") @NotNull final String userId) throws Exception;

    @Nullable
    @WebMethod
    List<ProjectDto> findAllProjectsSortByDateEnd
            (@WebParam(name = "session") @NotNull final Session session, @WebParam(name = "userId") @NotNull final String userId) throws Exception;

    @Nullable
    @WebMethod
    List<ProjectDto> findAllProjectsSortByStatus
            (@WebParam(name = "session") @NotNull final Session session, @WebParam(name = "userId") @NotNull final String userId) throws Exception;

    @Nullable
    @WebMethod
    ProjectDto findProjectByName
            (@WebParam(name = "session") @NotNull final Session session, @WebParam(name = "userId") @NotNull final String userId,
             @WebParam(name = "name") @NotNull final String name) throws Exception;

    @Nullable
    @WebMethod
    ProjectDto findProjectByDescription
            (@WebParam(name = "session") @NotNull final Session session, @WebParam(name = "userId") @NotNull final String userId,
            @WebParam(name = "description") @NotNull final String description) throws Exception;

}
