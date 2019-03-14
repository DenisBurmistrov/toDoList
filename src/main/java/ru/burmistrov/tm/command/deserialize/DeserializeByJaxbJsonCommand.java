package ru.burmistrov.tm.command.deserialize;

import org.eclipse.persistence.jaxb.UnmarshallerProperties;
import org.jetbrains.annotations.NotNull;
import ru.burmistrov.tm.command.AbstractCommand;
import ru.burmistrov.tm.entity.Domain;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;

public class DeserializeByJaxbJsonCommand extends AbstractCommand {

    @NotNull
    @Override
    public String getName() {
        return "-deserializeByJaxbJson";
    }

    @NotNull
    @Override
    public String getDescription() {
        return "Deserialize domain from Json";
    }

    @Override
    public void execute() throws JAXBException {
        System.setProperty("javax.xml.bind.context.factory", "org.eclipse.persistence.jaxb.JAXBContextFactory");

        JAXBContext jaxbContext = JAXBContext.newInstance(Domain.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

        //Set JSON type
        unmarshaller.setProperty(UnmarshallerProperties.MEDIA_TYPE, "application/json");
        unmarshaller.setProperty(UnmarshallerProperties.JSON_INCLUDE_ROOT, true);
        Domain domain = (Domain) unmarshaller.unmarshal(new File("C:\\Users\\d.burmistrov\\IdeaProjects\\toDoList\\" + "projects-and-tasks-by-"
                + getServiceLocator().getCurrentUser().getLogin() + ".json"));
        System.out.println(domain.getProjects());
        System.out.println(domain.getTasks());

    }

    @Override
    public boolean isSecure() {
        return true;
    }
}
