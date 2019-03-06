package ru.burmistrov.tm.api.repository;

import ru.burmistrov.tm.entity.Role;
import ru.burmistrov.tm.entity.User;

public interface IUserRepository {

    User logIn(String login, String password);

    User registrate(String login, String password, String firstName, String middleName, String lastName, String email, Role role);

    void updatePassword(User currentUser, String login, String newPassword);

    void updateCurrentUser(User currentUser, String firstName, String middleName, String lastName, String email);

}
