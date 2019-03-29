package ru.burmistrov.tm.api.endpoint;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.burmistrov.tm.dto.UserDto;
import ru.burmistrov.tm.entity.enumerated.Role;
import ru.burmistrov.tm.entity.Session;
import ru.burmistrov.tm.entity.User;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import java.security.NoSuchAlgorithmException;

@WebService
public interface IUserEndpoint {

    @WebMethod
    @Nullable
    UserDto logIn
            (@WebParam(name = "session") @NotNull final String login, @WebParam(name = "password") @NotNull final String password) throws Exception;

    @WebMethod
    void updateUserById
            (@WebParam(name = "session") @NotNull final Session session, @WebParam(name = "userId") @NotNull final String userId,
             @WebParam(name = "firstName") @NotNull final String firstName, @WebParam(name = "middleName") @NotNull final String middleName,
             @WebParam(name = "lastName") @NotNull final String lastName, @WebParam(name = "email") @NotNull final String email,
             @WebParam(name = "role") @NotNull final Role role) throws Exception;

}
