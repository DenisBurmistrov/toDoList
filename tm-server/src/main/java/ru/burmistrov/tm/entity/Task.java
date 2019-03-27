package ru.burmistrov.tm.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.burmistrov.tm.entity.enumerated.Status;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Getter
@Setter
public final class Task extends AbstractEntity implements Serializable {

    @Nullable
    private String projectId;

    @Nullable
    private String name;

    @Nullable
    private String description;

    @NotNull
    private Date dateBegin = new Date();

    @Nullable
    private Date dateEnd;

    @Nullable
    private Status status;

    public Task() {
    }

    @Nullable
    public String getName() {
        return name;
    }

    public void setName(@NotNull final String name) {
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
                "; ID проекта: " + projectId +
                "; Статус: " + status;

    }
}
