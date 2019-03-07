package ru.burmistrov.tm.repository;

import ru.burmistrov.tm.api.repository.IUserRepository;
import ru.burmistrov.tm.entity.AbstractEntity;
import ru.burmistrov.tm.entity.User;
import ru.burmistrov.tm.utils.PasswordUtil;

import java.util.LinkedHashMap;
import java.util.Map;

public final class UserRepository implements IUserRepository {

    private final Map<String, User> users = new LinkedHashMap<>();

    @Override
    public User logIn(String login, String password) {

        for (Map.Entry<String, User> entry : users.entrySet()) {
            if (entry.getValue().getLogin().equals(login) && entry.getValue().getPassword().equals(PasswordUtil.hashPassword(password))) {
                return entry.getValue();
            }
        }
        return null;
    }

    @Override
    public AbstractEntity persist(AbstractEntity entity) {
        for (Map.Entry<String, User> entry : users.entrySet()) {
            if (entry.getValue().equals(entity)) {
                return null;
            }
        }
        users.put(entity.getId(), (User) entity);
        return entity;
    }

    @Override
    public void updatePassword(String userId, String login, String newPassword) {

        for (Map.Entry<String, User> entry : users.entrySet()) {
            if (entry.getValue().getLogin().equals(login)) {
                entry.getValue().setPassword(newPassword);
            }
        }
    }

    @Override
    public void merge(AbstractEntity entity) {

        users.put(entity.getId(), (User) entity);
    }

    @Override
    public Map<String, AbstractEntity> getAbstractEntities() {
        Map<String, AbstractEntity> map = new LinkedHashMap<>();
        users.forEach(map::put);
        return map;
    }
}
