package ru.burmistrov.tm.command.admin.serialize;

import org.jetbrains.annotations.NotNull;
import ru.burmistrov.tm.command.AbstractCommand;
import ru.burmistrov.tm.endpoint.Exception_Exception;

public class SerializeByDefaultCommand extends AbstractCommand {

    @NotNull
    @Override
    public String getName() {
        return "-serializeByDefault";
    }

    @NotNull
    @Override
    public String getDescription() {
        return "Serialize all projects and tasks";
    }

    @Override
    public void execute() throws Exception_Exception {
        getServiceLocator().getAdminEndpoint().saveDataByDefault(getServiceLocator().getSession());
    }

    @Override
    public boolean isSecure() {
        return true;
    }
}
