package ru.burmistrov.tm.dto;

import jdk.nashorn.internal.objects.annotations.Getter;
import jdk.nashorn.internal.objects.annotations.Setter;
import org.jetbrains.annotations.NotNull;
import ru.burmistrov.tm.entity.Project;
import ru.burmistrov.tm.entity.Task;
import ru.burmistrov.tm.entity.User;

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
    @NotNull
    public List<Project> getProjects() {
        return projects;
    }

    @Setter
    public void setProjects(@NotNull final List<Project> projects) {
        this.projects = projects;
    }

    @Getter
    @NotNull
    public List<Task> getTasks() {
        return tasks;
    }

    @Setter
    public void setTasks(@NotNull final List<Task> tasks) {
        this.tasks = tasks;
    }

    @Getter
    @NotNull
    public List<User> getUsers() {
        return users;
    }

    @Setter
    public void setUsers(@NotNull final List<User> users) {
        this.users = users;
    }
}
