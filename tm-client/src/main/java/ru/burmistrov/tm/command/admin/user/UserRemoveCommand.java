package ru.burmistrov.tm.command.admin.user;

import org.jetbrains.annotations.NotNull;
import ru.burmistrov.tm.command.AbstractCommand;
import ru.burmistrov.tm.endpoint.Exception_Exception;

import java.util.Objects;

public class UserRemoveCommand extends AbstractCommand {

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
        getServiceLocator().getAdminEndpoint()
                .removeUserById(getServiceLocator().getSession(), Objects.requireNonNull(Objects.requireNonNull(getServiceLocator().getSession()).getUserId()));
        getServiceLocator().setSession(null);
    }

    @Override
    public boolean isSecure() {
        return true;
    }
}
