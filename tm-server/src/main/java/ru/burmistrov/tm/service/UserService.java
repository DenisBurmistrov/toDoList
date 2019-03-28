package ru.burmistrov.tm.service;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.burmistrov.tm.repository.UserRepository;
import ru.burmistrov.tm.api.service.IUserService;
import ru.burmistrov.tm.entity.AbstractEntity;
import ru.burmistrov.tm.entity.User;
import ru.burmistrov.tm.entity.enumerated.Role;
import ru.burmistrov.tm.utils.PasswordUtil;

import java.util.Objects;

public final class UserService implements IUserService {

    @Nullable
    private UserRepository userRepository;

    @NotNull
    private final SqlSessionFactory sqlSessionFactory;

    public UserService(@NotNull final SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    @Override
    @Nullable
    public User logIn(@NotNull final String login, @NotNull final String password) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            try {
                userRepository = sqlSession.getMapper(UserRepository.class);
                for (User user : userRepository.findAll()) {
                    if (Objects.requireNonNull(user.getLogin()).equals(login) &&
                            Objects.requireNonNull(Objects.requireNonNull(user.getPassword()))
                                    .equals(PasswordUtil.hashPassword(password))) {
                        return user;
                    }
                }
                return null;
            } catch (Exception e) {
                sqlSession.rollback();
            }
        }
        return null;
    }

    @Override
    public void merge(@NotNull final String userId, @NotNull final String firstName, @NotNull final String middleName,
                      @NotNull final String lastName, @NotNull final String email, @NotNull Role role) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            try {
                userRepository = sqlSession.getMapper(UserRepository.class);
                @NotNull final User currentUser = new User();
                currentUser.setFirstName(firstName);
                currentUser.setMiddleName(middleName);
                currentUser.setLastName(lastName);
                currentUser.setEmail(email);
                currentUser.setId(userId);
                currentUser.setRole(role);
                @Nullable final AbstractEntity abstractEntity = Objects.requireNonNull(userRepository).findOne(userId);
                if (abstractEntity != null) {
                    Objects.requireNonNull(userRepository).merge(currentUser);
                    Objects.requireNonNull(sqlSession).commit();
                }
            } catch (Exception e) {
                sqlSession.rollback();
            }
        }

    }
}
