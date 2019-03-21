package ru.burmistrov.tm.api.endpoint;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.burmistrov.tm.entity.AbstractEntity;
import ru.burmistrov.tm.entity.Role;
import ru.burmistrov.tm.entity.Session;
import ru.burmistrov.tm.entity.User;
import ru.burmistrov.tm.utils.exceptions.ValidateAccessException;

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
    void updateUserById(@WebParam @NotNull Session session, @WebParam @NotNull String userId, @WebParam @NotNull String firstName,
                        @WebParam @NotNull String middleName, @WebParam @NotNull String lastName,
                        @WebParam @NotNull String email, @WebParam @NotNull Role role) throws CloneNotSupportedException, ValidateAccessException;

}
