package ru.burmistrov.tm.api.repository;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.burmistrov.tm.entity.AbstractEntity;
import ru.burmistrov.tm.entity.User;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

public interface IUserRepository {

    @Nullable
    User logIn(@NotNull final String login, @NotNull final String password) throws NoSuchAlgorithmException;

    void updatePassword(@NotNull final String userId, @NotNull final String login, @NotNull final String newPassword) throws NoSuchAlgorithmException;

    @Nullable
    User persist(@NotNull final User entity) throws IOException, NoSuchAlgorithmException;

    void merge(@NotNull final User abstractEntity);

    void remove(@NotNull final User abstractEntity);

    void removeAll(@NotNull final User abstractEntity);

    @Nullable
    List<User> findAll(@NotNull final User abstractEntity);

    @Nullable
    User findOne(@NotNull final User abstractEntity);
}
