package ru.burmistrov.tm.api.repository;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.burmistrov.tm.entity.AbstractEntity;
import ru.burmistrov.tm.entity.User;

import java.util.List;

public interface IUserRepository {

    @Nullable
    User logIn(@NotNull String login, @NotNull String password);

    void updatePassword(@NotNull String userId, @NotNull String login, @NotNull String newPassword);

    @Nullable
    User persist(@NotNull User entity);

    void merge(@NotNull User abstractEntity);

    void remove(@NotNull User abstractEntity);

    void removeAll(@NotNull User abstractEntity);

    @Nullable
    List<User> findAll(@NotNull User abstractEntity);

    @Nullable
    User findOne(@NotNull User abstractEntity);
}
