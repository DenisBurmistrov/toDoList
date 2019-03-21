package ru.burmistrov.tm.command.admin.user;

import org.jetbrains.annotations.NotNull;
import ru.burmistrov.tm.command.AbstractCommand;
import ru.burmistrov.tm.endpoint.Exception_Exception;
import ru.burmistrov.tm.endpoint.Role;

public final class UserRegistrateCommand extends AbstractCommand {

    @NotNull
    @Override
    public String getName() {
        return "-createNewUser";
    }

    @NotNull
    @Override
    public String getDescription() {
        return "Create new user";
    }

    @Override
    public void execute() throws Exception_Exception {
        System.out.println("Введите логин:");
        @NotNull final String login = getServiceLocator().getTerminalCommandService().nextLine();
        System.out.println("Введите пароль:");
        @NotNull final String password = getServiceLocator().getTerminalCommandService().nextLine();
        System.out.println("Введите имя:");
        @NotNull final String firstName = getServiceLocator().getTerminalCommandService().nextLine();
        System.out.println("Введите фамалию:");
        @NotNull final String lastName = getServiceLocator().getTerminalCommandService().nextLine();
        System.out.println("Введите отчество:");
        @NotNull final String middleName = getServiceLocator().getTerminalCommandService().nextLine();
        System.out.println("Введите почту:");
        @NotNull final String email = getServiceLocator().getTerminalCommandService().nextLine();
        System.out.println(getServiceLocator().getAdminEndpoint().createUser(getServiceLocator().getSession(), login, password, firstName, lastName, middleName,
                email, Role.COMMON_USER));
    }

    @Override
    public boolean isSecure() {
        return false;
    }
}
