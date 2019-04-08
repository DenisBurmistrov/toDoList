package ru.burmistrov.tm.api.endpoint;


import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.burmistrov.tm.dto.UserDto;
import ru.burmistrov.tm.entity.enumerated.Role;
import ru.burmistrov.tm.entity.Session;
import ru.burmistrov.tm.entity.User;
import ru.burmistrov.tm.exception.ValidateAccessException;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

@WebService
public interface IAdminEndpoint {

    @WebMethod
    void saveDataByDefault(@WebParam(name = "session") @NotNull final Session session) throws Exception;

    @WebMethod
    void saveDataByFasterXmlJson(@WebParam(name = "session") @NotNull final Session session) throws Exception;

    @WebMethod
    void saveDataByFasterXml(@WebParam(name = "session") @NotNull final Session session) throws Exception;

    @WebMethod
    void saveDataByJaxbJson(@WebParam(name = "session") @NotNull final Session session) throws Exception;

    @WebMethod
    void saveDataByJaxbXml(@WebParam(name = "session") @NotNull final Session session) throws Exception;

    @WebMethod
    void loadDataByDefault(@WebParam(name = "session") @NotNull final Session session) throws Exception;

    @WebMethod
    void loadDataByFasterXmlJson(@WebParam(name = "session") @NotNull final Session session) throws Exception;

    @WebMethod
    void loadDataByFasterXml(@WebParam(name = "session") @NotNull final Session session) throws Exception;

    @WebMethod
    void loadDataByJaxbJson(@WebParam(name = "session") @NotNull final Session session) throws Exception;

    @WebMethod
    void loadDataByJaxbXml(@WebParam(name = "session") @NotNull final Session session) throws Exception;

    @WebMethod
    @Nullable
    UserDto createUser
            (@WebParam(name = "session") @NotNull final Session session, @WebParam(name = "login") @NotNull final String login,
             @WebParam(name = "password") @NotNull final String password, @WebParam(name = "firstName") @NotNull final String firstName,
             @WebParam(name = "middleName") @NotNull final String middleName, @WebParam(name = "lastName") @NotNull final String lastName,
             @WebParam(name = "email") @NotNull final String email, @WebParam(name = "roleType") @NotNull final Role roleType) throws Exception;

    @WebMethod
    void updatePasswordById
            (@WebParam(name = "session") @NotNull final Session session, @WebParam(name = "userId") @NotNull final String userId,
             @WebParam(name = "login") @NotNull final String login, @WebParam(name = "password") @NotNull final String password) throws Exception;

    @WebMethod
    void updateUserByLogin
            (@WebParam(name = "session") @NotNull final Session session, @WebParam(name = "login") @NotNull final String login,
             @WebParam(name = "firstName") @NotNull final String firstName, @WebParam(name = "middleName") @NotNull final String middleName,
             @WebParam(name = "lastName") @NotNull final String lastName, @WebParam(name = "email") @NotNull final String email,
             @WebParam(name = "role") @NotNull final Role role) throws Exception;

    @WebMethod
    void removeUserById
            (@WebParam(name = "session") @NotNull final Session session, @WebParam(name = "userId") @NotNull final String userId) throws Exception;

    @WebMethod
    void removeAllUsers
            (@WebParam(name = "session") @NotNull final Session session, @WebParam(name = "userId") @NotNull final String userId) throws Exception;

    @WebMethod
    User findOneByLogin(@WebParam(name = "session") @NotNull final Session session, @WebParam(name = "login") @NotNull final String login) throws Exception;

}
