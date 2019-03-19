package ru.burmistrov.tm.command.user;

import org.jetbrains.annotations.NotNull;
import ru.burmistrov.tm.command.AbstractCommand;

public final class UserShowCurrentUser extends AbstractCommand {

    public UserShowCurrentUser() {
    }

    @NotNull
    @Override
    public String getName() {
        return "-showCurrentUser";
    }

    @NotNull
    @Override
    public String getDescription() {
        return "Print info about current user";
    }

    @Override
    public void execute() {
            System.out.println(getServiceLocator().getSession().getUserId());
    }

    @Override
    public boolean isSecure() {
        return true;
    }
}
