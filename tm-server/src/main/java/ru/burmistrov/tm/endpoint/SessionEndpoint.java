package ru.burmistrov.tm.endpoint;

import org.jetbrains.annotations.NotNull;
import ru.burmistrov.tm.api.endpoint.ISessionEndpoint;
import ru.burmistrov.tm.entity.Session;
import ru.burmistrov.tm.entity.User;

public class SessionEndpoint implements ISessionEndpoint {


    @Override
    public Session getNewSession(@NotNull User user) {
        return null;
    }
}
