package ru.burmistrov.tm.entity;

import lombok.Data;

import java.util.*;

@Data
public final class Project extends AbstractEntity {

    private String userId;
    private String name;
    private String description;
    private Date dateBegin = new Date();
    private Date dateEnd;
    private Status status = Status.SHEDULED;

    public Project() {
    }

    public Project(String name, String description) {
        this.name = name;
        this.description = description;
        this.dateBegin = new Date();
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
