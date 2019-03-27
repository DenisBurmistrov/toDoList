package ru.burmistrov.tm.service;

import org.apache.ibatis.session.SqlSession;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.burmistrov.tm.api.repository.IUserRepository;
import ru.burmistrov.tm.api.service.IUserService;
import ru.burmistrov.tm.entity.AbstractEntity;
import ru.burmistrov.tm.entity.enumerated.Role;
import ru.burmistrov.tm.entity.User;
import ru.burmistrov.tm.utils.PasswordUtil;

import java.security.NoSuchAlgorithmException;
import java.util.Objects;

public final class UserService implements IUserService {

    @NotNull
    private final IUserRepository userRepository;

    @NotNull
    private final SqlSession session;

    public UserService(@NotNull final SqlSession session) {
        this.session = session;
        userRepository = session.getMapper(IUserRepository.class);
    }

    @Override
    @Nullable
    public User logIn(@NotNull final String login, @NotNull final String password) throws NoSuchAlgorithmException {
        for (User user : userRepository.findAll()) {
            if (Objects.requireNonNull(user.getLogin()).equals(login) &&
                    Objects.requireNonNull(Objects.requireNonNull(user.getPassword()))
                            .equals(PasswordUtil.hashPassword(password))) {
                return user;
            }
        }
        return null;
    }

    @Override
    public void merge(@NotNull final String userId, @NotNull final String firstName, @NotNull final String middleName,
                      @NotNull final String lastName, @NotNull final String email, @NotNull Role role) {
        try {
            @NotNull final User currentUser = new User();
            currentUser.setFirstName(firstName);
            currentUser.setMiddleName(middleName);
            currentUser.setLastName(lastName);
            currentUser.setEmail(email);
            currentUser.setId(userId);
            currentUser.setRole(role);
            @Nullable final AbstractEntity abstractEntity = userRepository.findOne(userId);
            if (abstractEntity != null) {
                Objects.requireNonNull(userRepository).merge(currentUser);
                Objects.requireNonNull(session).commit();
            }
        } catch (Exception e) {
            session.rollback();
        }
    }
}
