package ru.burmistrov.tm.command.user;

import org.jetbrains.annotations.Nullable;
import ru.burmistrov.tm.api.service.IUserService;
import ru.burmistrov.tm.command.AbstractCommand;

import java.util.Scanner;

public final class UserUpdateCurrentUser extends AbstractCommand {

    public UserUpdateCurrentUser() {
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
        if (getServiceLocator() != null) {
            @Nullable final IUserService userService = getServiceLocator().getUserService();
            @Nullable final Scanner scanner = getServiceLocator().getScanner();
            System.out.println("Введите новое имя:");
            @Nullable final String firstName = scanner.nextLine();
            System.out.println("Введите новую фамилию:");
            @Nullable final  String lastName = scanner.nextLine();
            System.out.println("Введите новое отчество:");
            @Nullable final  String middleName = scanner.nextLine();
            System.out.println("Введите новую почту:");
            @Nullable final String email = scanner.nextLine();
            if (userService != null) {
                userService.merge(getServiceLocator().getCurrentUser().getId(), firstName, middleName, lastName, email, getServiceLocator().getCurrentUser().getRole(), getServiceLocator().getCurrentUser().getLogin());
            }
        }
    }

    @Override
    public boolean isSecure() {
        return false;
    }
}
