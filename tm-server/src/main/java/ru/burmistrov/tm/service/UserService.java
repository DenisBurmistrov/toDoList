package ru.burmistrov.tm.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.burmistrov.tm.api.repository.IUserRepository;
import ru.burmistrov.tm.api.service.IUserService;
import ru.burmistrov.tm.entity.AbstractEntity;
import ru.burmistrov.tm.entity.enumerated.Role;
import ru.burmistrov.tm.entity.User;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

public final class UserService implements IUserService {

    @NotNull
    private final IUserRepository userRepository;

    public UserService(@NotNull final IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Nullable
    public User logIn(@NotNull final String login, @NotNull final String password) throws NoSuchAlgorithmException {
        return userRepository.logIn(login, password);
    }

    @Override
    public void merge(@NotNull final String userId, @NotNull final String firstName, @NotNull final String middleName,
                      @NotNull final String lastName, @NotNull final String email, @NotNull Role role){
        @NotNull final User currentUser = new User();
        currentUser.setFirstName(firstName);
        currentUser.setMiddleName(middleName);
        currentUser.setLastName(lastName);
        currentUser.setEmail(email);
        currentUser.setId(userId);
        currentUser.setRole(role.toString());
        @Nullable final AbstractEntity abstractEntity = userRepository.findOne(userId);
        if (abstractEntity != null)
            userRepository.merge(currentUser);
    }
}
