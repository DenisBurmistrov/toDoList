package ru.burmistrov.tm.api.loader;

import org.jetbrains.annotations.NotNull;
import ru.burmistrov.tm.api.service.*;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;

public interface ServiceLocator {

    void init() throws IOException, ParseException, NoSuchAlgorithmException, SQLException;

}
