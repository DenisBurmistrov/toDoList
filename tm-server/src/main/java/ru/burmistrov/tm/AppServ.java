package ru.burmistrov.tm;

import ru.burmistrov.tm.bootstrap.Bootstrap;

import javax.enterprise.inject.se.SeContainerInitializer;
import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.text.ParseException;


public class AppServ {

    public static void main(String[] args) throws IOException, ParseException, NoSuchAlgorithmException, SQLException {

            SeContainerInitializer.newInstance()
                .addPackages(AppServ.class).initialize()
                .select(Bootstrap.class).get().init();
    }
}
