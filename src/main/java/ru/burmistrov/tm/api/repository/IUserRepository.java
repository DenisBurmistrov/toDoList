package ru.burmistrov.tm.api.repository;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.burmistrov.tm.entity.AbstractEntity;
import ru.burmistrov.tm.entity.User;

import java.util.List;

public interface IUserRepository<T extends AbstractEntity> {

    @Nullable
    User logIn(@NotNull String login, @NotNull String password);

    void updatePassword(@NotNull String userId,@NotNull String login,@NotNull String newPassword);

    @Nullable
    T persist(@NotNull T entity);

    void merge(@NotNull T abstractEntity);

    void remove(@NotNull T abstractEntity);

    void removeAll(@NotNull T abstractEntity);

    @Nullable
    List<T> findAll(@NotNull T abstractEntity);

    @Nullable
    T findOne(@NotNull T abstractEntity);
}
