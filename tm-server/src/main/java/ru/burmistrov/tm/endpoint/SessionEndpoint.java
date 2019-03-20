package ru.burmistrov.tm.endpoint;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.burmistrov.tm.api.endpoint.ISessionEndpoint;
import ru.burmistrov.tm.api.loader.ServiceLocator;
import ru.burmistrov.tm.entity.Session;
import ru.burmistrov.tm.entity.User;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public class SessionEndpoint implements ISessionEndpoint {

    private ServiceLocator serviceLocator;

    public SessionEndpoint() {
    }

    public SessionEndpoint(ServiceLocator serviceLocator) {
        this.serviceLocator = serviceLocator;
    }

    @WebMethod
    @Override
    public Session getNewSession(@NotNull String userId) {

        return serviceLocator.getSessionService().persist(userId);
    }

    @WebMethod
    @Nullable
    public User logIn(@NotNull String login, @NotNull String password) {
        return serviceLocator.getUserService().logIn(login, password);
    }
}
