package ru.burmistrov.tm.command.user;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.burmistrov.tm.api.loader.ServiceLocator;
import ru.burmistrov.tm.command.AbstractCommand;
import ru.burmistrov.tm.endpoint.*;
import ru.burmistrov.tm.service.TerminalCommandService;

import javax.inject.Inject;

public final class UserLogInCommand extends AbstractCommand {

    @Inject
    private ServiceLocator serviceLocator;

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
        @NotNull final String login = serviceLocator.getTerminalCommandService().nextLine();
        System.out.println("Введите пароль:");
        @NotNull final String password = serviceLocator.getTerminalCommandService().nextLine();
        @Nullable final UserDto user = serviceLocator.getUserEndpoint().logIn(login, password);
        if (user == null) {
            System.out.println("Неверно введены данные");
        } else {
            serviceLocator.getSessionEndpoint().getNewSession(user.getId());
        }
    }

    @Override
    public boolean isSecure() {
        return false;
    }
}
