package ru.burmistrov.tm.command.admin.user;

import org.jetbrains.annotations.NotNull;
import ru.burmistrov.tm.api.loader.ServiceLocator;
import ru.burmistrov.tm.command.AbstractCommand;
import ru.burmistrov.tm.endpoint.AdminEndpoint;
import ru.burmistrov.tm.endpoint.Exception_Exception;
import ru.burmistrov.tm.endpoint.Session;
import ru.burmistrov.tm.service.TerminalCommandService;

import javax.inject.Inject;
import java.util.Objects;

public final class UserUpdatePasswordCommand extends AbstractCommand {

    @Inject
    private ServiceLocator serviceLocator;

    @Inject
    private AdminEndpoint adminEndpoint;

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
    public void execute() throws Exception_Exception {
        if (isSecure()) {
            System.out.println("Введите логин:");
            @NotNull final String login = serviceLocator.getTerminalCommandService().nextLine();
            System.out.println("Введите новый пароль:");
            @NotNull final String newPassword = serviceLocator.getTerminalCommandService().nextLine();
            adminEndpoint.updatePasswordById(serviceLocator.getSession(),
                    Objects.requireNonNull(serviceLocator.getSession()).getUserId(), login, newPassword);
        }
    }

    @Override
    public boolean isSecure() {
        return true;
    }
}
