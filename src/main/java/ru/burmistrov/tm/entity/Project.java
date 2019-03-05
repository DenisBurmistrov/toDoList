package ru.burmistrov.tm.entity;

import java.util.*;

public class Project {

    private String name;
    private String description;
    private Date createdAt;
    private Date shouldEndAt;
    private String id = UUID.randomUUID().toString();

    public Project() {
        createdAt = new Date();
    }

    public Project(String name, String description) {
        this.name = name;
        this.description = description;
        this.createdAt = new Date();
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

    public Date getCreatedAt() {
        return createdAt;
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
        return "ID: " + id +"; Название: " + name + "; Описание: " + description + "; Дата создания: " + createdAt;
    }

    public Date getShouldEndAt() {
        return shouldEndAt;
    }

    public void setShouldEndAt(Date shouldEndAt) {
        this.shouldEndAt = shouldEndAt;
    }
}
