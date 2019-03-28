package ru.burmistrov.tm.service;

import lombok.Data;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.InputStream;
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
        Properties property = new Properties();
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("application.properties");
        property.load(inputStream);
        String url = property.getProperty("urlDB");
        String password = property.getProperty("passwordDB");
        String user = property.getProperty("userDB");
        String driver = property.getProperty("driverDB");
        jdbcUrl = url;
        jdbcUsername = user;
        jdbcPassword = password;
        jdbcDriver = driver;
    }
}
