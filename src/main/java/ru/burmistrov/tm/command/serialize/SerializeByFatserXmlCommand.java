package ru.burmistrov.tm.command.serialize;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.jetbrains.annotations.NotNull;
import ru.burmistrov.tm.command.AbstractCommand;
import ru.burmistrov.tm.entity.AbstractEntity;
import ru.burmistrov.tm.entity.Domain;
import ru.burmistrov.tm.entity.Project;
import ru.burmistrov.tm.entity.Task;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.LinkedList;
import java.util.List;

public class SerializeByFatserXmlCommand extends AbstractCommand {
    @NotNull
    @Override
    public String getName() {
        return "-serializeByFasterXml";
    }

    @NotNull
    @Override
    public String getDescription() {
        return "Serialize all projects and tasks in Json";
    }

    @Override
    public void execute() throws ParseException, IOException, JAXBException {

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
        XmlMapper xmlMapper = new XmlMapper();
        xmlMapper.writeValue(new File("projects-and-tasks-by-"
                + getServiceLocator().getCurrentUser().getLogin() + ".xml"), domain);

    }

    @Override
    public boolean isSecure() {
        return true;
    }
}
