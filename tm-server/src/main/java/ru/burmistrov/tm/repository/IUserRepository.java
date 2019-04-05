package ru.burmistrov.tm.repository;

import org.apache.deltaspike.data.api.*;
import org.jetbrains.annotations.NotNull;
import ru.burmistrov.tm.entity.User;

import java.util.List;

@Repository
public interface IUserRepository extends FullEntityRepository<User, Long> {

    void persist(@NotNull final User user);

    User merge(@NotNull final User user);

    void remove(@NotNull final User user);

    @Modifying
    @Query(value = "DELETE from User user")
    void removeAll();

    @Query(value = "SELECT user FROM User user")
    List<User> findAll();

    @Query(value = "SELECT user FROM User user WHERE user.id = :id", max = 1)
    User findOne(@NotNull @QueryParam(value = "id") final String id);

    @Query(value = "SELECT user FROM User user WHERE user.login = :login")
    User findOneByLogin(@NotNull @QueryParam(value = "login") final String login);

    @Modifying
    @Query(value = "UPDATE User user SET user.password = :password WHERE user.login = :login")
    void updatePassword(@NotNull @QueryParam(value = "login") final String login, @NotNull @QueryParam(value = "password") final String newPassword);
}
