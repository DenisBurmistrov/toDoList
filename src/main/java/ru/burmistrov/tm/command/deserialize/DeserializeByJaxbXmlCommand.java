package ru.burmistrov.tm.command.deserialize;

import org.jetbrains.annotations.NotNull;
import ru.burmistrov.tm.command.AbstractCommand;
import ru.burmistrov.tm.entity.Domain;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.text.ParseException;

public class DeserializeByJaxbXmlCommand extends AbstractCommand {

    @NotNull
    @Override
    public String getName() {
        return "-deserializeByJaxbXml";
    }

    @NotNull
    @Override
    public String getDescription() {
        return "Deserialize domain in XML";
    }

    @Override
    public void execute() throws JAXBException {

        File file = new File("projects-and-tasks-by-"
                + getServiceLocator().getCurrentUser().getLogin() + ".xml");
        JAXBContext jaxbContext = JAXBContext.newInstance(Domain.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        Domain domain = (Domain) unmarshaller.unmarshal(file);
        System.out.println(domain.getProjects());
        System.out.println(domain.getTasks());

    }

    @Override
    public boolean isSecure() {
        return true;
    }
}
