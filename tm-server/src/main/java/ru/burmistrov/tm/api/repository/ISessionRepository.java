package ru.burmistrov.tm.api.repository;


import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.burmistrov.tm.entity.Session;
import ru.burmistrov.tm.exception.ValidateAccessException;

import java.io.IOException;

public interface ISessionRepository {

    @Nullable
    Session persist(@NotNull Session abstractEntity) throws IOException;

    boolean validate(Session session) throws CloneNotSupportedException, ValidateAccessException;

}
