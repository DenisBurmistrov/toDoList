package ru.burmistrov.tm.api.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.burmistrov.tm.entity.Session;
import ru.burmistrov.tm.exception.ValidateAccessException;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public interface ISessionService {

    Session persist(@NotNull final String userId) throws IOException, NoSuchAlgorithmException;

    boolean validate(@Nullable final Session session) throws CloneNotSupportedException, ValidateAccessException, NoSuchAlgorithmException;

    boolean validateAdmin(@Nullable final Session session) throws CloneNotSupportedException, ValidateAccessException, NoSuchAlgorithmException;
}
