package ru.burmistrov.tm.command.admin.deserialize;

import org.jetbrains.annotations.NotNull;
import ru.burmistrov.tm.api.loader.ServiceLocator;
import ru.burmistrov.tm.command.AbstractCommand;
import ru.burmistrov.tm.endpoint.AdminEndpoint;
import ru.burmistrov.tm.endpoint.Exception_Exception;
import ru.burmistrov.tm.endpoint.Session;

import javax.inject.Inject;

public class DeserializeByDefaultCommand extends AbstractCommand {

    @Inject
    private ServiceLocator serviceLocator;

    @Inject
    private AdminEndpoint adminEndpoint;

    @NotNull
    @Override
    public String getName() {
        return "-deserializeByDefault";
    }

    @NotNull
    @Override
    public String getDescription() {
        return "Serialize domain object";
    }

    @Override
    public void execute() throws Exception_Exception {
        adminEndpoint.loadDataByDefault(serviceLocator.getSession());

    }

    @Override
    public boolean isSecure() {
        return true;
    }


}
