package ru.burmistrov.tm.entity.enumerated;

import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.Serializable;

public enum Role implements Serializable {

    ADMINISTRATOR("Администратор"),
    COMMON_USER("Обычный пользователь");


    @NotNull private final String displayName;

    Role(@NotNull final String name) {
        displayName = name;
    }

    @Override
    public String toString() {
        return displayName;
    }

}
