package ru.burmistrov.tm.command.admin.serialize;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jetbrains.annotations.NotNull;
import ru.burmistrov.tm.command.AbstractCommand;
import ru.burmistrov.tm.entity.AbstractEntity;
import ru.burmistrov.tm.entity.Domain;
import ru.burmistrov.tm.entity.Project;
import ru.burmistrov.tm.entity.Task;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class SerializeByFasterXmlJsonCommand extends AbstractCommand {
    @NotNull
    @Override
    public String getName() {
        return "-serializeByFasterXmlJson";
    }

    @NotNull
    @Override
    public String getDescription() {
        return "Serialize all projects and tasks in Json";
    }

    @Override
    public void execute() throws IOException {

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

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.writeValue(new File("projects-and-tasks-by-"
                + getServiceLocator().getCurrentUser().getLogin() + ".json"), domain);
    }

    @Override
    public boolean isSecure() {
        return false;
    }
}
