package ru.burmistrov.tm.command.admin.user;

import org.jetbrains.annotations.NotNull;
import ru.burmistrov.tm.api.loader.ServiceLocator;
import ru.burmistrov.tm.command.AbstractCommand;
import ru.burmistrov.tm.endpoint.AdminEndpoint;
import ru.burmistrov.tm.endpoint.Exception_Exception;
import ru.burmistrov.tm.endpoint.Role;
import ru.burmistrov.tm.endpoint.Session;
import ru.burmistrov.tm.service.TerminalCommandService;

import javax.inject.Inject;
import java.util.Objects;

public final class UserUpdateUserByLogin extends AbstractCommand {

    @Inject
    private ServiceLocator serviceLocator;

    @Inject
    private AdminEndpoint adminEndpoint;

    @NotNull
    @Override
    public String getName() {
        return "-updateUserByLogin";
    }

    @NotNull
    @Override
    public String getDescription() {
        return "Update all variables of current user";
    }

    @Override
    public void execute() throws Exception_Exception {
        System.out.println("Введите логин:");
        @NotNull final String login = serviceLocator.getTerminalCommandService().nextLine();
        System.out.println("Введите новое имя:");
        @NotNull final String firstName = serviceLocator.getTerminalCommandService().nextLine();
        System.out.println("Введите новую фамилию:");
        @NotNull final String lastName = serviceLocator.getTerminalCommandService().nextLine();
        System.out.println("Введите новое отчество:");
        @NotNull final String middleName = serviceLocator.getTerminalCommandService().nextLine();
        System.out.println("Введите новую почту:");
        @NotNull final String email = serviceLocator.getTerminalCommandService().nextLine();
        adminEndpoint.updateUserByLogin(serviceLocator.getSession(), login,
                firstName, middleName, lastName, email, Role.COMMON_USER);
    }

    @Override
    public boolean isSecure() {
        return false;
    }
}
