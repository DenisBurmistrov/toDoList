package ru.burmistrov.tm.repository;

import ru.burmistrov.tm.api.repository.IUserRepository;
import ru.burmistrov.tm.entity.AbstractEntity;
import ru.burmistrov.tm.entity.User;
import ru.burmistrov.tm.utils.PasswordUtil;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public final class UserRepository extends AbstractRepository implements IUserRepository {

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
    public void updatePassword(String userId, String login, String newPassword) {

        for (Map.Entry<String, User> entry : users.entrySet()) {
            if (entry.getValue().getLogin().equals(login)) {
                entry.getValue().setPassword(newPassword);
            }
        }
    }

    @Override
    public AbstractEntity persist(AbstractEntity entity) {
        users.put(entity.getId(), (User) entity);
        return entity;
    }

    @Override
    public void merge(AbstractEntity entity) {
        users.put(entity.getId(), (User) entity);
    }

    @Override
    public void remove(AbstractEntity abstractEntity) {
        User user = (User) abstractEntity;
        users.remove(user.getId());
    }

    @Override
    public void removeAll(AbstractEntity abstractEntity) {
        users.clear();
    }

    @Override
    public List<AbstractEntity> findAll(AbstractEntity abstractEntity) {

        List<AbstractEntity> result = new LinkedList<>();
        users.forEach((k, v) -> result.add(v));
        return result;
    }

    @Override
    public AbstractEntity findOne(AbstractEntity abstractEntity) {
        User user = (User) abstractEntity;
        List list = users.entrySet().stream().filter(e -> user.equals(e.getValue())).collect(Collectors.toList());
        if(list.size() > 0) {
            return (AbstractEntity) list.get(0);
        }
        return null;
    }
}
