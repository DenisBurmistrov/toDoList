package ru.burmistrov.tm.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.burmistrov.tm.api.repository.IUserRepository;
import ru.burmistrov.tm.api.service.IUserService;
import ru.burmistrov.tm.entity.AbstractEntity;
import ru.burmistrov.tm.entity.Role;
import ru.burmistrov.tm.entity.User;

public final class UserService extends AbstractService implements IUserService {

    @NotNull
    private final IUserRepository<AbstractEntity> userRepository;

    public UserService(@NotNull IUserRepository<AbstractEntity> userRepository) {
        this.userRepository = userRepository;
    }

    @Nullable
    public User logIn(@NotNull String login, @NotNull String auth) {
        return userRepository.logIn(login, auth);
    }

    @Nullable
    public User persist(@NotNull String login, @NotNull String password, @NotNull String firstName, @NotNull String middleName, @NotNull String lastName,
                        @NotNull String email, @NotNull Role roleType) {

        @NotNull final User user = new User();
        user.setRole(roleType);
        user.setLogin(login);
        user.setPassword(password);
        user.setFirstName(firstName);
        user.setMiddleName(middleName);
        user.setLastName(lastName);
        user.setEmail(email);
        AbstractEntity abstractEntity = userRepository.findOne(user);
        if(abstractEntity == null)
            return (User) userRepository.persist(user);

        return null;
    }

    public void updatePassword(@NotNull String userId, @NotNull String login, @NotNull String password) {
        if (password.length() > 0) {
            userRepository.updatePassword(userId, login, password);
        }
    }

    public void merge(@NotNull String userId, @NotNull String firstName, @NotNull String middleName, @NotNull String lastName, @NotNull String email,
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
    public void remove(@NotNull String userId) {
        @NotNull final User user = new User();
        user.setId(userId);
        userRepository.remove(user);
    }

    @Override
    public void removeAll(@Nullable String userId) {
        @NotNull final User user = new User();
        user.setId(userId);
        userRepository.removeAll(user);
    }
}
