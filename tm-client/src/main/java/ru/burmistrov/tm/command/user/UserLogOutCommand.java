package ru.burmistrov.tm.command.user;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.burmistrov.tm.api.loader.ServiceLocator;
import ru.burmistrov.tm.command.AbstractCommand;
import ru.burmistrov.tm.endpoint.Session;
import ru.burmistrov.tm.service.TerminalCommandService;

@Component
public final class UserLogOutCommand extends AbstractCommand {

    @Autowired
    private ServiceLocator serviceLocator;

    @NotNull
    @Override
    public String getName() {
        return "-logOut";
    }

    @NotNull
    @Override
    public String getDescription() {
        return "Exit from authorise user";
    }

    @Override
    public void execute() {
        serviceLocator.setSession(null);
    }

    @Override
    public boolean isSecure() {
        return true;
    }
}
