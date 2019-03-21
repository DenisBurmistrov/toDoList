package ru.burmistrov.tm.entity.enumerated;

import org.jetbrains.annotations.NotNull;

import java.io.Serializable;

public enum Role implements Serializable {

    ADMINISTRATOR("Администратор"),
    COMMON_USER("Обычный пользователь");

    @NotNull
    private final String displayName;

    Role(@NotNull String displayName) {
        this.displayName = displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }
}
