package ru.burmistrov.tm.api.repository;

import ru.burmistrov.tm.entity.AbstractEntity;
import ru.burmistrov.tm.entity.User;

import java.util.List;

public interface IUserRepository {

    User logIn(String login, String password);

    void updatePassword(String userId, String login, String newPassword);

    AbstractEntity persist(AbstractEntity entity);

    void merge(AbstractEntity abstractEntity);

    void remove(AbstractEntity abstractEntity);

    void removeAll(AbstractEntity abstractEntity);

    List<AbstractEntity> findAll(AbstractEntity abstractEntity);

    AbstractEntity findOne(AbstractEntity abstractEntity);
}
