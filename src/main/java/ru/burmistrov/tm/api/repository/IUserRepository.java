package ru.burmistrov.tm.api.repository;

import com.sun.istack.internal.NotNull;
import com.sun.istack.internal.Nullable;
import ru.burmistrov.tm.entity.AbstractEntity;
import ru.burmistrov.tm.entity.User;

import java.util.List;

public interface IUserRepository<T extends AbstractEntity> {

    @NotNull
    User logIn(@Nullable String login,@Nullable String password);

    void updatePassword(@Nullable String userId,@Nullable String login,@Nullable String newPassword);

    @NotNull
    T persist(@Nullable T entity);

    void merge(@NotNull T abstractEntity);

    void remove(@NotNull T abstractEntity);

    void removeAll(@NotNull T abstractEntity);

    @NotNull
    List<T> findAll(@NotNull T abstractEntity);

    @NotNull
    T findOne(@Nullable T abstractEntity);
}
