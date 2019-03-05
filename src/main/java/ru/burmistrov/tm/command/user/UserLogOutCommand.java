package ru.burmistrov.tm.command.user;

import ru.burmistrov.tm.Bootstrap;
import ru.burmistrov.tm.command.AbstractCommand;
import ru.burmistrov.tm.service.UserService;

public class UserLogOutCommand extends AbstractCommand {

    private final UserService userService = getBootstrap().getUserService();

    public UserLogOutCommand(Bootstrap bootstrap) {
        super(bootstrap);
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
        if(isSecure()){
            System.out.println(userService.logOut());
        }
        else System.out.println("Для использования этой команды нужно авторизоваться");
    }

    @Override
    public boolean isSecure() {
        return true;
    }
}
