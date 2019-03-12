package ru.burmistrov.tm.command.user;

import ru.burmistrov.tm.api.service.IUserService;
import ru.burmistrov.tm.command.AbstractCommand;
import ru.burmistrov.tm.entity.Role;

import java.util.Scanner;

public final class UserRegistrateCommand extends AbstractCommand {

    public UserRegistrateCommand() {
    }

    @Override
    public String getName() {
        return "-createNewUser";
    }

    @Override
    public String getDescription() {
        return "Create new user";
    }

    @Override
    public void execute() {
        if (getServiceLocator() != null) {
            IUserService userService = getServiceLocator().getUserService();
            Scanner scanner = getServiceLocator().getScanner();
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
            if (userService != null) {
                System.out.println(userService.persist(login, password, firstName, lastName, middleName, email, Role.COMMON_USER) + " зарегистрирован");
            }
        }
    }

    @Override
    public boolean isSecure() {
        return false;
    }
}
