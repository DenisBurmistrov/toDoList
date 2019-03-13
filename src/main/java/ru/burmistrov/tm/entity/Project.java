package ru.burmistrov.tm.entity;

import lombok.Data;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.Serializable;
import java.util.*;

@Data
public final class Project extends AbstractEntity implements Serializable {

    @Nullable
    private String userId;

    @Nullable
    private String name;

    @Nullable
    private String description;

    @NotNull
    private Date dateBegin = new Date();

    @Nullable
    private Date dateEnd;

    @NotNull
    private Status status = Status.SHEDULED;

    public Project() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Project project = (Project) o;
        return Objects.equals(userId, project.userId) &&
                Objects.equals(name, project.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, name);
    }

    @Override
    public String toString() {
        return "ID: " + getId() + "; Название: " + name + "; Описание: " + description + "; Дата создания: " + dateBegin + "; ID назначенного пользователя: " + userId;
    }

}
