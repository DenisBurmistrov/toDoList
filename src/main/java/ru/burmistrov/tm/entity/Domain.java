package ru.burmistrov.tm.entity;

import lombok.Data;
import ru.burmistrov.tm.repository.ProjectRepository;
import ru.burmistrov.tm.repository.TaskRepository;

import java.io.Serializable;
import java.util.Map;

@Data
public class Domain implements Serializable {

    private ProjectRepository projectRepository = new ProjectRepository();

    private TaskRepository taskRepository = new TaskRepository();

    Map<String, Project> projects = projectRepository.projects;

    Map<String, Task> tasks = taskRepository.tasks;

}
