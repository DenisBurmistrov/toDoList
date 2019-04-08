package ru.burmistrov.tm.command.admin.deserialize;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.burmistrov.tm.api.loader.ServiceLocator;
import ru.burmistrov.tm.command.AbstractCommand;
import ru.burmistrov.tm.endpoint.AdminEndpoint;
import ru.burmistrov.tm.endpoint.Exception_Exception;

@Component
public class DeserializeByFasterXmlJsonCommand extends AbstractCommand {

    @Autowired
    private ServiceLocator serviceLocator;

    @Autowired
    private AdminEndpoint adminEndpoint;

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
        adminEndpoint.loadDataByFasterXmlJson(serviceLocator.getSession());
    }

    @Override
    public boolean isSecure() {
        return true;
    }
}
