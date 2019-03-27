package ru.burmistrov.tm.entity.enumerated;

import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.Serializable;

@NoArgsConstructor
public enum Role implements Serializable {

    ADMINISTRATOR("Администратор"),
    COMMON_USER("Обычный пользователь");

    @Nullable
    private String displayName;

    Role(@NotNull String displayName) {
        this.displayName = displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }
}
