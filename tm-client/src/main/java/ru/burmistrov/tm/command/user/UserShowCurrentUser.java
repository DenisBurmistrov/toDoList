package ru.burmistrov.tm.command.user;

import org.jetbrains.annotations.NotNull;
import ru.burmistrov.tm.api.loader.ServiceLocator;
import ru.burmistrov.tm.command.AbstractCommand;
import ru.burmistrov.tm.endpoint.Session;
import ru.burmistrov.tm.service.TerminalCommandService;

import javax.inject.Inject;
import java.util.Objects;

public final class UserShowCurrentUser extends AbstractCommand {

    @Inject
    private ServiceLocator serviceLocator;

    @NotNull
    @Override
    public String getName() {
        return "-showCurrentUser";
    }

    @NotNull
    @Override
    public String getDescription() {
        return "Print info about current user";
    }

    @Override
    public void execute() {
            System.out.println(Objects.requireNonNull(serviceLocator.getSession()).getUserId());
    }

    @Override
    public boolean isSecure() {
        return true;
    }
}
