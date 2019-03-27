package ru.burmistrov.tm.entity.enumerated;

import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.Serializable;


@NoArgsConstructor
public enum Status implements Serializable {

    SHEDULED("Запланировано"),
    IN_PROCESS("В процессе"),
    COMPLETE("Готово");

    @Nullable
    private String displayName;

    Status(@NotNull String displayName) {
        this.displayName = displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }
}
