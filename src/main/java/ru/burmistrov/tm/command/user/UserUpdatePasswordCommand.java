package ru.burmistrov.tm.command.user;

import ru.burmistrov.tm.Bootstrap;
import ru.burmistrov.tm.command.AbstractCommand;
import ru.burmistrov.tm.service.UserService;

import java.util.Scanner;

public class UserUpdatePasswordCommand extends AbstractCommand {

    private final UserService userService = getBootstrap().getUserService();

    private final Scanner scanner = getBootstrap().getScanner();

    public UserUpdatePasswordCommand(Bootstrap bootstrap) {
        super(bootstrap);
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
            userService.updatePassword(getBootstrap().getCurrentUser(), login, newPassword);
        }
    }

    @Override
    public boolean isSecure() {
        return true;
    }
}
