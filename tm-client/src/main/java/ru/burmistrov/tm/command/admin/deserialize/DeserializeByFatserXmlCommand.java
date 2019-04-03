package ru.burmistrov.tm.command.admin.deserialize;

import org.jetbrains.annotations.NotNull;
import ru.burmistrov.tm.api.loader.ServiceLocator;
import ru.burmistrov.tm.command.AbstractCommand;
import ru.burmistrov.tm.endpoint.AdminEndpoint;
import ru.burmistrov.tm.endpoint.Exception_Exception;
import ru.burmistrov.tm.endpoint.Session;

import javax.inject.Inject;

public class DeserializeByFatserXmlCommand extends AbstractCommand {

    @Inject
    private ServiceLocator serviceLocator;

    @Inject
    private AdminEndpoint adminEndpoint;

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
    public void execute() throws Exception_Exception {
        adminEndpoint.loadDataByFasterXml(serviceLocator.getSession());
    }

    @Override
    public boolean isSecure() {
        return true;
    }
}
