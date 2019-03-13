package ru.burmistrov.tm.entity;

import lombok.Data;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Date;
import java.util.Objects;

@Data
public final class Task extends AbstractEntity {

    @NotNull
    private String projectId;

    @NotNull
    private String name;

    @NotNull
    private String description;

    @NotNull
    private Date dateBegin = new Date();

    @NotNull
    private Date dateEnd;

    @NotNull
    private String userId;

    @NotNull
    private Status status = Status.SHEDULED;

    public Task() {
    }

    @NotNull
    public String getName() {
        return name;
    }

    public void setName(@NotNull String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return Objects.equals(projectId, task.projectId) &&
                Objects.equals(name, task.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(projectId, name);
    }

    @Override
    public String toString() {
        return "ID: " + getId() +
                "; Название: " + name +
                "; Описание: " + description +
                "; ID проекта: " + projectId;
    }
}
