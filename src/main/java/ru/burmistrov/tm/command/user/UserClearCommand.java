package ru.burmistrov.tm.command.user;

import ru.burmistrov.tm.api.loader.ServiceLocator;
import ru.burmistrov.tm.command.AbstractCommand;

public class UserClearCommand extends AbstractCommand {

    public UserClearCommand() {

    }

    @Override
    public String getName() {
        return "-removeAllUsers";
    }

    @Override
    public String getDescription() {
        return "Remove all users";
    }

    @Override
    public void execute() {
        getServiceLocator().getUserService().removeAll(getServiceLocator().getCurrentUser().getId());
        getServiceLocator().setCurrentUser(null);
    }

    @Override
    public boolean isSecure() {
        return true;
    }
}
