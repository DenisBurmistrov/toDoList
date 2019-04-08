package ru.burmistrov.tm.repository;

import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.burmistrov.tm.entity.Project;
import ru.burmistrov.tm.entity.User;

import java.util.List;

@Repository
public interface IUserRepository extends JpaRepository<User, Long> {

    User save(@NotNull final User user);

    void delete(@NotNull final User user);

    @Modifying
    @Query(value = "DELETE from User user")
    void removeAll();

    @Query(value = "SELECT user FROM User user")
    List<User> findAll();

    @Query(value = "SELECT user FROM User user WHERE user.id = :id")
    User findOne(@NotNull @Param(value = "id") final String id);

    @Query(value = "SELECT user FROM User user WHERE user.login = :login")
    User findOneByLogin(@NotNull @Param(value = "login") final String login);

    @Modifying
    @Query(value = "UPDATE User user SET user.password = :password WHERE user.login = :login")
    void updatePassword(@NotNull @Param(value = "login") final String login, @NotNull @Param(value = "password") final String newPassword);
}
