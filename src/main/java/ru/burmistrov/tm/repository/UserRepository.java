package ru.burmistrov.tm.repository;

import ru.burmistrov.tm.Bootstrap;
import ru.burmistrov.tm.api.IUserRepository;
import ru.burmistrov.tm.entity.Role;
import ru.burmistrov.tm.entity.User;
import ru.burmistrov.tm.utils.Utils;

import java.util.Map;

public class UserRepository implements IUserRepository {

    private final Bootstrap bootstrap = Bootstrap.getInstance();

    private Map<String, User> users = bootstrap.getUsers();


    @Override
    public String logIn(String login, String password) {

        for (Map.Entry<String, User> entry : users.entrySet()) {
            if (entry.getValue().getLogin().equals(login) && entry.getValue().getPassword().equals(Utils.hashPassword(password))) {
                bootstrap.setCurrentUser(entry.getValue());
                return "Вы авторизовались под пользователем: " + entry.getValue().getLogin();
            }
        }
        return "Неверные данные аккаунта";
    }

    @Override
    public String logOut() {
        bootstrap.setCurrentUser(null);
        return "Выход завершён";
    }

    @Override
    public String registrate(String login, String password, String firstName, String middleName, String lastName, String email) {
        for (Map.Entry<String, User> entry : users.entrySet()) {
            if (entry.getValue().getLogin().equals(login)) {
                return "Пользователь с данным логином уже существует";
            }
        }
        User user = new User();
        user.setRole(Role.COMMON_USER);
        user.setLogin(login);
        user.setPassword(password);
        user.setFirstName(firstName);
        user.setMiddleName(middleName);
        user.setLastName(lastName);
        user.setEmail(email);
        users.put(user.getId(), user);
        return "Регистрация прошла успешно";
    }

    @Override
    public String updatePassword(String login, String newPassword) {

        for (Map.Entry<String, User> entry : users.entrySet()) {
            if (entry.getValue().getLogin().equals(login)) {
                entry.getValue().setPassword(newPassword);
                return "Пароль обновлён";
            }
        }
        return "Нет пользователя с логином: " + login;
    }

    @Override
    public String showCurrentUser() {
        return bootstrap.getCurrentUser().toString();
    }

    @Override
    public String updateCurrentUser(String firstName, String middleName, String lastName, String email) {
        User currentUser = bootstrap.getCurrentUser();
        currentUser.setFirstName(firstName);
        currentUser.setMiddleName(middleName);
        currentUser.setLastName(lastName);
        currentUser.setEmail(email);
        return "Обновление завершено";
    }


}
