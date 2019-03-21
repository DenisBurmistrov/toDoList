package ru.burmistrov.tm.command.user;

import org.jetbrains.annotations.NotNull;
import ru.burmistrov.tm.command.AbstractCommand;

import java.util.Objects;

public final class UserShowCurrentUser extends AbstractCommand {

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
            System.out.println(Objects.requireNonNull(getServiceLocator().getSession()).getUserId());
    }

    @Override
    public boolean isSecure() {
        return true;
    }
}
