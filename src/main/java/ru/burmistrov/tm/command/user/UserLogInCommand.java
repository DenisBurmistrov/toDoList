package ru.burmistrov.tm.command.user;

import ru.burmistrov.tm.api.loader.ServiceLocator;
import ru.burmistrov.tm.api.service.IUserService;
import ru.burmistrov.tm.command.AbstractCommand;
import ru.burmistrov.tm.entity.User;

import java.util.Scanner;

public final class UserLogInCommand extends AbstractCommand {

    private final Scanner scanner = getServiceLocator().getScanner();

    private final IUserService userService = getServiceLocator().getUserService();

    public UserLogInCommand(final ServiceLocator serviceLocator) {
        super(serviceLocator);
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
        System.out.println("Введите логин:");
        String login = scanner.nextLine();
        System.out.println("Введите пароль:");
        String password = scanner.nextLine();
        User user = userService.logIn(login, password);
        if(user == null) {
            System.out.println("Неверно введены данные");
        }
        else {
            getServiceLocator().setCurrentUser(user);
        }


    }

    @Override
    public boolean isSecure() {
        return false;
    }
}
