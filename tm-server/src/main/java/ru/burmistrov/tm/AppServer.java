package ru.burmistrov.tm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.burmistrov.tm.api.loader.ServiceLocator;
import ru.burmistrov.tm.bootstrap.Bootstrap;
import ru.burmistrov.tm.configuration.SpringConfiguration;
import ru.burmistrov.tm.entity.User;
import ru.burmistrov.tm.entity.enumerated.Role;
import ru.burmistrov.tm.repository.IUserRepository;
import ru.burmistrov.tm.util.PasswordUtil;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.text.ParseException;


public class AppServer {

    public static void main(String[] args) throws IOException, ParseException, NoSuchAlgorithmException, SQLException {
        ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfiguration.class);
        ServiceLocator serviceLocator = context.getBean(Bootstrap.class);
        IUserRepository userRepository = context.getBean(IUserRepository.class);
        serviceLocator.init();
        User userAdmin = new User();
        userAdmin.setLogin("admin");
        userAdmin.setPassword(PasswordUtil.hashPassword("admin"));
        userAdmin.setFirstName("admin");
        userAdmin.setMiddleName("admin");
        userAdmin.setLastName("admin");
        userAdmin.setEmail("admin");
        userAdmin.setRole(Role.ADMINISTRATOR);
        userRepository.save(userAdmin);
    }
}
