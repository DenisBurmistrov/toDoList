package ru.burmistrov.tm.endpoint;

import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.burmistrov.tm.api.endpoint.IUserEndpoint;
import ru.burmistrov.tm.api.service.IAdminService;
import ru.burmistrov.tm.api.service.ISessionService;
import ru.burmistrov.tm.api.service.IUserService;
import ru.burmistrov.tm.dto.UserDto;
import ru.burmistrov.tm.entity.enumerated.Role;
import ru.burmistrov.tm.entity.Session;
import ru.burmistrov.tm.entity.User;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import java.util.Objects;

@NoArgsConstructor
@WebService
@Service
public class UserEndpoint implements IUserEndpoint {

    @Autowired
    private IUserService userService;

    @Autowired
    private ISessionService sessionService;

    @Autowired
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
}
