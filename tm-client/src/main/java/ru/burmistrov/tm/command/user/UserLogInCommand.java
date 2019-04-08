package ru.burmistrov.tm.command.user;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.burmistrov.tm.api.loader.ServiceLocator;
import ru.burmistrov.tm.command.AbstractCommand;
import ru.burmistrov.tm.endpoint.*;
import ru.burmistrov.tm.service.TerminalCommandService;

@Component
public final class UserLogInCommand extends AbstractCommand {

    @Autowired
    private ServiceLocator serviceLocator;

    @Autowired
    private UserEndpoint userEndpoint;

    @Autowired
    private SessionEndpoint sessionEndpoint;

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
        @Nullable final UserDto user = userEndpoint.logIn(login, password);
        if (user == null) {
            System.out.println("Неверно введены данные");
        } else {
            Session session = sessionEndpoint.getNewSession(user.getId());
            serviceLocator.setSession(session);
        }
    }

    @Override
    public boolean isSecure() {
        return false;
    }
}
