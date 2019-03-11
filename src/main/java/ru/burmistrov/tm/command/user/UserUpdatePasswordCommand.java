package ru.burmistrov.tm.command.user;

import ru.burmistrov.tm.api.loader.ServiceLocator;
import ru.burmistrov.tm.api.service.IUserService;
import ru.burmistrov.tm.command.AbstractCommand;

import java.util.Scanner;

public final class UserUpdatePasswordCommand extends AbstractCommand {

    private final IUserService userService = getServiceLocator().getUserService();

    private final Scanner scanner = getServiceLocator().getScanner();

    public UserUpdatePasswordCommand() {

    }

    @Override
    public String getName() {
        return "-updatePassword";
    }

    @Override
    public String getDescription() {
        return "Update password by login";
    }

    @Override
    public void execute() {
        if(isSecure()){
            System.out.println("Введите логин:");
            String login = scanner.nextLine();
            System.out.println("Введите новый пароль");
            String newPassword = scanner.nextLine();
            userService.updatePassword(getServiceLocator().getCurrentUser().getId(), login, newPassword);
        }
    }

    @Override
    public boolean isSecure() {
        return true;
    }
}
