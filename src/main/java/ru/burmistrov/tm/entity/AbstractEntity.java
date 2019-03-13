package ru.burmistrov.tm.entity;

import lombok.Data;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

@Data
public abstract class AbstractEntity {

    @NotNull
    private String id = UUID.randomUUID().toString();

}
