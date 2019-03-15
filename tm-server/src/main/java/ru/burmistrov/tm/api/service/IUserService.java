package ru.burmistrov.tm.api.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.burmistrov.tm.entity.AbstractEntity;
import ru.burmistrov.tm.entity.Role;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface IUserService<T extends AbstractEntity> {

    @WebMethod
    @Nullable
    T logIn(@WebParam(name = "login") @NotNull String login, @WebParam(name = "password") @NotNull String password);

    @WebMethod
    @Nullable
    T createUser(@WebParam(name = "login") @NotNull String login, @WebParam(name = "password") @NotNull String password,
                 @WebParam(name = "firstName") @NotNull String firstName, @WebParam(name = "middleName") @NotNull String middleName,
                 @WebParam(name = "lastName") @NotNull String lastName, @WebParam(name = "email") @NotNull String email,
                 @WebParam(name = "role") @NotNull Role roleType);

    @WebMethod
    void updatePasswordById(@WebParam(name = "userId") @NotNull String userId, @WebParam(name = "login") @NotNull String login,
                            @WebParam(name = "password") @NotNull String password);

    @WebMethod
    void updateUserById(@WebParam(name = "userId") @NotNull String userId, @WebParam(name = "firstNme") @NotNull String firstName,
                        @WebParam(name = "middleName") @NotNull String middleName, @WebParam(name = "lastName") @NotNull String lastName,
                        @WebParam(name = "email") @NotNull String email, @WebParam(name = "role") @NotNull Role role,
                        @WebParam(name = "login") @NotNull String login);

    @WebMethod
    void removeUserById(@WebParam(name = "userId") @NotNull String userId);

    @WebMethod
    void removeAllUsers(@WebParam(name = "userId") @NotNull String userId);

}
