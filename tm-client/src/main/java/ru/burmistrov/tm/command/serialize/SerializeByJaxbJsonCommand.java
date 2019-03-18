package ru.burmistrov.tm.command.serialize;

import org.jetbrains.annotations.NotNull;
import ru.burmistrov.tm.command.AbstractCommand;
import ru.burmistrov.tm.entity.AbstractEntity;
import ru.burmistrov.tm.entity.Domain;
import ru.burmistrov.tm.entity.Project;
import ru.burmistrov.tm.entity.Task;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class SerializeByJaxbJsonCommand extends AbstractCommand {

    @NotNull
    @Override
    public String getName() {
        return "-serializeByJaxbJson";
    }

    @NotNull
    @Override
    public String getDescription() {
        return "Serialize all projects and tasks in Json";
    }

    @Override
    public void execute() throws JAXBException, IOException {
        System.setProperty("javax.xml.bind.context.factory","org.eclipse.persistence.jaxb.JAXBContextFactory");
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
        JAXBContext jaxbContext = JAXBContext.newInstance(Domain.class);
        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty("eclipselink.media-type", "application/json");

        marshaller.marshal(domain,  new FileWriter("projects-and-tasks-by-"
                + getServiceLocator().getCurrentUser().getLogin() + ".json"));
    }

    @Override
    public boolean isSecure() {
        return true;
    }
}
