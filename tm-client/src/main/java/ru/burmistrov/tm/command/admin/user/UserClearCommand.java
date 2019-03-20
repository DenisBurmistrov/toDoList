package ru.burmistrov.tm.command.admin.user;

import org.jetbrains.annotations.NotNull;
import ru.burmistrov.tm.command.AbstractCommand;
import ru.burmistrov.tm.endpoint.CloneNotSupportedException_Exception;

import java.util.Objects;

public class UserClearCommand extends AbstractCommand {

    public UserClearCommand() {

    }

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
    public void execute() throws CloneNotSupportedException_Exception {
        getServiceLocator().getAdminEndpoint().removeAllUsers(getServiceLocator().getSession(), Objects.requireNonNull(getServiceLocator().getSession().getUserId()));
    }

    @Override
    public boolean isSecure() {
        return true;
    }
}
