package ru.burmistrov.tm.entity.enumerated;

import org.jetbrains.annotations.NotNull;

import java.io.Serializable;

public enum Status implements Serializable {

    SHEDULED("Запланировано"),
    IN_PROCESS("В процессе"),
    COMPLETE("Готово");

    @NotNull
    private final String displayName;

    Status(@NotNull String displayName) {
        this.displayName = displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }
}
