package ru.burmistrov.tm.command.admin.deserialize;

import org.jetbrains.annotations.NotNull;
import ru.burmistrov.tm.command.AbstractCommand;
import ru.burmistrov.tm.endpoint.Exception_Exception;

public class DeserializeByFasterXmlJsonCommand extends AbstractCommand {
    @NotNull
    @Override
    public String getName() {
        return "-deserializeByFasterXmlJson";
    }

    @NotNull
    @Override
    public String getDescription() {
        return "Deserialize domain fron json";
    }

    @Override
    public void execute() throws  Exception_Exception {
        getServiceLocator().getAdminEndpoint().loadDataByFasterXmlJson(getServiceLocator().getSession());
    }

    @Override
    public boolean isSecure() {
        return true;
    }
}
