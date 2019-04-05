package ru.burmistrov.tm.api.endpoint;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.burmistrov.tm.dto.UserDto;
import ru.burmistrov.tm.entity.enumerated.Role;
import ru.burmistrov.tm.entity.Session;
import ru.burmistrov.tm.entity.User;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import java.security.NoSuchAlgorithmException;

@WebService
public interface IUserEndpoint {

    @WebMethod
    @Nullable
    UserDto logIn
            (@WebParam(name = "session") @NotNull final String login, @WebParam(name = "password") @NotNull final String password) throws Exception;
}
