package ru.burmistrov.tm.command.admin.user;

import org.jetbrains.annotations.NotNull;
import ru.burmistrov.tm.command.AbstractCommand;
import ru.burmistrov.tm.endpoint.CloneNotSupportedException_Exception;

import java.util.Objects;

public class UserRemoveCommand extends AbstractCommand {

    public UserRemoveCommand() {
    }

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
    public void execute() throws CloneNotSupportedException_Exception {
        getServiceLocator().getAdminEndpoint().removeUserById(getServiceLocator().getSession(), Objects.requireNonNull(getServiceLocator().getSession().getUserId()));
        getServiceLocator().setSession(null);
    }

    @Override
    public boolean isSecure() {
        return true;
    }
}
