package ru.burmistrov.tm.command.user;

import ru.burmistrov.tm.api.loader.ServiceLocator;
import ru.burmistrov.tm.api.service.IUserService;
import ru.burmistrov.tm.command.AbstractCommand;

import java.util.Scanner;

public final class UserUpdateCurrentUser extends AbstractCommand {

    private final IUserService userService = getServiceLocator().getUserService();

    private final Scanner scanner = getServiceLocator().getScanner();


    public UserUpdateCurrentUser(final ServiceLocator serviceLocator) {
        super(serviceLocator);
    }

    @Override
    public String getName() {
        return "-merge";
    }

    @Override
    public String getDescription() {
        return "Update all variables of current user";
    }

    @Override
    public void execute() {
        System.out.println("Введите логин");
        String login = scanner.nextLine();
        System.out.println("Введите имя:");
        String firstName = scanner.nextLine();
        System.out.println("Введите фамилию:");
        String lastName = scanner.nextLine();
        System.out.println("Введите отчество:");
        String middleName = scanner.nextLine();
        System.out.println("Введите почту:");
        String email = scanner.nextLine();
        userService.merge(getServiceLocator().getCurrentUser().getId(), firstName, middleName, lastName, email, getServiceLocator().getCurrentUser().getRole(),login);
    }

    @Override
    public boolean isSecure() {
        return false;
    }
}
