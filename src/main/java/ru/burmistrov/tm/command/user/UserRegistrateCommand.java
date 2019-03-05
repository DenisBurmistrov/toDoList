package ru.burmistrov.tm.command.user;

import ru.burmistrov.tm.Bootstrap;
import ru.burmistrov.tm.command.AbstractCommand;
import ru.burmistrov.tm.service.UserService;

import java.util.Scanner;

public class UserRegistrateCommand extends AbstractCommand {

    private final UserService userService = getBootstrap().getUserService();

    private final Scanner scanner = getBootstrap().getScanner();

    public UserRegistrateCommand(Bootstrap bootstrap) {
        super(bootstrap);
    }

    @Override
    public String getName() {
        return "-registrate";
    }

    @Override
    public String getDescription() {
        return "Create new user";
    }

    @Override
    public void execute() {
        System.out.println("Введите логин:");
        String login = scanner.nextLine();
        System.out.println("Введите пароль:");
        String password = scanner.nextLine();
        System.out.println("Введите имя:");
        String firstName = scanner.nextLine();
        System.out.println("Введите фамалию:");
        String lastName = scanner.nextLine();
        System.out.println("Введите отчество:");
        String middleName = scanner.nextLine();
        System.out.println("Введите почту:");
        String email = scanner.nextLine();
        System.out.println(userService.registrate(login, password, firstName, lastName, middleName, email));
    }

    @Override
    public boolean isSecure() {
        return false;
    }
}
