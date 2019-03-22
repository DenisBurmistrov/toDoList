package ru.burmistrov.tm.repository;

import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.burmistrov.tm.api.repository.IUserRepository;
import ru.burmistrov.tm.entity.Task;
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

        for (User user : findAll(new User())) {
            System.out.println(user.getPassword());
            System.out.println(password);
            if (Objects.requireNonNull(user.getLogin()).equals(login) &&
                    Objects.requireNonNull(PasswordUtil.hashPassword(Objects.requireNonNull(user.getPassword())))
                            .equals(PasswordUtil.hashPassword(password))) {
                return user;
            }
        }
        return null;
    }

    @Override
    public void updatePassword(@NotNull final String userId, @NotNull final String login, @NotNull final String newPassword) throws NoSuchAlgorithmException, SQLException {
        User user = new User();
        user.setPassword(newPassword);
        user.setLogin(login);
        user.setId(userId);
        String query =  "UPDATE tm.app_user SET " +
                "passwordHash = '" + Objects.requireNonNull(user).getPassword() +"', " +
                "WHERE login = '" + user.getLogin() + "'";
        Statement statement = Objects.requireNonNull(connection).createStatement();
        int resultSet = statement.executeUpdate(query);
    }

    @NotNull
    @Override
    public User persist(@Nullable final User entity) throws SQLException {

        String query = "INSERT INTO tm.app_user (id, email, firstName, lastName, login, middleName, passwordHash, role) \n" +
                " VALUES ('" + Objects.requireNonNull(entity).getId() + "', '" + entity.getEmail() + "', '" + entity.getFirstName() + "', '" +
               entity.getLastName()  + "', '" + entity.getLogin() + "', '" + entity.getMiddleName()  + "', '" +entity.getPassword() + "', '" + entity.getRole() + "');";
        Statement statement = Objects.requireNonNull(connection).createStatement();
        int resultSet = statement.executeUpdate(query);
        return entity;
    }

    @Override
    public void merge(@Nullable final User entity) throws SQLException {
        String query =  "UPDATE tm.app_user SET " +
                "firstName = '" + Objects.requireNonNull(entity).getFirstName() +"', " +
                "lastName = '" + entity.getLastName() + "', " +
                "middleName = '" + entity.getMiddleName() + "', " +
                "email = '" + entity.getEmail() + "'  " +
                "WHERE id = '" + entity.getId() + "'";
        Statement statement = Objects.requireNonNull(connection).createStatement();
        int resultSet = statement.executeUpdate(query);
    }

    @Override
    public void remove(@Nullable final User abstractEntity) throws SQLException {
        String query =  "DELETE FROM tm.app_user " +
                "WHERE id = '"+ Objects.requireNonNull(abstractEntity).getId() +"'";
        Statement statement = Objects.requireNonNull(connection).createStatement();
        int resultSet = statement.executeUpdate(query);
    }

    @Override
    public void removeAll(@Nullable final User abstractEntity) throws SQLException {
        String query =  "DELETE FROM tm.app_user";
        Statement statement = Objects.requireNonNull(connection).createStatement();
        int resultSet = statement.executeUpdate(query);
    }

    @NotNull
    @Override
    public List<User> findAll(@Nullable final User abstractEntity) throws SQLException {
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

    @NotNull
    @Override
    public User findOne(@Nullable final User abstractEntity) throws SQLException {
        @NotNull final String query =
                "SELECT * FROM app_user WHERE id = ?";
        @NotNull final PreparedStatement statement =
                Objects.requireNonNull(connection).prepareStatement(query);
        statement.setString(1, Objects.requireNonNull(abstractEntity).getId());
        @NotNull final ResultSet resultSet = statement.executeQuery();
        @NotNull final User user = Objects.requireNonNull(fetch(resultSet));
        statement.close();
        return user;
    }
}
