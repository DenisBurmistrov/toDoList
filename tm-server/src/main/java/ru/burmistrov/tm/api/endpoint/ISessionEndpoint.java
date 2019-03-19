package ru.burmistrov.tm.api.endpoint;

import org.jetbrains.annotations.NotNull;
import ru.burmistrov.tm.entity.Session;
import ru.burmistrov.tm.entity.User;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService
public interface ISessionEndpoint {

    @WebMethod
    Session getNewSession(@WebParam @NotNull User user);
}
