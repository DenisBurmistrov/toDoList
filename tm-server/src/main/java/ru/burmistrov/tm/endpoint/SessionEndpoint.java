package ru.burmistrov.tm.endpoint;

import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.burmistrov.tm.api.endpoint.ISessionEndpoint;
import ru.burmistrov.tm.api.loader.ServiceLocator;
import ru.burmistrov.tm.api.service.ISessionService;
import ru.burmistrov.tm.entity.Session;
import ru.burmistrov.tm.entity.User;

import javax.inject.Inject;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import java.io.IOException;

@NoArgsConstructor
@WebService
public class SessionEndpoint implements ISessionEndpoint {

    @Inject
    private ISessionService sessionService;

    @WebMethod
    @Nullable
    @Override
    public Session getNewSession(@WebParam(name = "userId") @NotNull final String userId) throws Exception {
        return sessionService.persist(userId);
    }

}
