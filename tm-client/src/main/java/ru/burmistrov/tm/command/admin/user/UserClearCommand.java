package ru.burmistrov.tm.command.admin.user;

import org.jetbrains.annotations.NotNull;
import ru.burmistrov.tm.command.AbstractCommand;

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
    public void execute() {
        getServiceLocator().getAdminEndpoint().removeAllUsers(Objects.requireNonNull(getServiceLocator().getSession().getUserId()));
    }

    @Override
    public boolean isSecure() {
        return true;
    }
}
