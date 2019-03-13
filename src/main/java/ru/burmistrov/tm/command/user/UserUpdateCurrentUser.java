package ru.burmistrov.tm.command.user;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.burmistrov.tm.api.service.IUserService;
import ru.burmistrov.tm.command.AbstractCommand;

import java.util.Scanner;

public final class UserUpdateCurrentUser extends AbstractCommand {

    public UserUpdateCurrentUser() {
    }

    @NotNull
    @Override
    public String getName() {
        return "-updateCurrentUser";
    }

    @NotNull
    @Override
    public String getDescription() {
        return "Update all variables of current user";
    }

    @Override
    public void execute() {
        @Nullable final IUserService userService = getServiceLocator().getUserService();
        @Nullable final Scanner scanner = getServiceLocator().getScanner();
        System.out.println("Введите новое имя:");
        @NotNull final String firstName = scanner.nextLine();
        System.out.println("Введите новую фамилию:");
        @NotNull final String lastName = scanner.nextLine();
        System.out.println("Введите новое отчество:");
        @NotNull final String middleName = scanner.nextLine();
        System.out.println("Введите новую почту:");
        @NotNull final String email = scanner.nextLine();
        userService.merge(getServiceLocator().getCurrentUser().getId(), firstName, middleName, lastName, email,
                getServiceLocator().getCurrentUser().getRole(), getServiceLocator().getCurrentUser().getLogin());
    }

    @Override
    public boolean isSecure() {
        return false;
    }
}
