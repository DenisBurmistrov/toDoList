package ru.burmistrov.tm.command.admin.user;

import org.jetbrains.annotations.NotNull;
import ru.burmistrov.tm.command.AbstractCommand;
import ru.burmistrov.tm.endpoint.CloneNotSupportedException_Exception;

import java.util.Objects;

public final class UserUpdatePasswordCommand extends AbstractCommand {

    public UserUpdatePasswordCommand() {

    }

    @NotNull
    @Override
    public String getName() {
        return "-updatePassword";
    }

    @NotNull
    @Override
    public String getDescription() {
        return "Update password by login";
    }

    @Override
    public void execute() throws CloneNotSupportedException_Exception {
        if (isSecure()) {
            System.out.println("Введите логин:");
            @NotNull final String login = getServiceLocator().getTerminalCommandService().nextLine();
            System.out.println("Введите новый пароль");
            @NotNull final String newPassword = getServiceLocator().getTerminalCommandService().nextLine();
            getServiceLocator().getAdminEndpoint().updatePasswordById(getServiceLocator().getSession(), Objects.requireNonNull(getServiceLocator().getSession().getUserId()),
                    login, newPassword);
        }
    }

    @Override
    public boolean isSecure() {
        return true;
    }
}
