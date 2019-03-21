package ru.burmistrov.tm.repository;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.burmistrov.tm.entity.AbstractEntity;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.*;

public abstract class AbstractRepository<T extends AbstractEntity> {

    Map<String, T> map = new LinkedHashMap<>();

    public AbstractRepository() {
    }

    @NotNull
    public T persist(@NotNull final T abstractEntity) throws IOException, NoSuchAlgorithmException {
        map.put(abstractEntity.getId(), abstractEntity);
        return abstractEntity;
    }

    public void merge(@NotNull final T abstractEntity) {
        map.put(abstractEntity.getId(), abstractEntity);
    }

    public void remove(@NotNull final T abstractEntity) {
        map.remove(abstractEntity.getId());
    }

    public void removeAll(@NotNull final T abstractEntity) {
        map.entrySet().removeIf(e -> Objects.requireNonNull
                (e.getValue().getUserId()).equals(abstractEntity.getUserId()));
    }

    @NotNull
    public List<T> findAll(@NotNull final T abstractEntity) {
        @NotNull final List<T> result = new LinkedList<>();
        map.entrySet()
                .stream().filter(e -> Objects.requireNonNull(e.getValue().getUserId()).equals(abstractEntity.getUserId()))
                .forEach(e -> result.add(e.getValue()));
        return result;
    }

    @Nullable
    public T findOne(@NotNull final T abstractEntity) {
        @NotNull final List<T> result = new ArrayList<>();
        map.forEach((k, v) -> {
            if (abstractEntity.getId().equals(k)) {
                result.add(v);
            }
        });
        if (result.size() > 0) {
            return result.get(0);
        }
        return null;
    }

    public Map<String, T> getMap() {
        return map;
    }
}
