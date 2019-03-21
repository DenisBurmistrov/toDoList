package ru.burmistrov.tm.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.burmistrov.tm.entity.enumerated.Status;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Data
@NoArgsConstructor
public final class Project extends AbstractEntity implements Serializable {

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Project project = (Project) o;
        return Objects.equals(getUserId(), project.getUserId()) &&
                Objects.equals(name, project.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUserId(), name);
    }

    @Override
    public String toString() {
        return "ID: " + getId() + "; Название: " + name + "; Описание: " + description + "; Дата создания: " + dateBegin + "; ID назначенного пользователя: " + getUserId();
    }

}
