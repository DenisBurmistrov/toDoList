package ru.burmistrov.tm.repository;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.burmistrov.tm.api.repository.IUserRepository;
import ru.burmistrov.tm.entity.AbstractEntity;
import ru.burmistrov.tm.entity.User;
import ru.burmistrov.tm.utils.PasswordUtil;

import java.util.*;

public final class UserRepository extends AbstractRepository implements IUserRepository<AbstractEntity>  {

    private final Map<String, User> users = new LinkedHashMap<>();

    @Nullable
    @Override
    public User logIn(@Nullable String login, @Nullable String password) {

        for (Map.Entry<String, User> entry : users.entrySet()) {
            if (password != null && entry.getValue().getLogin().equals(login) && entry.getValue().getPassword().equals(PasswordUtil.hashPassword(password))) {
                return entry.getValue();
            }
        }
        return null;
    }

    @Override
    public void updatePassword(@Nullable String userId,@Nullable String login, @Nullable String newPassword) {

        for (Map.Entry<String, User> entry : users.entrySet()) {
            if (entry.getValue().getLogin().equals(login)) {
                entry.getValue().setPassword(newPassword);
            }
        }
    }

    @NotNull
    @Override
    public AbstractEntity persist(@Nullable AbstractEntity entity) {
        users.put(entity.getId(), (User) entity);
        return entity;
    }

    @Override
    public void merge(@Nullable  AbstractEntity entity) {
        User user = (User) entity;
        users.get(entity.getId()).setFirstName(user.getFirstName());
        users.get(entity.getId()).setMiddleName(user.getMiddleName());
        users.get(entity.getId()).setLastName(user.getLastName());
        users.get(entity.getId()).setEmail(user.getEmail());
    }

    @Override
    public void remove(@Nullable AbstractEntity abstractEntity) {
        User user = (User) abstractEntity;
        users.remove(user.getId());
    }

    @Override
    public void removeAll(@Nullable AbstractEntity abstractEntity) {
        users.clear();
    }

    @NotNull
    @Override
    public List<AbstractEntity> findAll(@Nullable AbstractEntity abstractEntity) {

        List<AbstractEntity> result = new LinkedList<>();
        users.forEach((k, v) -> result.add(v));
        return result;
    }

    @Nullable
    @Override
    public AbstractEntity findOne(@Nullable AbstractEntity abstractEntity) {
        User user = (User) abstractEntity;
        List<User> result = new ArrayList<>();
        users.forEach((k,v) -> {
            if(v.equals(user)) {
                result.add(v);
            }
        });
        if(result.size() > 0) {
            return result.get(0);
        }
        return null;
    }
}
