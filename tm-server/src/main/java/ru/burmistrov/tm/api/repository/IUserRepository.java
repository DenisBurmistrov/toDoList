package ru.burmistrov.tm.api.repository;

import org.jetbrains.annotations.NotNull;
import ru.burmistrov.tm.entity.User;

import java.util.List;

public interface IUserRepository {

    void persist(@NotNull final User user);

    void merge(@NotNull final User user);

    void remove(@NotNull final String id);

    void removeAll();

    List<User> findAll();

    User findOne(@NotNull final String id);

    User findOneByLogin(@NotNull final String login);

    void updatePassword(@NotNull final String login, @NotNull final String newPassword);

}
