package ru.burmistrov.tm.endpoint;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.burmistrov.tm.api.endpoint.IUserEndpoint;
import ru.burmistrov.tm.api.loader.ServiceLocator;
import ru.burmistrov.tm.entity.enumerated.Role;
import ru.burmistrov.tm.entity.Session;
import ru.burmistrov.tm.entity.User;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService
public class UserEndpoint implements IUserEndpoint {

    private ServiceLocator serviceLocator;

    public UserEndpoint(ServiceLocator serviceLocator) {
        this.serviceLocator = serviceLocator;
    }

    @WebMethod
    @Nullable
    public User logIn(@NotNull String login, @NotNull String password) {
        return serviceLocator.getUserService().logIn(login, password);
    }

    @WebMethod
    @Override
    public void updateUserById
            (@WebParam(name = "session") @NotNull Session session, @WebParam(name = "userId") @NotNull String userId,
             @WebParam(name = "firstName") @NotNull String firstName, @WebParam(name = "middleName") @NotNull String middleName,
             @WebParam(name = "lastName") @NotNull String lastName, @WebParam(name = "email") @NotNull String email,
             @WebParam(name = "role") @NotNull Role role) throws Exception {
        if (serviceLocator.getSessionService().validate(session)) {
            serviceLocator.getAdminService().updateUserById(userId, firstName, middleName, lastName, email, role);
        }
    }
}
