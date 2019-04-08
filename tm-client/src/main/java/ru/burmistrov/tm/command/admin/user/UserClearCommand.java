package ru.burmistrov.tm.command.admin.user;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.burmistrov.tm.api.loader.ServiceLocator;
import ru.burmistrov.tm.command.AbstractCommand;
import ru.burmistrov.tm.endpoint.AdminEndpoint;
import ru.burmistrov.tm.endpoint.Exception_Exception;

import java.util.Objects;

@Component
public class UserClearCommand extends AbstractCommand {

    @Autowired
    private ServiceLocator serviceLocator;

    @Autowired
    private AdminEndpoint adminEndpoint;

    @NotNull
    @Override
    public String getName() {
        return "-removeAllUsers";
    }

    @NotNull
    @Override
    public String getDescription() {
        return "Remove all users";
    }

    @Override
    public void execute() throws Exception_Exception {
        adminEndpoint.removeAllUsers
                (serviceLocator.getSession(), Objects.requireNonNull(serviceLocator.getSession()).getUserId());
    }

    @Override
    public boolean isSecure() {
        return true;
    }
}
