package ru.burmistrov.tm.command.user;

import ru.burmistrov.tm.Bootstrap;
import ru.burmistrov.tm.command.AbstractCommand;
import ru.burmistrov.tm.service.UserService;

import java.util.Scanner;

public class UserUpdateCurrentUser extends AbstractCommand {

    private final UserService userService = getBootstrap().getUserService();

    private final Scanner scanner = getBootstrap().getScanner();


    public UserUpdateCurrentUser(Bootstrap bootstrap) {
        super(bootstrap);
    }

    @Override
    public String getName() {
        return "-updateCurrentUser";
    }

    @Override
    public String getDescription() {
        return "Update all variables of current user";
    }

    @Override
    public void execute() {
        System.out.println("Введите имя:");
        String firstName = scanner.nextLine();
        System.out.println("Введите фамилию:");
        String lastName = scanner.nextLine();
        System.out.println("Введите отчество:");
        String middleName = scanner.nextLine();
        System.out.println("Введите почту:");
        String email = scanner.nextLine();
        userService.updateCurrentUser(getBootstrap().getCurrentUser(), firstName, middleName, lastName, email);
    }

    @Override
    public boolean isSecure() {
        return false;
    }
}
