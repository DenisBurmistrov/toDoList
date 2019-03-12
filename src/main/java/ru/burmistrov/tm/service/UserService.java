package ru.burmistrov.tm.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.burmistrov.tm.api.repository.IUserRepository;
import ru.burmistrov.tm.api.service.IUserService;
import ru.burmistrov.tm.entity.AbstractEntity;
import ru.burmistrov.tm.entity.Role;
import ru.burmistrov.tm.entity.User;

public final class UserService extends AbstractService implements IUserService {

    @Nullable private final IUserRepository<AbstractEntity> userRepository;

    public UserService(@Nullable IUserRepository<AbstractEntity> userRepository) {
        this.userRepository = userRepository;
    }

    @Nullable
    public User logIn(@Nullable String login,@Nullable String auth)
    {
        if (userRepository != null) {
            return userRepository.logIn(login, auth);
        }
        return null;
    }

    @Nullable
    public User persist(@Nullable String login,@Nullable String password,@Nullable String firstName,@Nullable String middleName,@Nullable String lastName,
                        @Nullable String email,@Nullable Role roleType) {

        User user = new User();
        user.setRole(roleType);
        user.setLogin(login);
        user.setPassword(password);
        user.setFirstName(firstName);
        user.setMiddleName(middleName);
        user.setLastName(lastName);
        user.setEmail(email);
        if (userRepository != null) {
            AbstractEntity abstractEntity = userRepository.findOne(user);
            if (abstractEntity == null) {
                return (User) userRepository.persist(user);
            }
        }
        return null;
    }

    public void updatePassword(@Nullable String userId,@Nullable String login,@Nullable String password) {
        if (password != null && password.length() > 0 && userRepository != null) {
            userRepository.updatePassword(userId, login, password);
        }
    }

    public void merge(@Nullable String userId, @Nullable String firstName, @Nullable String middleName, @Nullable String lastName, @Nullable String email,
                       @Nullable Role role, @Nullable String login) {
        User currentUser = new User();
        currentUser.setFirstName(firstName);
        currentUser.setMiddleName(middleName);
        currentUser.setLastName(lastName);
        currentUser.setEmail(email);
        currentUser.setId(userId);
        currentUser.setRole(role);
        currentUser.setLogin(login);
        if (userRepository != null) {
            AbstractEntity abstractEntity = userRepository.findOne(currentUser);
            if (abstractEntity != null)
                userRepository.merge(currentUser);
        }
    }

    @Override
    public void remove(@Nullable String userId) {
        User user = new User();
        user.setId(userId);
        if (userRepository != null) {
            userRepository.remove(user);
        }
    }

    @Override
    public void removeAll(@Nullable String userId) {
        User user = new User();
        user.setId(userId);
        if (userRepository != null) {
            userRepository.removeAll(user);
        }
    }
}
