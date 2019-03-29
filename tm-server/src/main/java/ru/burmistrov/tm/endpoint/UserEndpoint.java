package ru.burmistrov.tm.endpoint;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.burmistrov.tm.api.endpoint.IUserEndpoint;
import ru.burmistrov.tm.api.loader.ServiceLocator;
import ru.burmistrov.tm.dto.UserDto;
import ru.burmistrov.tm.entity.enumerated.Role;
import ru.burmistrov.tm.entity.Session;
import ru.burmistrov.tm.entity.User;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

@WebService
public class UserEndpoint implements IUserEndpoint {

    private ServiceLocator serviceLocator;

    public UserEndpoint(ServiceLocator serviceLocator) {
        this.serviceLocator = serviceLocator;
    }

    @WebMethod
    @Nullable
    public UserDto logIn(@WebParam(name = "login") @NotNull final String login, @WebParam(name = "password") @NotNull final String password) throws Exception {
        User user = serviceLocator.getUserService().logIn(login, password);
        UserDto userDto = null;
        if (user != null) {
            userDto = new UserDto();
            userDto.setLogin(user.getLogin());
            userDto.setPassword(user.getPassword());
        }
        return userDto;
    }

    @WebMethod
    @Override
    public void updateUserById
            (@WebParam(name = "session") @NotNull final Session session, @WebParam(name = "userId") @NotNull final String userId,
             @WebParam(name = "firstName") @NotNull final String firstName, @WebParam(name = "middleName") @NotNull final String middleName,
             @WebParam(name = "lastName") @NotNull final String lastName, @WebParam(name = "email") @NotNull final String email,
             @WebParam(name = "role") @NotNull final Role role) throws Exception {
        if (serviceLocator.getSessionService().validate(session)) {
            serviceLocator.getAdminService().updateUserById(userId, firstName, middleName, lastName, email, role);
        }
    }
}
