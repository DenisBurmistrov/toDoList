package ru.burmistrov.tm.entity.enumerated;

import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.Serializable;

public enum Status implements Serializable {

    SCHEDULED("Запланировано"),
    IN_PROCESS("В процессе"),
    COMPLETE("Готово");

    @NotNull private final String displayName;

    Status(@NotNull final String name) {
    displayName = name;
    }

    @Override
    public String toString() {
        return displayName;
    }

    @Nullable
    public static Status createStatus(@NotNull final String string) {
        switch (string) {
            case "Запланировано":
                return Status.SCHEDULED;
            case "В процессе":
                return Status.IN_PROCESS;
            case "Готово":
                return Status.COMPLETE;
        }
        return null;
    }
}
