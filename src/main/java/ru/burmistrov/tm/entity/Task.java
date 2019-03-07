package ru.burmistrov.tm.entity;

import java.util.Date;
import java.util.Objects;
import java.util.UUID;

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
        return getId().equals(task.getId()) &&
                projectId.equals(task.projectId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), projectId);
    }

    @Override
    public String toString() {
        return "ID: " + getId() +
                "; Название: " + name +
                "; Описание: " + description +
                "; ID проекта: " + projectId;
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

    public Date getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(Date dateEnd) {
        this.dateEnd = dateEnd;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
