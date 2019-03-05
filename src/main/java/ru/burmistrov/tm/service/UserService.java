package ru.burmistrov.tm.service;

import ru.burmistrov.tm.repository.UserRepository;

public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String logIn(String login, String auth) {
        return userRepository.logIn(login, auth);
    }

    public String logOut() {
        return userRepository.logOut();
    }

    public String registrate(String login, String password, String firstName, String middleName, String lastName, String email) {
        return userRepository.registrate(login, password, firstName, middleName, lastName, email);
    }

    public String updatePassword(String login, String password) {
        if (password.length() > 0) {
            return userRepository.updatePassword(login, password);
        }
        return "Нельзя использовать пустой пароль";
    }

    public String showCurrentUser() {
        return userRepository.showCurrentUser();
    }

    public String updateCurrentUser(String firstName, String middleName, String lastName, String email) {
        if (firstName.length() == 0) return "Нельзя использовать пустое имя";
        if (middleName.length() == 0) return "Нельзя использовать пустое отчество";
        if (lastName.length() == 0) return "Нельзя использовать пустую фамалию";
        if (email.length() == 0) return "Нельзя использовать пустую почту";
        return userRepository.updateCurrentUser(firstName, middleName, lastName, email);
    }
}
