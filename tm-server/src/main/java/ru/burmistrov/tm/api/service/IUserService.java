package ru.burmistrov.tm.api.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.burmistrov.tm.entity.enumerated.Role;
import ru.burmistrov.tm.entity.User;

import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface IUserService {

    @Nullable
    User logIn(@NotNull final String login, @NotNull final String auth) throws NoSuchAlgorithmException, SQLException;

}
