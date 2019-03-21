package ru.burmistrov.tm.entity;

import jdk.nashorn.internal.objects.annotations.Getter;
import jdk.nashorn.internal.objects.annotations.Setter;

import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.util.List;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Domain implements Serializable {

    @XmlElementWrapper(name="projects")
    @XmlElement(name="project")
    private List<Project> projects;

    @XmlElementWrapper(name="tasks")
    @XmlElement(name="task")
    private List<Task> tasks;

    @XmlElementWrapper(name="users")
    @XmlElement(name="user")
    private List<User> users;

    public Domain() {
    }

    @Getter
    public List<Project> getProjects() {
        return projects;
    }

    @Setter
    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }

    @Getter
    public List<Task> getTasks() {
        return tasks;
    }

    @Setter
    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
