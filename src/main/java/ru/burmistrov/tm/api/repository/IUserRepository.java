package ru.burmistrov.tm.api.repository;

import ru.burmistrov.tm.entity.Role;
import ru.burmistrov.tm.entity.User;

public interface IUserRepository {

    User logIn(String login, String password);

    User registrate(String login, String password, String firstName, String middleName, String lastName, String email, Role role);

    void updatePassword(String userId, String login, String newPassword);

    void updateCurrentUser(String userId, String firstName, String middleName, String lastName, String email);

}
