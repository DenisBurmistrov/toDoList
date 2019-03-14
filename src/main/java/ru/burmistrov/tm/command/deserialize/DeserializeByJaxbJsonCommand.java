package ru.burmistrov.tm.command.deserialize;

import org.eclipse.persistence.jaxb.UnmarshallerProperties;
import org.jetbrains.annotations.NotNull;
import ru.burmistrov.tm.command.AbstractCommand;
import ru.burmistrov.tm.entity.Domain;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;

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
        Unmarshaller jaxbContextUnmarshaller = jaxbContext.createUnmarshaller();

        //Set JSON type
        jaxbContextUnmarshaller.setProperty(UnmarshallerProperties.MEDIA_TYPE, "application/json");
        StreamSource json = new StreamSource("projects-and-tasks-by-"
                + getServiceLocator().getCurrentUser().getLogin() + ".json");

        Domain domain = jaxbContextUnmarshaller.unmarshal(json, Domain.class).getValue();

        System.out.println(domain);
    }

    @Override
    public boolean isSecure() {
        return true;
    }
}
