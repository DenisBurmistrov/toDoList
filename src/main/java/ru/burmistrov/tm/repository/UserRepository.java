package ru.burmistrov.tm.repository;

import ru.burmistrov.tm.api.repository.IUserRepository;
import ru.burmistrov.tm.entity.Role;
import ru.burmistrov.tm.entity.User;
import ru.burmistrov.tm.utils.PasswordUtil;

import java.util.LinkedHashMap;
import java.util.Map;

public class UserRepository implements IUserRepository {

    private final Map<String, User> users = new LinkedHashMap<>();

    @Override
    public User logIn(String login, String password) {

        for (Map.Entry<String, User> entry : users.entrySet()) {
            if (entry.getValue().getLogin().equals(login) && entry.getValue().getPassword().equals(PasswordUtil.hashPassword(password))) {
                return entry.getValue();
            }
        }
        return null;
    }

    @Override
    public User registrate(String login, String password, String firstName, String middleName, String lastName, String email, Role roleType) {
        for (Map.Entry<String, User> entry : users.entrySet()) {
            if (entry.getValue().getLogin().equals(login)) {
                return null;
            }
        }
        User user = new User();
        user.setRole(roleType);
        user.setLogin(login);
        user.setPassword(password);
        user.setFirstName(firstName);
        user.setMiddleName(middleName);
        user.setLastName(lastName);
        user.setEmail(email);
        users.put(user.getId(), user);
        return user;
    }

    @Override
    public void updatePassword(User currentUser, String login, String newPassword) {

        for (Map.Entry<String, User> entry : users.entrySet()) {
            if (entry.getValue().getLogin().equals(login)) {
                entry.getValue().setPassword(newPassword);
            }
        }
    }

    @Override
    public void updateCurrentUser(User currentUser, String firstName, String middleName, String lastName, String email) {
        currentUser.setFirstName(firstName);
        currentUser.setMiddleName(middleName);
        currentUser.setLastName(lastName);
        currentUser.setEmail(email);
    }

    public Map<String, User> getUsers() {
        return users;
    }
}
