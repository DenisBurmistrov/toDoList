package ru.burmistrov.tm.api.service;

import ru.burmistrov.tm.entity.Role;
import ru.burmistrov.tm.entity.User;

public interface IUserService {

    User logIn(String login, String auth);

    User registrate(String login, String password, String firstName, String middleName, String lastName, String email, Role roleType);

    void updatePassword(String userId, String login, String password);

    void updateCurrentUser(String userId, String firstName, String middleName, String lastName, String email);
}
