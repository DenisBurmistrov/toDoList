package ru.burmistrov.tm.service;

import ru.burmistrov.tm.api.repository.IUserRepository;
import ru.burmistrov.tm.api.service.IUserService;
import ru.burmistrov.tm.entity.Role;
import ru.burmistrov.tm.entity.User;

public final class UserService implements IUserService {

    private final IUserRepository userRepository;

    public UserService(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User logIn(String login, String auth) {
        return userRepository.logIn(login, auth);
    }

    public User persist(String login, String password, String firstName, String middleName, String lastName, String email, Role roleType) {

        User user = new User();
        user.setRole(roleType);
        user.setLogin(login);
        user.setPassword(password);
        user.setFirstName(firstName);
        user.setMiddleName(middleName);
        user.setLastName(lastName);
        user.setEmail(email);
        if(!userRepository.getAbstractEntities().containsValue(user)) {
            return (User) userRepository.persist(user);
        }
        return null;
    }

    public void updatePassword(String userId, String login, String password) {
        if (password.length() > 0) {
            userRepository.updatePassword(userId, login, password);
        }
    }

    public void merge(String userId, String firstName, String middleName, String lastName, String email, Role role) {
        User currentUser = new User();
        currentUser.setFirstName(firstName);
        currentUser.setMiddleName(middleName);
        currentUser.setLastName(lastName);
        currentUser.setEmail(email);
        currentUser.setId(userId);
        currentUser.setRole(role);
        if (firstName.length() == 0 && middleName.length() == 0 && lastName.length() == 0
                && email.length() == 0 && userRepository.getAbstractEntities().containsKey(userId))
            userRepository.merge(currentUser);
    }
}
