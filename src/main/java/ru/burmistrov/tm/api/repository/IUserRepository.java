package ru.burmistrov.tm.api.repository;

import org.jetbrains.annotations.Nullable;
import ru.burmistrov.tm.entity.AbstractEntity;
import ru.burmistrov.tm.entity.User;

import java.util.List;

public interface IUserRepository<T extends AbstractEntity> {

    @Nullable
    User logIn(@Nullable String login,@Nullable String password);

    void updatePassword(@Nullable String userId,@Nullable String login,@Nullable String newPassword);

    @Nullable
    T persist(@Nullable T entity);

    void merge(@Nullable T abstractEntity);

    void remove(@Nullable T abstractEntity);

    void removeAll(@Nullable T abstractEntity);

    @Nullable
    List<T> findAll(@Nullable T abstractEntity);

    @Nullable
    T findOne(@Nullable T abstractEntity);
}
