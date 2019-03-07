package ru.burmistrov.tm.command.user;

import ru.burmistrov.tm.api.loader.ServiceLocator;
import ru.burmistrov.tm.api.service.IUserService;
import ru.burmistrov.tm.command.AbstractCommand;
import ru.burmistrov.tm.entity.Role;

import java.util.Scanner;

public final class UserRegistrateCommand extends AbstractCommand {

    private final IUserService userService = getServiceLocator().getUserService();

    private final Scanner scanner = getServiceLocator().getScanner();

    public UserRegistrateCommand(final ServiceLocator serviceLocator) {
        super(serviceLocator);
    }

    @Override
    public String getName() {
        return "-persist";
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
        System.out.println(userService.persist(login, password, firstName, lastName, middleName, email, Role.COMMON_USER) + " зарегистрирован");
    }

    @Override
    public boolean isSecure() {
        return false;
    }
}
