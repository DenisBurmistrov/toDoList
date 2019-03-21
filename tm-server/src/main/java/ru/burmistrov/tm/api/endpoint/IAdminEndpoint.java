package ru.burmistrov.tm.api.endpoint;


import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.burmistrov.tm.entity.enumerated.Role;
import ru.burmistrov.tm.entity.Session;
import ru.burmistrov.tm.entity.User;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService
public interface IAdminEndpoint {

    @WebMethod
    void saveDataByDefault(@WebParam(name = "session") @NotNull Session session) throws Exception;

    @WebMethod
    void saveDataByFasterXmlJson(@WebParam(name = "session") @NotNull Session session) throws Exception;

    @WebMethod
    void saveDataByFasterXml(@WebParam(name = "session") @NotNull Session session) throws Exception;

    @WebMethod
    void saveDataByJaxbJson(@WebParam(name = "session") @NotNull Session session) throws Exception;

    @WebMethod
    void saveDataByJaxbXml(@WebParam(name = "session") @NotNull Session session) throws Exception;

    @WebMethod
    void loadDataByDefault(@WebParam(name = "session") @NotNull Session session) throws Exception;

    @WebMethod
    void loadDataByFasterXmlJson(@WebParam(name = "session") @NotNull Session session) throws Exception;

    @WebMethod
    void loadDataByFasterXml(@WebParam(name = "session") @NotNull Session session) throws Exception;

    @WebMethod
    void loadDataByJaxbJson(@WebParam(name = "session") @NotNull Session session) throws Exception;

    @WebMethod
    void loadDataByJaxbXml(@WebParam(name = "session") @NotNull Session session) throws Exception;

    @WebMethod
    @Nullable
    User createUser
            (@WebParam(name = "session") @NotNull Session session, @WebParam(name = "login") @NotNull String login,
             @WebParam(name = "password") @NotNull String password, @WebParam(name = "firstName") @NotNull String firstName,
             @WebParam(name = "middleName") @NotNull String middleName, @WebParam(name = "lastName") @NotNull String lastName,
             @WebParam(name = "email") @NotNull String email, @WebParam(name = "roleType") @NotNull Role roleType) throws Exception;

    @WebMethod
    void updatePasswordById
            (@WebParam(name = "session") @NotNull Session session, @WebParam(name = "userId") @NotNull String userId,
             @WebParam(name = "login") @NotNull String login, @WebParam(name = "password") @NotNull String password) throws Exception;

    @WebMethod
    void updateUserById
            (@WebParam(name = "session") @NotNull Session session, @WebParam(name = "userId") @NotNull String userId,
             @WebParam(name = "firstName") @NotNull String firstName, @WebParam(name = "middleName") @NotNull String middleName,
             @WebParam(name = "lastName") @NotNull String lastName, @WebParam(name = "email") @NotNull String email,
             @WebParam(name = "role") @NotNull Role role) throws Exception;

    @WebMethod
    void removeUserById
            (@WebParam(name = "session") @NotNull Session session, @WebParam(name = "userId") @NotNull String userId) throws Exception;

    @WebMethod
    void removeAllUsers
            (@WebParam(name = "session") @NotNull Session session, @WebParam(name = "userId") @NotNull String userId) throws Exception;

}
