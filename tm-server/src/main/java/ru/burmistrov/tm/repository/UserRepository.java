package ru.burmistrov.tm.repository;

import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.burmistrov.tm.api.repository.IUserRepository;
import ru.burmistrov.tm.entity.User;
import ru.burmistrov.tm.utils.PasswordUtil;

import java.security.NoSuchAlgorithmException;
import java.util.*;

@NoArgsConstructor
public final class UserRepository extends AbstractRepository<User> implements IUserRepository  {

    @Nullable
    @Override
    public User logIn(@NotNull final String login, @NotNull final String password) throws NoSuchAlgorithmException {

        for (Map.Entry<String, User> entry : map.entrySet()) {
            if (Objects.requireNonNull(entry.getValue().getLogin()).equals(login) &&
                    Objects.requireNonNull(entry.getValue().getPassword()).equals(PasswordUtil.hashPassword(password))) {
                return entry.getValue();
            }
        }
        return null;
    }

    @Override
    public void updatePassword(@NotNull final String userId, @NotNull final String login, @NotNull final String newPassword) throws NoSuchAlgorithmException {

        for (Map.Entry<String, User> entry : map.entrySet()) {
            if (Objects.requireNonNull(entry.getValue().getLogin()).equals(login)) {
                entry.getValue().setPassword(newPassword);
            }
        }
    }

    @NotNull
    @Override
    public User persist(@Nullable final User entity) {
        map.put(Objects.requireNonNull(entity).getId(), entity);
        return entity;
    }

    @Override
    public void merge(@Nullable final User entity) {
        map.get(Objects.requireNonNull(entity).getId()).setFirstName(Objects.requireNonNull(entity).getFirstName());
        map.get(entity.getId()).setMiddleName(entity.getMiddleName());
        map.get(entity.getId()).setLastName(entity.getLastName());
        map.get(entity.getId()).setEmail(entity.getEmail());
    }

    @Override
    public void remove(@Nullable final User abstractEntity) {
        map.remove(Objects.requireNonNull(abstractEntity).getId());
    }

    @Override
    public void removeAll(@Nullable final User abstractEntity) {
        map.clear();
    }

    @NotNull
    @Override
    public List<User> findAll(@Nullable final User abstractEntity) {

        List<User> result = new LinkedList<>();
        map.forEach((k, v) -> result.add(v));
        return result;
    }

    @Nullable
    @Override
    public User findOne(@Nullable final User abstractEntity) {
        List<User> result = new ArrayList<>();
        map.forEach((k,v) -> {
            if(k.equals(Objects.requireNonNull(abstractEntity).getId())) {
                result.add(v);
            }
        });
        if(result.size() > 0) {
            return result.get(0);
        }
        return null;
    }
}
