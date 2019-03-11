package ru.burmistrov.tm.api.service;

import ru.burmistrov.tm.entity.AbstractEntity;
import ru.burmistrov.tm.entity.Role;

public interface IUserService<T extends AbstractEntity> {

    T logIn(String login, String auth);

    T persist(String login, String password, String firstName, String middleName, String lastName, String email, Role roleType);

    void updatePassword(String userId, String login, String password);

    void merge(String userId, String firstName, String middleName, String lastName, String email, Role role, String login);

    void remove(String userId);

    void removeAll(String userId);

}
