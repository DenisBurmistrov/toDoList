package ru.burmistrov.tm.command.admin.deserialize;

import org.jetbrains.annotations.NotNull;
import ru.burmistrov.tm.command.AbstractCommand;
import ru.burmistrov.tm.endpoint.JAXBException_Exception;

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
    public void execute() throws JAXBException_Exception {
        getServiceLocator().getAdminEndpoint().loadDataByJaxbJson(getServiceLocator().getSession());
    }

    @Override
    public boolean isSecure() {
        return true;
    }
}
