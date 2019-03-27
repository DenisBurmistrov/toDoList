package ru.burmistrov.tm.api.repository;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.burmistrov.tm.entity.AbstractEntity;
import ru.burmistrov.tm.entity.User;
import ru.burmistrov.tm.entity.enumerated.Role;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.List;

public interface IUserRepository {

    @Nullable
    User logIn(@NotNull final String login, @NotNull final String password) throws NoSuchAlgorithmException;

    void updatePassword(@NotNull final String login, @NotNull final String newPassword) throws NoSuchAlgorithmException;

    @Nullable
    User persist(@NotNull final String email,
                 @NotNull final String firstName, @NotNull final String lastName,
                 @NotNull final String login, @NotNull final String middleName,
                 @NotNull final String passwordHash, @NotNull final Role role) throws IOException, NoSuchAlgorithmException;

    void merge(@NotNull final User abstractEntity);

    void remove(@NotNull final String id);

    void removeAll();

    @Nullable
    List<User> findAll();

    @Nullable
    User findOne(@NotNull final String id);

    @Nullable
    User findOneByLogin(@NotNull final String login) ;
}
