package ru.burmistrov.tm.entity;

import org.jetbrains.annotations.NotNull;

public enum Role {

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
