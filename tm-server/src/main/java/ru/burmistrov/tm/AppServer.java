package ru.burmistrov.tm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.burmistrov.tm.api.loader.ServiceLocator;
import ru.burmistrov.tm.bootstrap.Bootstrap;
import ru.burmistrov.tm.configuration.SpringConfiguration;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.text.ParseException;


public class AppServer {

    public static void main(String[] args) throws IOException, ParseException, NoSuchAlgorithmException, SQLException {
        ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfiguration.class);
        ServiceLocator serviceLocator = context.getBean(Bootstrap.class);
        serviceLocator.init();
    }
}
