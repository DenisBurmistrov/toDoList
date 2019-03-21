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
public interface IUserEndpoint {

    @WebMethod
    @Nullable
    User logIn
            (@WebParam(name = "session") @NotNull String login, @WebParam(name = "password") @NotNull String password);

    @WebMethod
    void updateUserById
            (@WebParam(name = "session") @NotNull Session session, @WebParam(name = "userId") @NotNull String userId,
             @WebParam(name = "firstName") @NotNull String firstName, @WebParam(name = "middleName") @NotNull String middleName,
             @WebParam(name = "lastName") @NotNull String lastName, @WebParam(name = "email") @NotNull String email,
             @WebParam(name = "role") @NotNull Role role) throws Exception;

}
