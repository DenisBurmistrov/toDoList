package ru.burmistrov.tm.command.admin.serialize;

import org.jetbrains.annotations.NotNull;
import ru.burmistrov.tm.command.AbstractCommand;
import ru.burmistrov.tm.endpoint.IOException_Exception;

public class SerializeByFatserXmlCommand extends AbstractCommand {
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
    public void execute() throws IOException_Exception {

        getServiceLocator().getAdminEndpoint().saveDataByFasterXml(getServiceLocator().getSession());
    }

    @Override
    public boolean isSecure() {
        return true;
    }
}
