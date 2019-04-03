package ru.burmistrov.tm.command.admin.user;

import org.jetbrains.annotations.NotNull;
import ru.burmistrov.tm.api.loader.ServiceLocator;
import ru.burmistrov.tm.command.AbstractCommand;
import ru.burmistrov.tm.endpoint.AdminEndpoint;
import ru.burmistrov.tm.endpoint.Exception_Exception;
import ru.burmistrov.tm.endpoint.Session;

import javax.inject.Inject;
import java.util.Objects;

public class UserRemoveCommand extends AbstractCommand {

    @Inject
    private ServiceLocator serviceLocator;

    @Inject
    private AdminEndpoint adminEndpoint;

    @NotNull
    @Override
    public String getName() {
        return "-removeUser";
    }

    @NotNull
    @Override
    public String getDescription() {
        return "Remove current user";
    }

    @Override
    public void execute() throws Exception_Exception {
        adminEndpoint.removeUserById
                (serviceLocator.getSession(), Objects.requireNonNull(serviceLocator.getSession()).getUserId());
        serviceLocator.setSession(null);
    }

    @Override
    public boolean isSecure() {
        return true;
    }
}
