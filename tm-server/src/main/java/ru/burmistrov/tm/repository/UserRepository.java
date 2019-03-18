package ru.burmistrov.tm.repository;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.burmistrov.tm.api.repository.IUserRepository;
import ru.burmistrov.tm.entity.AbstractEntity;
import ru.burmistrov.tm.entity.User;
import ru.burmistrov.tm.utils.PasswordUtil;

import java.util.*;

public final class UserRepository implements IUserRepository  {

    private final Map<String, User> users = new LinkedHashMap<>();

    @Nullable
    @Override
    public User logIn(@Nullable String login, @Nullable String password) {

        for (Map.Entry<String, User> entry : users.entrySet()) {
            if (password != null && Objects.requireNonNull(entry.getValue().getLogin()).equals(login)
                    && Objects.requireNonNull(entry.getValue().getPassword()).equals(PasswordUtil.hashPassword(password))) {
                return entry.getValue();
            }
        }
        return null;
    }

    @Override
    public void updatePassword(@Nullable String userId,@Nullable String login, @Nullable String newPassword) {

        for (Map.Entry<String, User> entry : users.entrySet()) {
            if (Objects.requireNonNull(entry.getValue().getLogin()).equals(login)) {
                entry.getValue().setPassword(newPassword);
            }
        }
    }

    @NotNull
    @Override
    public User persist(@Nullable User entity) {
        users.put(Objects.requireNonNull(entity).getId(), entity);
        return entity;
    }

    @Override
    public void merge(@Nullable  User entity) {
        users.get(Objects.requireNonNull(entity).getId()).setFirstName(Objects.requireNonNull(entity).getFirstName());
        users.get(entity.getId()).setMiddleName(entity.getMiddleName());
        users.get(entity.getId()).setLastName(entity.getLastName());
        users.get(entity.getId()).setEmail(entity.getEmail());
    }

    @Override
    public void remove(@Nullable User abstractEntity) {
        users.remove(Objects.requireNonNull(abstractEntity).getId());
    }

    @Override
    public void removeAll(@Nullable User abstractEntity) {
        users.clear();
    }

    @NotNull
    @Override
    public List<User> findAll(@Nullable User abstractEntity) {

        List<User> result = new LinkedList<>();
        users.forEach((k, v) -> result.add(v));
        return result;
    }

    @Nullable
    @Override
    public User findOne(@Nullable User abstractEntity) {
        List<User> result = new ArrayList<>();
        users.forEach((k,v) -> {
            if(v.equals(abstractEntity)) {
                result.add(v);
            }
        });
        if(result.size() > 0) {
            return result.get(0);
        }
        return null;
    }
}
