package ru.burmistrov.tm.api.service;

import org.jetbrains.annotations.Nullable;
import ru.burmistrov.tm.entity.AbstractEntity;
import ru.burmistrov.tm.entity.Role;

public interface IUserService<T extends AbstractEntity> {

    @Nullable
    T logIn(@Nullable String login,@Nullable String auth);

    @Nullable
    T persist(@Nullable String login,@Nullable String password,@Nullable String firstName,@Nullable String middleName,@Nullable String lastName,
              @Nullable String email,@Nullable Role roleType);

    void updatePassword(@Nullable String userId,@Nullable String login,@Nullable String password);

    void merge(@Nullable String userId,@Nullable String firstName,@Nullable String middleName,@Nullable String lastName,@Nullable String email,
               @Nullable Role role,@Nullable String login);

    void remove(@Nullable String userId);

    void removeAll(@Nullable String userId);

}
