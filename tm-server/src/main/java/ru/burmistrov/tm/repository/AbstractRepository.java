package ru.burmistrov.tm.repository;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.burmistrov.tm.entity.AbstractEntity;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.*;

public abstract class AbstractRepository<T extends AbstractEntity> {

    Map<String, T> map = new LinkedHashMap<>();

    public AbstractRepository() {
    }

    public Map<String, T> getMap() {
        return map;
    }
}
