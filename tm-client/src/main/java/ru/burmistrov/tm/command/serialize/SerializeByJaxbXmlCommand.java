package ru.burmistrov.tm.command.serialize;

import org.jetbrains.annotations.NotNull;
import ru.burmistrov.tm.command.AbstractCommand;
import ru.burmistrov.tm.endpoint.IOException_Exception;
import ru.burmistrov.tm.endpoint.JAXBException_Exception;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;

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
    public void execute() throws JAXBException_Exception, IOException_Exception {
        getServiceLocator().getAdminEndpoint().saveDataByJaxbXml(getServiceLocator().getSession());
    }

    @Override
    public boolean isSecure() {
        return true;
    }
}
