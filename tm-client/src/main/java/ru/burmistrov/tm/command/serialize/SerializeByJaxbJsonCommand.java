package ru.burmistrov.tm.command.serialize;

import org.jetbrains.annotations.NotNull;
import ru.burmistrov.tm.command.AbstractCommand;
import ru.burmistrov.tm.endpoint.IOException_Exception;
import ru.burmistrov.tm.endpoint.JAXBException_Exception;

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
    public void execute() throws JAXBException_Exception, IOException_Exception {
        getServiceLocator().getAdminEndpoint().saveDataByJaxbJson(getServiceLocator().getSession());
    }

    @Override
    public boolean isSecure() {
        return true;
    }
}
