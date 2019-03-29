package ru.burmistrov.tm.service;

import lombok.Data;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import java.util.Properties;

@Data
public class PropertyService {

    @NotNull
    private final String jdbcUrl;

    @NotNull
    private final String jdbcUsername;

    @NotNull
    private final String jdbcPassword;

    @NotNull
    private final String jdbcDriver;

    public PropertyService() throws IOException {
        @NotNull final Properties property = new Properties();
        @NotNull final InputStream inputStream = Objects.requireNonNull(this.getClass().getClassLoader().getResourceAsStream("application.properties"));
        property.load(inputStream);
        @NotNull final String url = property.getProperty("urlDB");
        @NotNull final String password = property.getProperty("passwordDB");
        @NotNull final String user = property.getProperty("userDB");
        @NotNull final String driver = property.getProperty("driverDB");
        jdbcUrl = url;
        jdbcUsername = user;
        jdbcPassword = password;
        jdbcDriver = driver;
    }
}
