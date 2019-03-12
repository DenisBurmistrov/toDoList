package ru.burmistrov.tm.command.user;

import ru.burmistrov.tm.api.service.IUserService;
import ru.burmistrov.tm.command.AbstractCommand;

import java.util.Scanner;

public final class UserUpdatePasswordCommand extends AbstractCommand {

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
        if(getServiceLocator() != null) {
            IUserService userService = getServiceLocator().getUserService();
            Scanner scanner = getServiceLocator().getScanner();
            if (isSecure()) {
                System.out.println("Введите логин:");
                String login = scanner.nextLine();
                System.out.println("Введите новый пароль");
                String newPassword = scanner.nextLine();
                if (userService != null) {
                    userService.updatePassword(getServiceLocator().getCurrentUser().getId(), login, newPassword);
                }
            }
        }
    }

    @Override
    public boolean isSecure() {
        return true;
    }
}
