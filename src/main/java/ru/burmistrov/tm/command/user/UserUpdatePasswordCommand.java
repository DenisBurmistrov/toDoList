package ru.burmistrov.tm.command.user;

import org.jetbrains.annotations.Nullable;
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
            @Nullable final IUserService userService = getServiceLocator().getUserService();
            @Nullable final Scanner scanner = getServiceLocator().getScanner();
            if (isSecure()) {
                System.out.println("Введите логин:");
                @Nullable final String login = scanner.nextLine();
                System.out.println("Введите новый пароль");
                @Nullable final String newPassword = scanner.nextLine();
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
