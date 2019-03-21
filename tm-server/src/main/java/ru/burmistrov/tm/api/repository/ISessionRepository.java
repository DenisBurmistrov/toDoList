package ru.burmistrov.tm.api.repository;


import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.burmistrov.tm.entity.Session;
import ru.burmistrov.tm.exception.ValidateAccessException;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public interface ISessionRepository {

    @Nullable
    Session persist(@NotNull final Session abstractEntity) throws IOException, NoSuchAlgorithmException;

    boolean validate(@Nullable final Session session) throws CloneNotSupportedException, ValidateAccessException, NoSuchAlgorithmException;

}
