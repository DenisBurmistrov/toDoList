package ru.burmistrov.tm.endpoint;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.burmistrov.tm.api.loader.ServiceLocator;
import ru.burmistrov.tm.api.service.IUserService;
import ru.burmistrov.tm.entity.AbstractEntity;
import ru.burmistrov.tm.entity.Role;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService(endpointInterface = "ru.burmistrov.tm.api.service.IUserService")
public class UserEndpoint{

    private ServiceLocator serviceLocator;

    public UserEndpoint(ServiceLocator serviceLocator) {
        this.serviceLocator = serviceLocator;
    }

    @WebMethod
    @Nullable
    public AbstractEntity logIn(@NotNull String login, @NotNull String password) {
        return serviceLocator.getUserService().logIn(login, password);
    }

    @WebMethod
    @Nullable
    public AbstractEntity createUser(@NotNull String login, @NotNull String password, @NotNull String firstName, @NotNull String middleName,
                                     @NotNull String lastName, @NotNull String email, @NotNull Role roleType) {
        return serviceLocator.getUserService().createUser(login, password, firstName, middleName, lastName, email, roleType);
    }

    @WebMethod
    public void updatePasswordById(@NotNull String userId, @NotNull String login, @NotNull String password) {
        serviceLocator.getUserService().updatePasswordById(userId, login, password);
    }

    @WebMethod
    public void updateUserById(@NotNull String userId, @NotNull String firstName, @NotNull String middleName,
                               @NotNull String lastName, @NotNull String email, @NotNull Role role, @NotNull String login) {
        serviceLocator.getUserService().updateUserById(userId, firstName, middleName, lastName, email, role, login);
    }

    @WebMethod
    public void removeUserById(@NotNull String userId) {
        serviceLocator.getUserService().removeUserById(userId);
    }

    @WebMethod
    public void removeAllUsers(@NotNull String userId) {
        serviceLocator.getUserService().removeAllUsers(userId);
    }
}
