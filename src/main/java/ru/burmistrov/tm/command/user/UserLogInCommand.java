package ru.burmistrov.tm.command.user;

import ru.burmistrov.tm.api.service.IUserService;
import ru.burmistrov.tm.command.AbstractCommand;
import ru.burmistrov.tm.entity.User;

import java.util.Scanner;

public final class UserLogInCommand extends AbstractCommand {



    public UserLogInCommand() {

    }

    @Override
    public String getName() {
        return "-logIn";
    }

    @Override
    public String getDescription() {
        return "Log in program by login and password";
    }

    @Override
    public void execute() {
        if(getServiceLocator() != null) {
            Scanner scanner = getServiceLocator().getScanner();
            IUserService userService = getServiceLocator().getUserService();
            System.out.println("Введите логин:");
            String login = scanner.nextLine();
            System.out.println("Введите пароль:");
            String password = scanner.nextLine();
            User user = (User) userService.logIn(login, password);
            if (user == null) {
                System.out.println("Неверно введены данные");
            } else {
                getServiceLocator().setCurrentUser(user);
            }
        }
    }

    @Override
    public boolean isSecure() {
        return false;
    }
}
