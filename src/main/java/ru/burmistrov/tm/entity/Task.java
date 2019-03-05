package ru.burmistrov.tm.entity;

import java.util.Date;
import java.util.Objects;
import java.util.UUID;

public class Task {

    private String id = UUID.randomUUID().toString();
    private String projectId;
    private String name;
    private String description;
    private Integer priority;
    private Date dateBegin = new Date();
    private Date dateEnd;
    private String userId;

    public Task() {
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPriority() {
        return priority;
    }

    public boolean setPriority(Integer priority) {
        if (priority >= 0 && priority <= 5) {
            this.priority = priority;
            return true;
        } else {
            return false;
        }
    }

    public void setId(String id) {
        this.id = id;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return id.equals(task.id) &&
                projectId.equals(task.projectId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, projectId);
    }

    @Override
    public String toString() {
        return "ID: " + id +
                "; Название: " + name +
                "; Описание: " + description +
                "; Приоритет: " + priority +
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
