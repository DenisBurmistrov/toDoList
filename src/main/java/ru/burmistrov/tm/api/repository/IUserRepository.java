package ru.burmistrov.tm.api.repository;

import ru.burmistrov.tm.entity.AbstractEntity;
import ru.burmistrov.tm.entity.User;

import java.util.Map;

public interface IUserRepository {

    User logIn(String login, String password);

    AbstractEntity persist(AbstractEntity entity);

    void updatePassword(String userId, String login, String newPassword);

    void merge(AbstractEntity abstractEntity);

    Map<String, AbstractEntity> getAbstractEntities();

}
