package ru.burmistrov.tm.api.service;

import org.jetbrains.annotations.NotNull;
import ru.burmistrov.tm.entity.AbstractEntity;
import ru.burmistrov.tm.entity.Role;

public interface IUserService<T extends AbstractEntity> {

    @NotNull
    T logIn(@NotNull String login,@NotNull String auth);

    @NotNull
    T persist(@NotNull String login,@NotNull String password,@NotNull String firstName,@NotNull String middleName,@NotNull String lastName,
              @NotNull String email,@NotNull Role roleType);

    void updatePassword(@NotNull String userId,@NotNull String login,@NotNull String password);

    void merge(@NotNull String userId,@NotNull String firstName,@NotNull String middleName,@NotNull String lastName,@NotNull String email,
               @NotNull Role role,@NotNull String login);

    void remove(@NotNull String userId);

    void removeAll(@NotNull String userId);

}
