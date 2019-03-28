package ru.burmistrov.tm.entity.enumerated;

import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.Serializable;

public enum Status implements Serializable {

    SCHEDULED(),
    IN_PROCESS(),
    COMPLETE()
}
