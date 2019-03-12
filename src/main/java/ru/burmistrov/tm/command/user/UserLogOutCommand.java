package ru.burmistrov.tm.command.user;

import ru.burmistrov.tm.command.AbstractCommand;

public final class UserLogOutCommand extends AbstractCommand {

    public UserLogOutCommand() {
    }

    @Override
    public String getName() {
        return "-logOut";
    }

    @Override
    public String getDescription() {
        return "Exit from authorise user";
    }

    @Override
    public void execute() {
        if (getServiceLocator() != null) {
            getServiceLocator().setCurrentUser(null);
        }
    }

    @Override
    public boolean isSecure() {
        return true;
    }
}
