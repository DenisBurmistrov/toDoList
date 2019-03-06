package ru.burmistrov.tm.entity;

import java.util.*;

public final class Project {

    private String id = UUID.randomUUID().toString();
    private String userId;
    private String name;
    private String description;
    private Date dateBegin = new Date();
    private Date dateEnd;

    public Project() {
    }

    public Project(String name, String description) {
        this.name = name;
        this.description = description;
        this.dateBegin = new Date();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDateBegin() {
        return dateBegin;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Project project = (Project) o;
        return Objects.equals(name, project.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "ID: " + id + "; Название: " + name + "; Описание: " + description + "; Дата создания: " + dateBegin + "; ID назначенного пользователя: " + userId;
    }

    public Date getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(Date dateEnd) {
        this.dateEnd = dateEnd;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
