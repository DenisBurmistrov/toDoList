package ru.burmistrov.tm.command.admin.deserialize;

import org.jetbrains.annotations.NotNull;
import ru.burmistrov.tm.command.AbstractCommand;
import ru.burmistrov.tm.endpoint.JAXBException_Exception;

public class DeserializeByJaxbXmlCommand extends AbstractCommand {

    @NotNull
    @Override
    public String getName() {
        return "-deserializeByJaxbXml";
    }

    @NotNull
    @Override
    public String getDescription() {
        return "Deserialize domain from XML";
    }

    @Override
    public void execute() throws JAXBException_Exception {

        getServiceLocator().getAdminEndpoint().loadDataByJaxbXml(getServiceLocator().getSession());
    }

    @Override
    public boolean isSecure() {
        return true;
    }
}
