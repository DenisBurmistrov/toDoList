package ru.burmistrov.tm.entity;

import lombok.Data;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.persistence.*;
import java.util.UUID;

@Data
@MappedSuperclass
public abstract class AbstractEntity {

    public AbstractEntity() {
    }

    @NotNull
    @Id
    private String id = UUID.randomUUID().toString();

    @Nullable
    @Column(name = "user_id")
    @ManyToOne(cascade = CascadeType.ALL)
    private String userId;
}
