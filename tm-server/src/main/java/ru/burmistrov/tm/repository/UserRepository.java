package ru.burmistrov.tm.repository;

import lombok.NoArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.burmistrov.tm.api.repository.IUserRepository;
import ru.burmistrov.tm.entity.User;
import ru.burmistrov.tm.mapper.IUserMapper;
import ru.burmistrov.tm.utils.PasswordUtil;

import java.security.NoSuchAlgorithmException;
import java.util.*;

@NoArgsConstructor
public final class UserRepository extends AbstractRepository<User> implements IUserRepository  {

    @Nullable
    private IUserMapper userMapper;

    @Nullable
    private SqlSession session;

    public UserRepository(@Nullable SqlSession session) {
        this.session = session;
        userMapper = Objects.requireNonNull(session).getMapper(IUserMapper.class);
    }

    @Nullable
    @Override
    public User logIn(@NotNull final String login, @NotNull final String password) throws NoSuchAlgorithmException {
        for (User user : findAll()) {
            if (Objects.requireNonNull(user.getLogin()).equals(login) &&
                    Objects.requireNonNull(Objects.requireNonNull(user.getPassword()))
                            .equals(PasswordUtil.hashPassword(password))) {
                return user;
            }
        }
        return null;
    }

    @Override
    public void updatePassword(@NotNull final String login, @NotNull final String newPassword) throws NoSuchAlgorithmException {
        Objects.requireNonNull(userMapper).updatePassword(login, PasswordUtil.hashPassword(newPassword));
        Objects.requireNonNull(session).commit();
    }

    @NotNull
    @Override
    public User persist(@NotNull final String email,
                        @NotNull final String firstName, @NotNull final String lastName,
                        @NotNull final String login, @NotNull final String middleName,
                        @NotNull final String passwordHash, @NotNull final String role) throws NoSuchAlgorithmException {

        @NotNull final User user = new User();
        user.setLogin(login);
        user.setHashPassword(passwordHash);
        user.setFirstName(firstName);
        user.setMiddleName(middleName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setRole(role);

        Objects.requireNonNull(userMapper).persist(user.getId(), email, firstName, lastName, login, middleName, Objects.requireNonNull(user.getPassword()), role);
        Objects.requireNonNull(session).commit();
        return user;
    }

    @Override
    public void merge(@NotNull final User user) {
        Objects.requireNonNull(userMapper).merge(user);
        Objects.requireNonNull(session).commit();
    }

    @Override
    public void remove(@NotNull final String id) {
        Objects.requireNonNull(userMapper).remove(Objects.requireNonNull(id));
        Objects.requireNonNull(session).commit();
    }

    @Override
    public void removeAll() {
        Objects.requireNonNull(userMapper).removeAll();
        Objects.requireNonNull(session).commit();
    }

    @NotNull
    @Override
    public List<User> findAll() {
        return Objects.requireNonNull(userMapper).findAll();

    }

    @Nullable
    @Override
    public User findOne(@NotNull final String id) {
        return Objects.requireNonNull(userMapper).findOne(id);
    }

    @Nullable
    public User findOneByLogin(@NotNull final String login) {
        return Objects.requireNonNull(userMapper).findOneByLogin(login);
    }
}
