package ru.burmistrov.tm.api.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.burmistrov.tm.entity.Session;
import ru.burmistrov.tm.exception.ValidateAccessException;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

public interface ISessionService {

    @Nullable
    Session persist(@NotNull final String userId) throws IOException, NoSuchAlgorithmException, SQLException;

    boolean validate(@Nullable final Session session) throws CloneNotSupportedException, ValidateAccessException, NoSuchAlgorithmException, SQLException;

    boolean validateAdmin(@Nullable final Session session) throws CloneNotSupportedException, ValidateAccessException, NoSuchAlgorithmException, SQLException;
}
