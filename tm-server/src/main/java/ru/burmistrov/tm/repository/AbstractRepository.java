package ru.burmistrov.tm.repository;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.burmistrov.tm.entity.AbstractEntity;
import ru.burmistrov.tm.entity.Project;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

public abstract class AbstractRepository<T extends AbstractEntity> {

    private LinkedHashMap<String, T> abstractMap;

    public AbstractRepository(LinkedHashMap<String, T> abstractMap) {
        this.abstractMap = abstractMap;
    }

    @NotNull
    public T persist(@NotNull T abstractEntity) throws IOException {
        abstractMap.put(abstractEntity.getId(), abstractEntity);
        return abstractEntity;
    }

    public void merge(@NotNull T abstractEntity) {
        abstractMap.put(abstractEntity.getId(), abstractEntity);
    }

    public void remove(@NotNull T abstractEntity) {
        abstractMap.remove(abstractEntity.getId());
    }

    public void removeAll(@NotNull T abstractEntity) {
        abstractMap.entrySet().removeIf(e -> Objects.requireNonNull
                (e.getValue().getUserId()).equals(abstractEntity.getUserId()));
    }

    @NotNull
    public List<T> findAll(@NotNull T abstractEntity) {
        @NotNull final List<T> result = new LinkedList<>();
        abstractMap.entrySet()
                .stream().filter(e -> Objects.requireNonNull(e.getValue().getUserId()).equals(abstractEntity.getUserId()))
                .forEach(e -> result.add(e.getValue()));
        return result;
    }

    @Nullable
    public T findOne(@NotNull T abstractEntity) {
        @NotNull final List<T> result = new ArrayList<>();
        abstractMap.forEach((k, v) -> {
            if (abstractEntity.getId().equals(k)) {
                result.add(v);
            }
        });
        if (result.size() > 0) {
            return result.get(0);
        }
        return null;
    }

    public LinkedHashMap<String, T> getAbstractMap() {
        return abstractMap;
    }
}
