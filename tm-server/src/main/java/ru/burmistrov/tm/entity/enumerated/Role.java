package ru.burmistrov.tm.entity.enumerated;

import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.Serializable;

//@NoArgsConstructor
public enum Role implements Serializable {

    ADMINISTRATOR(),
    COMMON_USER()

}
