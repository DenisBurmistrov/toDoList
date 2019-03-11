package ru.burmistrov.tm.command.user;

import ru.burmistrov.tm.api.loader.ServiceLocator;
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
        IUserService userService = getServiceLocator().getUserService();

        Scanner scanner = getServiceLocator().getScanner();
        System.out.println("Введите новое имя:");
        String firstName = scanner.nextLine();
        System.out.println("Введите новую фамилию:");
        String lastName = scanner.nextLine();
        System.out.println("Введите новое отчество:");
        String middleName = scanner.nextLine();
        System.out.println("Введите новую почту:");
        String email = scanner.nextLine();
        userService.merge(getServiceLocator().getCurrentUser().getId(), firstName, middleName, lastName, email, getServiceLocator().getCurrentUser().getRole(),getServiceLocator().getCurrentUser().getLogin());
    }

    @Override
    public boolean isSecure() {
        return false;
    }
}
