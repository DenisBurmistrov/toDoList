package ru.burmistrov.tm.command.user;

import ru.burmistrov.tm.Bootstrap;
import ru.burmistrov.tm.command.AbstractCommand;
import ru.burmistrov.tm.service.UserService;

public class UserShowCurrentUser extends AbstractCommand {

    private final UserService userService = getBootstrap().getUserService();

    public UserShowCurrentUser(Bootstrap bootstrap) {
        super(bootstrap);
    }

    @Override
    public String getName() {
        return "-showCurrentUser";
    }

    @Override
    public String getDescription() {
        return "Print info about current user";
    }

    @Override
    public void execute() {
        System.out.println(getBootstrap().getCurrentUser().toString());
    }

    @Override
    public boolean isSecure() {
        return true;
    }
}
