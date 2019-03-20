package ru.burmistrov.tm.command.admin.serialize;

import org.jetbrains.annotations.NotNull;
import ru.burmistrov.tm.command.AbstractCommand;
import ru.burmistrov.tm.endpoint.CloneNotSupportedException_Exception;
import ru.burmistrov.tm.endpoint.IOException_Exception;
import ru.burmistrov.tm.endpoint.JAXBException_Exception;

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
    public void execute() throws JAXBException_Exception, IOException_Exception, CloneNotSupportedException_Exception {
        getServiceLocator().getAdminEndpoint().saveDataByJaxbXml(getServiceLocator().getSession());
    }

    @Override
    public boolean isSecure() {
        return true;
    }
}