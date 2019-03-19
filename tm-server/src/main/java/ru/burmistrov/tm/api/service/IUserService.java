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

    @Nullable
    User persist(@NotNull String login, @NotNull String password, @NotNull String firstName, @NotNull String middleName, @NotNull String lastName,
              @NotNull String email, @Nullable Role roleType);

    void updatePassword(@NotNull String userId, @NotNull String login, @NotNull String password);

    void merge(@NotNull String userId, @NotNull String firstName, @NotNull String middleName, @NotNull String lastName, @NotNull String email,
               @NotNull Role role, @NotNull String login);

    void remove(@NotNull String userId);

    void removeAll(@NotNull String userId);

}
