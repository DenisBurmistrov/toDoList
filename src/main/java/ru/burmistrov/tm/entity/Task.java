package ru.burmistrov.tm.entity;

import lombok.Data;

import java.util.Date;
import java.util.Objects;

@Data
public final class Task extends AbstractEntity {

    private String projectId;
    private String name;
    private String description;
    private Date dateBegin = new Date();
    private Date dateEnd;
    private String userId;

    public Task() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
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
