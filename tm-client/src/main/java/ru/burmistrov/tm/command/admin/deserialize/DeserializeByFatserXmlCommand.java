package ru.burmistrov.tm.command.admin.deserialize;

import org.jetbrains.annotations.NotNull;
import ru.burmistrov.tm.command.AbstractCommand;
import ru.burmistrov.tm.endpoint.CloneNotSupportedException_Exception;
import ru.burmistrov.tm.endpoint.IOException_Exception;

public class DeserializeByFatserXmlCommand extends AbstractCommand {
    @NotNull
    @Override
    public String getName() {
        return "-deserializeByFasterXml";
    }

    @NotNull
    @Override
    public String getDescription() {
        return "Deserialize domain from xml";
    }

    @Override
    public void execute() throws IOException_Exception, CloneNotSupportedException_Exception {
        getServiceLocator().getAdminEndpoint().loadDataByFasterXml(getServiceLocator().getSession());
    }

    @Override
    public boolean isSecure() {
        return true;
    }
}
