package ru.burmistrov.tm.entity;

import lombok.Data;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

@Data
public abstract class AbstractEntity {

    public AbstractEntity() {
    }

    @NotNull
    private String id = UUID.randomUUID().toString();

    @Nullable
    private String userId;
}
