package ru.burmistrov.tm.utils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionUtil {

    public static Connection getConnection() throws IOException, SQLException {
        InputStream inputStream;
        Properties property = new Properties();
        ConnectionUtil connectionUtil = new ConnectionUtil();

        inputStream = connectionUtil.getClass().getClassLoader().getResourceAsStream("application.properties");
        property.load(inputStream);
        String url = property.getProperty("urlDB");
        String password = property.getProperty("passwordDB");
        String user = property.getProperty("userDB");

        return DriverManager.getConnection(url, user, password);
    }
}
