package ru.burmistrov.tm.api.repository;

import ru.burmistrov.tm.entity.AbstractEntity;
import ru.burmistrov.tm.entity.User;

import java.util.List;

public interface IUserRepository<T extends AbstractEntity> {

    User logIn(String login, String password);

    void updatePassword(String userId, String login, String newPassword);

    T persist(T entity);

    void merge(T abstractEntity);

    void remove(T abstractEntity);

    void removeAll(T abstractEntity);

    List<T> findAll(T abstractEntity);

    T findOne(T abstractEntity);
}
