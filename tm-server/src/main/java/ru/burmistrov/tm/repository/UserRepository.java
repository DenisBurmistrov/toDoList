package ru.burmistrov.tm.repository;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import ru.burmistrov.tm.api.repository.IUserRepository;
import ru.burmistrov.tm.entity.User;
import ru.burmistrov.tm.entity.enumerated.Role;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

@Getter
@NoArgsConstructor
public class UserRepository implements IUserRepository {

    @Inject
    @NotNull private EntityManager entityManager;

    @Override
    public void persist(@NotNull final User user) {
        entityManager.persist(user);
    }

    @Override
    public void merge(@NotNull final User user) {
        entityManager.merge(user);
    }

    @Override
    public void remove(@NotNull final String id) {
        entityManager.createQuery("DELETE from User user WHERE user.id = '" + id + "'");
    }

    @Override
    public void removeAll() {
        entityManager.createQuery("DELETE from User user");
    }

    @Override
    public List<User> findAll() {
        return entityManager.createQuery("SELECT user FROM User user").getResultList();
    }

    @Override
    public User findOne(@NotNull final String id) {
        return (User) entityManager.createQuery("SELECT user FROM User user WHERE user.id = '" + id + "'").getSingleResult();
    }

    @Override
    public User findOneByLogin(@NotNull final String login) {
        return (User) entityManager.createQuery("SELECT user FROM User user WHERE user.login = '" + login + "'").getSingleResult();
    }

    @Override
    public void updatePassword(@NotNull final String login, @NotNull String newPassword) {
        entityManager.createQuery("UPDATE user SET " +
                "user.password = '" + newPassword + "', user.login = '" + login + "'");
    }
}
