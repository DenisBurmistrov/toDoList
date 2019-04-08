package ru.burmistrov.tm.command.user;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.burmistrov.tm.api.loader.ServiceLocator;
import ru.burmistrov.tm.command.AbstractCommand;

import java.util.Objects;

@Component
public final class UserShowCurrentUser extends AbstractCommand {

    @Autowired
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
