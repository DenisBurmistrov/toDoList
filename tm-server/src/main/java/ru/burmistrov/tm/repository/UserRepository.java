package ru.burmistrov.tm.repository;

import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.burmistrov.tm.api.repository.IUserRepository;
import ru.burmistrov.tm.entity.User;
import ru.burmistrov.tm.entity.enumerated.FieldConst;
import ru.burmistrov.tm.utils.PasswordUtil;

import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.*;

@NoArgsConstructor
public final class UserRepository extends AbstractRepository<User> implements IUserRepository  {

    @Nullable
    private Connection connection;

    public UserRepository(@Nullable final Connection connection) {
        this.connection = connection;
    }

    @Nullable
    @SneakyThrows
    private User fetch(@Nullable final ResultSet row) {
        if (row == null) return null;
        @NotNull final User user = new User();
        user.setId(row.getString(FieldConst.ID));
        user.setEmail(row.getString(FieldConst.EMAIL));
        user.setFirstName(row.getString(FieldConst.FIRST_NAME));
        user.setLastName(row.getString(FieldConst.LAST_NAME));
        user.setLogin(row.getString(FieldConst.LOGIN));
        user.setMiddleName(row.getString(FieldConst.MIDDLE_NAME));
        user.setPassword(row.getString(FieldConst.PASSWORD));
        user.setRole(row.getString(FieldConst.ROLE));
        return user;
    }

    @Nullable
    @Override
    public User logIn(@NotNull final String login, @NotNull final String password) throws NoSuchAlgorithmException, SQLException {
        for (User user : findAll()) {
            if (Objects.requireNonNull(user.getLogin()).equals(login) &&
                    Objects.requireNonNull(Objects.requireNonNull(user.getPassword()))
                            .equals(PasswordUtil.hashPassword(password))) {
                return user;
            }
        }
        return null;
    }

    @Override
    public void updatePassword(@NotNull final String login, @NotNull final String newPassword) throws NoSuchAlgorithmException, SQLException {
        String query =  "UPDATE tm.app_user SET " +
                "passwordHash = '" + PasswordUtil.hashPassword(newPassword) +"' " +
                "WHERE login = '" + login + "'";
        Statement statement = Objects.requireNonNull(connection).createStatement();
        statement.executeUpdate(query);
    }

    @NotNull
    @Override
    public User persist(@NotNull final String email,
                        @NotNull final String firstName, @NotNull final String lastName,
                        @NotNull final String login, @NotNull final String middleName,
                        @NotNull final String passwordHash, @NotNull final String role) throws SQLException, NoSuchAlgorithmException {

        @NotNull final User user = new User();
        user.setLogin(login);
        user.setHashPassword(passwordHash);
        user.setFirstName(firstName);
        user.setMiddleName(middleName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setRole(role);

        @NotNull final String query = "INSERT INTO tm.app_user (id, email, firstName, lastName, login, middleName, passwordHash, role) \n" +
                " VALUES ('" + user.getId() + "', '" + user.getEmail() + "', '" + user.getFirstName() + "', '" +
               user.getLastName()  + "', '" + user.getLogin() + "', '" + user.getMiddleName()  + "', '" + user.getPassword() + "', '" + user.getRole() + "');";
        @NotNull final Statement statement = Objects.requireNonNull(connection).createStatement();
        statement.executeUpdate(query);
        return user;
    }

    @Override
    public void merge(@Nullable final User entity) throws SQLException {
        @NotNull final String query =  "UPDATE tm.app_user SET " +
                "firstName = '" + Objects.requireNonNull(entity).getFirstName() +"', " +
                "lastName = '" + entity.getLastName() + "', " +
                "middleName = '" + entity.getMiddleName() + "', " +
                "email = '" + entity.getEmail() + "'  " +
                "WHERE id = '" + entity.getId() + "'";
        @NotNull final Statement statement = Objects.requireNonNull(connection).createStatement();
        statement.executeUpdate(query);
    }

    @Override
    public void remove(@Nullable final String id) throws SQLException {
        @NotNull final String query =  "DELETE FROM tm.app_user " +
                "WHERE id = '" + id + "'";
        @NotNull final Statement statement = Objects.requireNonNull(connection).createStatement();
        statement.executeUpdate(query);
    }

    @Override
    public void removeAll() throws SQLException {
        @NotNull final String query =  "DELETE FROM tm.app_user";
        @NotNull final Statement statement = Objects.requireNonNull(connection).createStatement();
        int resultSet = statement.executeUpdate(query);
    }

    @NotNull
    @Override
    public List<User> findAll() throws SQLException {
        @NotNull final String query =
                "SELECT * FROM tm.app_user";
        @NotNull final PreparedStatement statement =
                Objects.requireNonNull(connection).prepareStatement(query);
        @NotNull final ResultSet resultSet = statement.executeQuery();
        @NotNull final List<User> result = new ArrayList<>();
        while (resultSet.next()) result.add(fetch(resultSet));
        statement.close();
        return result;

    }

    @Nullable
    @Override
    public User findOne(@NotNull final String id) throws SQLException {
        @NotNull final String query =
                "SELECT * FROM tm.app_user WHERE id = ?";
        @NotNull final PreparedStatement statement =
                Objects.requireNonNull(connection).prepareStatement(query);
        statement.setString(1, id);
        @NotNull final ResultSet resultSet = statement.executeQuery();
        if(resultSet.next()) {
            @NotNull final User user = Objects.requireNonNull(fetch(resultSet));
            statement.close();
            return user;
        }
        return null;
    }

    @Nullable
    public User findOneByLogin(@NotNull final String login) throws SQLException {
        @NotNull final String query =
                "SELECT * FROM tm.app_user WHERE login = ?";
        @NotNull final PreparedStatement statement =
                Objects.requireNonNull(connection).prepareStatement(query);
        statement.setString(1, login);
        @NotNull final ResultSet resultSet = statement.executeQuery();
        if(resultSet.next()) {
            @NotNull final User user = Objects.requireNonNull(fetch(resultSet));
            statement.close();
            return user;
        }
        return null;
    }
}
