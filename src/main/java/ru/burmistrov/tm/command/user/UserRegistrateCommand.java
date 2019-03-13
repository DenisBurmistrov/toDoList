package ru.burmistrov.tm.command.user;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.burmistrov.tm.api.service.IUserService;
import ru.burmistrov.tm.command.AbstractCommand;
import ru.burmistrov.tm.entity.Role;

import java.util.Scanner;

public final class UserRegistrateCommand extends AbstractCommand {

    public UserRegistrateCommand() {
    }

    @NotNull
    @Override
    public String getName() {
        return "-createNewUser";
    }

    @NotNull
    @Override
    public String getDescription() {
        return "Create new user";
    }

    @Override
    public void execute() {
        @Nullable final IUserService userService = getServiceLocator().getUserService();
        @Nullable final Scanner scanner = getServiceLocator().getScanner();
        System.out.println("Введите логин:");
        @NotNull final String login = scanner.nextLine();
        System.out.println("Введите пароль:");
        @NotNull final String password = scanner.nextLine();
        System.out.println("Введите имя:");
        @NotNull final String firstName = scanner.nextLine();
        System.out.println("Введите фамалию:");
        @NotNull final String lastName = scanner.nextLine();
        System.out.println("Введите отчество:");
        @NotNull final String middleName = scanner.nextLine();
        System.out.println("Введите почту:");
        @NotNull final String email = scanner.nextLine();
        System.out.println(userService.persist(login, password, firstName, lastName, middleName, email, Role.COMMON_USER) + " зарегистрирован");
    }

    @Override
    public boolean isSecure() {
        return false;
    }
}
