package ru.burmistrov.tm.endpoint;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.burmistrov.tm.api.endpoint.IUserEndpoint;
import ru.burmistrov.tm.api.loader.ServiceLocator;
import ru.burmistrov.tm.entity.Role;
import ru.burmistrov.tm.entity.Session;
import ru.burmistrov.tm.entity.User;
import ru.burmistrov.tm.utils.exceptions.ValidateAccessException;

import javax.jws.WebMethod;
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
    public void updateUserById(@NotNull Session session, @NotNull String userId, @NotNull String firstName, @NotNull String middleName, @NotNull String lastName, @NotNull String email, @NotNull Role role) throws CloneNotSupportedException, ValidateAccessException {
        if (serviceLocator.getSessionService().validate(session)) {
            serviceLocator.getAdminService().merge(userId, firstName, middleName, lastName, email, role);
        }
    }
}
