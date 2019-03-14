package ru.burmistrov.tm.command.serialize;

import org.jetbrains.annotations.NotNull;
import ru.burmistrov.tm.command.AbstractCommand;
import ru.burmistrov.tm.entity.AbstractEntity;
import ru.burmistrov.tm.entity.Domain;
import ru.burmistrov.tm.entity.Project;
import ru.burmistrov.tm.entity.Task;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class SerializeByJaxbXmlCommand extends AbstractCommand {

    @NotNull
    @Override
    public String getName() {
        return "-serializeByJaxbXml";
    }

    @NotNull
    @Override
    public String getDescription() {
        return "Serialize all projects and tasks in XML";
    }

    @Override
    public void execute() throws JAXBException, IOException {
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
        JAXBContext context = JAXBContext.newInstance(Domain.class);
        Marshaller m = context.createMarshaller();
        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        m.marshal(domain, new FileWriter("projects-and-tasks-by-"
                + getServiceLocator().getCurrentUser().getLogin() + ".xml"));
    }

    @Override
    public boolean isSecure() {
        return true;
    }
}
