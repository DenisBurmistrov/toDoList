package ru.burmistrov.tm.service;

import lombok.NoArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.burmistrov.tm.api.repository.IUserRepository;
import ru.burmistrov.tm.api.service.IUserService;
import ru.burmistrov.tm.entity.AbstractEntity;
import ru.burmistrov.tm.entity.User;
import ru.burmistrov.tm.entity.enumerated.Role;
import ru.burmistrov.tm.repository.UserRepository;
import ru.burmistrov.tm.utils.PasswordUtil;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

@NoArgsConstructor
public final class UserService implements IUserService {

    @Inject
    private IUserRepository userRepository;

    @Inject
    private EntityManager entityManager;

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

        @NotNull final User currentUser = new User();
        currentUser.setFirstName(firstName);
        currentUser.setMiddleName(middleName);
        currentUser.setLastName(lastName);
        currentUser.setEmail(email);
        currentUser.setId(userId);
        currentUser.setRole(role);
        @Nullable final AbstractEntity abstractEntity = Objects.requireNonNull(userRepository).findOne(userId);
        if (abstractEntity != null) {
            try {
                entityManager.getTransaction().begin();
                Objects.requireNonNull(userRepository).merge(currentUser);
                entityManager.getTransaction().commit();
            } catch (Exception e) {
                entityManager.getTransaction().rollback();
            }
        }

    }
}
