package ru.burmistrov.tm.endpoint;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.burmistrov.tm.api.endpoint.IUserEndpoint;
import ru.burmistrov.tm.api.service.IAdminService;
import ru.burmistrov.tm.api.service.ISessionService;
import ru.burmistrov.tm.api.service.IUserService;
import ru.burmistrov.tm.dto.UserDto;
import ru.burmistrov.tm.entity.enumerated.Role;
import ru.burmistrov.tm.entity.Session;
import ru.burmistrov.tm.entity.User;

import javax.inject.Inject;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import java.util.Objects;

@WebService
public class UserEndpoint implements IUserEndpoint {

    @Inject
    private IUserService userService;

    @Inject
    private ISessionService sessionService;

    @Inject
    private IAdminService adminService;


    @WebMethod
    @Nullable
    public UserDto logIn(@WebParam(name = "login") @NotNull final String login, @WebParam(name = "password") @NotNull final String password) throws Exception {
        User user = userService.logIn(login, password);
        UserDto userDto = null;
        if (user != null) {
            userDto = new UserDto();
            userDto.setId(Objects.requireNonNull(user).getId());
            userDto.setLogin(user.getLogin());
            userDto.setPassword(user.getPassword());
            userDto.setFirstName(user.getFirstName());
            userDto.setMiddleName(user.getMiddleName());
            userDto.setLastName(user.getLastName());
            userDto.setEmail(user.getEmail());
            userDto.setRole(user.getRole());
        }
        return userDto;
    }

    @WebMethod
    @Override
    public void updateUserByLogin
            (@WebParam(name = "session") @NotNull final Session session, @WebParam(name = "login") @NotNull final String login,
             @WebParam(name = "firstName") @NotNull final String firstName, @WebParam(name = "middleName") @NotNull final String middleName,
             @WebParam(name = "lastName") @NotNull final String lastName, @WebParam(name = "email") @NotNull final String email,
             @WebParam(name = "role") @NotNull final Role role) throws Exception {
        if (sessionService.validate(session)) {
            adminService.updateUserByLogin(login, firstName, middleName, lastName, email, role);
        }
    }
}
