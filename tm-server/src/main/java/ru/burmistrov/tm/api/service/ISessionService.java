package ru.burmistrov.tm.api.service;

import org.jetbrains.annotations.NotNull;
import ru.burmistrov.tm.entity.Role;
import ru.burmistrov.tm.entity.Session;
import ru.burmistrov.tm.entity.User;

import java.io.IOException;

public interface ISessionService {

    Session persist(@NotNull String userId) throws IOException;

    boolean validate(Session session) throws CloneNotSupportedException;

    boolean validateAdmin(Session session) throws CloneNotSupportedException;
}
