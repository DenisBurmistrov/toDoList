package ru.burmistrov.tm.command.admin.serialize;

import org.jetbrains.annotations.NotNull;
import ru.burmistrov.tm.command.AbstractCommand;
import ru.burmistrov.tm.entity.AbstractEntity;
import ru.burmistrov.tm.entity.Domain;
import ru.burmistrov.tm.entity.Project;
import ru.burmistrov.tm.entity.Task;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.LinkedList;
import java.util.List;

public class SerializeByDefaultCommand extends AbstractCommand {

    @NotNull
    @Override
    public String getName() {
        return "-serializeByDefault";
    }

    @NotNull
    @Override
    public String getDescription() {
        return "Serialize all projects and tasks";
    }

    @Override
    public void execute() throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("projects-and-tasks-by-"
                + getServiceLocator().getCurrentUser().getLogin() + ".dat"));
        Domain domain = new Domain();
        List<Project> projects = new LinkedList<>();
        List<Task> tasks = new LinkedList<>();

        for(AbstractEntity project : getServiceLocator().getProjectService().findAll(getServiceLocator().getCurrentUser().getId()))
        {
            Project receivedProject = (Project) project;
            projects.add(receivedProject);
        }

        for (AbstractEntity task : getServiceLocator().getTaskService().findAll(getServiceLocator().getCurrentUser().getId())) {
            Task receivedTask = (Task) task;
            tasks.add(receivedTask);
        }
        domain.setProjects(projects);
        domain.setTasks(tasks);
        oos.writeObject(domain);
    }

    @Override
    public boolean isSecure() {
        return true;
    }
}
