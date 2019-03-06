package ru.burmistrov.tm.command.user;

import ru.burmistrov.tm.Bootstrap;
import ru.burmistrov.tm.command.AbstractCommand;
import ru.burmistrov.tm.service.UserService;

import java.util.Scanner;

public class UserLogInCommand extends AbstractCommand {

    private final Scanner scanner = getBootstrap().getScanner();

    private final UserService userService = getBootstrap().getUserService();

    public UserLogInCommand(Bootstrap bootstrap) {
        super(bootstrap);
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
        System.out.println(userService.logIn(login, password));

    }

    @Override
    public boolean isSecure() {
        return false;
    }
}