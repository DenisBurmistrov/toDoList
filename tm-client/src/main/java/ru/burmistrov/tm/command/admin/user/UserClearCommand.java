package ru.burmistrov.tm.command.admin.user;

import org.jetbrains.annotations.NotNull;
import ru.burmistrov.tm.api.loader.ServiceLocator;
import ru.burmistrov.tm.command.AbstractCommand;
import ru.burmistrov.tm.endpoint.AdminEndpoint;
import ru.burmistrov.tm.endpoint.Exception_Exception;
import ru.burmistrov.tm.endpoint.Session;

import javax.inject.Inject;
import java.util.Objects;

public class UserClearCommand extends AbstractCommand {

    @Inject
    private ServiceLocator serviceLocator;

    @NotNull
    @Override
    public String getName() {
        return "-removeAllUsers";
    }

    @NotNull
    @Override
    public String getDescription() {
        return "Remove all users";
    }

    @Override
    public void execute() throws Exception_Exception {
        serviceLocator.getAdminEndpoint().removeAllUsers(serviceLocator.getSession(),
                Objects.requireNonNull(serviceLocator.getSession()).getUserId());
    }

    @Override
    public boolean isSecure() {
        return true;
    }
}
