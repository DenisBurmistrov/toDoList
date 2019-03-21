package ru.burmistrov.tm.command.admin.serialize;

import org.jetbrains.annotations.NotNull;
import ru.burmistrov.tm.command.AbstractCommand;
import ru.burmistrov.tm.endpoint.Exception_Exception;

public class SerializeByFasterXmlCommand extends AbstractCommand {

    @NotNull
    @Override
    public String getName() {
        return "-serializeByFasterXml";
    }

    @NotNull
    @Override
    public String getDescription() {
        return "Serialize all projects and tasks in Json";
    }

    @Override
    public void execute() throws Exception_Exception {

        getServiceLocator().getAdminEndpoint().saveDataByFasterXml(getServiceLocator().getSession());
    }

    @Override
    public boolean isSecure() {
        return true;
    }
}
