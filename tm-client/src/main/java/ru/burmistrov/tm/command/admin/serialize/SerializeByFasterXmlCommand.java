package ru.burmistrov.tm.command.admin.serialize;

import org.jetbrains.annotations.NotNull;
import ru.burmistrov.tm.api.loader.ServiceLocator;
import ru.burmistrov.tm.command.AbstractCommand;
import ru.burmistrov.tm.endpoint.AdminEndpoint;
import ru.burmistrov.tm.endpoint.Exception_Exception;
import ru.burmistrov.tm.endpoint.Session;

import javax.inject.Inject;

public class SerializeByFasterXmlCommand extends AbstractCommand {

    @Inject
    private ServiceLocator serviceLocator;

    @Inject
    private AdminEndpoint adminEndpoint;

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
        adminEndpoint.saveDataByFasterXml(serviceLocator.getSession());
    }

    @Override
    public boolean isSecure() {
        return true;
    }
}
