package ru.burmistrov.tm.api.repository;

import org.jetbrains.annotations.NotNull;
import ru.burmistrov.tm.entity.Session;
import ru.burmistrov.tm.entity.User;

public interface ISessionRepository {

    Session persist(@NotNull User user);
}
