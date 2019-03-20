package ru.burmistrov.tm.api.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.burmistrov.tm.entity.AbstractEntity;
import ru.burmistrov.tm.entity.Role;
import ru.burmistrov.tm.entity.User;

import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface IUserService {

    @Nullable
    User logIn(@NotNull String login, @NotNull String auth);

    void merge(@NotNull String userId, @NotNull String firstName, @NotNull String middleName, @NotNull String lastName, @NotNull String email,
               @NotNull Role role);
}
