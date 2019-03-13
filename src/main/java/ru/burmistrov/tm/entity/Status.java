package ru.burmistrov.tm.entity;

import org.jetbrains.annotations.NotNull;

public enum Status {

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
