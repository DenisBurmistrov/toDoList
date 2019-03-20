package ru.burmistrov.tm.command.admin.deserialize;

import org.jetbrains.annotations.NotNull;
import ru.burmistrov.tm.command.AbstractCommand;
import ru.burmistrov.tm.endpoint.ClassNotFoundException_Exception;
import ru.burmistrov.tm.endpoint.IOException_Exception;

public class DeserializeByDefaultCommand extends AbstractCommand {

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
    public void execute() throws ClassNotFoundException_Exception, IOException_Exception {
        getServiceLocator().getAdminEndpoint().loadDataByDefault(getServiceLocator().getSession());

    }

    @Override
    public boolean isSecure() {
        return true;
    }


}
