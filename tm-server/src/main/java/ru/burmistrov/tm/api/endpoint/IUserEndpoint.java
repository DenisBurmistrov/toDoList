package ru.burmistrov.tm.api.endpoint;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.burmistrov.tm.entity.AbstractEntity;
import ru.burmistrov.tm.entity.Role;
import ru.burmistrov.tm.entity.User;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

@WebService
public interface IUserEndpoint {

    @WebMethod
    @Nullable
    User logIn(@WebParam @NotNull String login, @WebParam @NotNull String password);

    @WebMethod
    @Nullable
    User createUser(@WebParam @NotNull String login, @WebParam @NotNull String password,
                 @WebParam @NotNull String firstName, @WebParam @NotNull String middleName,
                 @WebParam @NotNull String lastName, @WebParam @NotNull String email,
                 @WebParam @NotNull Role roleType);

    @WebMethod
    void updatePasswordById(@WebParam @NotNull String userId, @WebParam @NotNull String login,
                            @WebParam @NotNull String password);

    @WebMethod
    void updateUserById(@WebParam @NotNull String userId, @WebParam @NotNull String firstName,
                        @WebParam @NotNull String middleName, @WebParam @NotNull String lastName,
                        @WebParam @NotNull String email, @WebParam @NotNull Role role,
                        @WebParam @NotNull String login);

    @WebMethod
    void removeUserById(@WebParam @NotNull String userId);

    @WebMethod
    void removeAllUsers(@WebParam @NotNull String userId);

}
