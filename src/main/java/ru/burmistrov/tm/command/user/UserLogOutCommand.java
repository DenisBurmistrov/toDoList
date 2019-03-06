package ru.burmistrov.tm.command.user;

import ru.burmistrov.tm.api.loader.ServiceLocator;
import ru.burmistrov.tm.command.AbstractCommand;

public final class UserLogOutCommand extends AbstractCommand {

    public UserLogOutCommand(final ServiceLocator serviceLocator) {
        super(serviceLocator);
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
        getServiceLocator().setCurrentUser(null);
    }

    @Override
    public boolean isSecure() {
        return true;
    }
}
