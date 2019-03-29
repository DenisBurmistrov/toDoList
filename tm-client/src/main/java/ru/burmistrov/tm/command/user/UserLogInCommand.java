package ru.burmistrov.tm.command.user;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.burmistrov.tm.command.AbstractCommand;
import ru.burmistrov.tm.endpoint.Exception_Exception;
import ru.burmistrov.tm.endpoint.UserDto;

public final class UserLogInCommand extends AbstractCommand {

    @NotNull
    @Override
    public String getName() {
        return "-logIn";
    }

    @NotNull
    @Override
    public String getDescription() {
        return "Log in program by login and password";
    }

    @Override
    public void execute() throws Exception_Exception {
        System.out.println("Введите логин:");
        @NotNull final String login = getServiceLocator().getTerminalCommandService().nextLine();
        System.out.println("Введите пароль:");
        @NotNull final String password = getServiceLocator().getTerminalCommandService().nextLine();
        @Nullable final UserDto user = getServiceLocator().getUserEndpoint().logIn(login, password);
        if (user == null) {
            System.out.println("Неверно введены данные");
        } else {
            getServiceLocator().setSession(getServiceLocator().getSessionEndpoint().getNewSession(user.getId()));
        }
    }

    @Override
    public boolean isSecure() {
        return false;
    }
}
