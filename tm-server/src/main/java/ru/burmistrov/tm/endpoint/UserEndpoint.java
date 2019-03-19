package ru.burmistrov.tm.endpoint;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.burmistrov.tm.api.loader.ServiceLocator;
import ru.burmistrov.tm.entity.Role;
import ru.burmistrov.tm.entity.User;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public class UserEndpoint{

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
    @Nullable
    public User createUser(@NotNull String login, @NotNull String password, @NotNull String firstName, @NotNull String middleName,
                                     @NotNull String lastName, @NotNull String email, @NotNull Role roleType) {
        return serviceLocator.getUserService().persist(login, password, firstName, middleName, lastName, email, roleType);
    }

    @WebMethod
    public void updatePasswordById(@NotNull String userId, @NotNull String login, @NotNull String password) {
        serviceLocator.getUserService().updatePassword(userId, login, password);
    }

    @WebMethod
    public void updateUserById(@NotNull String userId, @NotNull String firstName, @NotNull String middleName,
                               @NotNull String lastName, @NotNull String email, @NotNull Role role) {
        serviceLocator.getUserService().merge(userId, firstName, middleName, lastName, email, role);
    }

    @WebMethod
    public void removeUserById(@NotNull String userId) {
        serviceLocator.getUserService().remove(userId);
    }

    @WebMethod
    public void removeAllUsers(@NotNull String userId) {
        serviceLocator.getUserService().removeAll(userId);
    }
}
