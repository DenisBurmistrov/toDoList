package ru.burmistrov.tm.entity;

import java.util.Date;
import java.util.Objects;

public class Task {

    private Long id;
    private String name;
    private String description;
    private Integer priority;
    private Date createdAt;
    private Date shouldEndAt;
    private static long counter = 1;

    public Task() {
        id = counter++;
    }

    public Long getId() {
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
        }
        else {
            System.out.println("Некорректное значение приоритета (Диапазон от 0 до 5)");
            return false;
        }
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return Objects.equals(name, task.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "ID: " + id +
                "; Название: " + name +
                "; Описание: " + description +
                "; Приоритет " + priority;
    }

    private void incrementCounter() {
        counter++;
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

    public Date getShouldEndAt() {
        return shouldEndAt;
    }

    public void setShouldEndAt(Date shouldEndAt) {
        this.shouldEndAt = shouldEndAt;
    }
}
