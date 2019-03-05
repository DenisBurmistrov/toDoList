package ru.burmistrov.tm.api;

public interface IUserRepository {

    String logIn(String login, String password);

    String logOut();

    String registrate(String login, String password, String firstName, String middleName, String lastName, String email);

    String updatePassword(String login, String newPassword);

    String showCurrentUser();

    String updateCurrentUser(String firstName, String middleName, String lastName, String email);

}
