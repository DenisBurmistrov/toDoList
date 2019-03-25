package ru.burmistrov.tm.api.repository;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.burmistrov.tm.entity.AbstractEntity;
import ru.burmistrov.tm.entity.User;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.List;

public interface IUserRepository {

    @Nullable
    User logIn(@NotNull final String login, @NotNull final String password) throws NoSuchAlgorithmException, SQLException;

    void updatePassword(@NotNull final String login, @NotNull final String newPassword) throws NoSuchAlgorithmException, SQLException;

    @Nullable
    User persist(@NotNull final String email,
                 @NotNull final String firstName, @NotNull final String lastName,
                 @NotNull final String login, @NotNull final String middleName,
                 @NotNull final String passwordHash, @NotNull final String role) throws IOException, NoSuchAlgorithmException, SQLException;

    void merge(@NotNull final User abstractEntity) throws SQLException;

    void remove(@NotNull final String id) throws SQLException;

    void removeAll() throws SQLException;

    @Nullable
    List<User> findAll() throws SQLException;

    @Nullable
    User findOne(@NotNull final String id) throws SQLException;

    @Nullable
    User findOneByLogin(@NotNull final String login) throws SQLException;
}
