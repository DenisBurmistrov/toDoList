package ru.burmistrov.tm.command.admin.user;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.burmistrov.tm.api.loader.ServiceLocator;
import ru.burmistrov.tm.command.AbstractCommand;
import ru.burmistrov.tm.endpoint.AdminEndpoint;
import ru.burmistrov.tm.endpoint.Exception_Exception;
import ru.burmistrov.tm.endpoint.Role;
import ru.burmistrov.tm.endpoint.Session;
import ru.burmistrov.tm.service.TerminalCommandService;

@Component
public final class UserRegistrateCommand extends AbstractCommand {

    @Autowired
    private ServiceLocator serviceLocator;

    @Autowired
    private AdminEndpoint adminEndpoint;

    @NotNull
    @Override
    public String getName() {
        return "-createUser";
    }

    @NotNull
    @Override
    public String getDescription() {
        return "Create new user";
    }

    @Override
    public void execute() throws Exception_Exception {
        System.out.println("Введите логин:");
        @NotNull final String login = serviceLocator.getTerminalCommandService().nextLine();
        System.out.println("Введите пароль:");
        @NotNull final String password = serviceLocator.getTerminalCommandService().nextLine();
        System.out.println("Введите имя:");
        @NotNull final String firstName = serviceLocator.getTerminalCommandService().nextLine();
        System.out.println("Введите фамалию:");
        @NotNull final String lastName = serviceLocator.getTerminalCommandService().nextLine();
        System.out.println("Введите отчество:");
        @NotNull final String middleName = serviceLocator.getTerminalCommandService().nextLine();
        System.out.println("Введите почту:");
        @NotNull final String email = serviceLocator.getTerminalCommandService().nextLine();
        adminEndpoint.createUser
                (serviceLocator.getSession(), login, password, firstName, lastName, middleName,
                email, Role.COMMON_USER);
    }

    @Override
    public boolean isSecure() {
        return false;
    }
}
