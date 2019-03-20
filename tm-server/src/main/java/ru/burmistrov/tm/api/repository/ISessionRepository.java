package ru.burmistrov.tm.api.repository;


import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.burmistrov.tm.entity.Role;
import ru.burmistrov.tm.entity.Session;

public interface ISessionRepository {

    @Nullable
    Session persist(@NotNull Session abstractEntity);

    boolean validate(Session session) throws CloneNotSupportedException;

}
