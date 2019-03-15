package ru.burmistrov.tm.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.burmistrov.tm.api.repository.IUserRepository;
import ru.burmistrov.tm.api.service.IUserService;
import ru.burmistrov.tm.entity.AbstractEntity;
import ru.burmistrov.tm.entity.Role;
import ru.burmistrov.tm.entity.User;

public final class UserService implements IUserService {

    @NotNull
    private final IUserRepository userRepository;

    public UserService(@NotNull IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Nullable
    public User logIn(@NotNull String login, @NotNull String password) {
        return userRepository.logIn(login, password);
    }

    @Nullable
    public User createUser(@NotNull String login, @NotNull String password, @NotNull String firstName, @NotNull String middleName, @NotNull String lastName,
                           @NotNull String email, @NotNull Role roleType) {

        @NotNull final User user = new User();
        user.setRole(roleType);
        user.setLogin(login);
        user.setPassword(password);
        user.setFirstName(firstName);
        user.setMiddleName(middleName);
        user.setLastName(lastName);
        user.setEmail(email);
        User abstractEntity = userRepository.findOne(user);
        if(abstractEntity == null)
            return userRepository.persist(user);

        return null;
    }

    public void updatePasswordById(@NotNull String userId, @NotNull String login, @NotNull String password) {
        if (password.length() > 0) {
            userRepository.updatePassword(userId, login, password);
        }
    }

    public void updateUserById(@NotNull String userId, @NotNull String firstName, @NotNull String middleName, @NotNull String lastName, @NotNull String email,
                               @NotNull Role role, @NotNull String login) {
        @NotNull final User currentUser = new User();
        currentUser.setFirstName(firstName);
        currentUser.setMiddleName(middleName);
        currentUser.setLastName(lastName);
        currentUser.setEmail(email);
        currentUser.setId(userId);
        currentUser.setRole(role);
        currentUser.setLogin(login);
        AbstractEntity abstractEntity = userRepository.findOne(currentUser);
        if(abstractEntity != null)
            userRepository.merge(currentUser);
    }

    @Override
    public void removeUserById(@NotNull String userId) {
        @NotNull final User user = new User();
        user.setId(userId);
        userRepository.remove(user);
    }

    @Override
    public void removeAllUsers(@Nullable String userId) {
        @NotNull final User user = new User();
        user.setId(userId);
        userRepository.removeAll(user);
    }
}
