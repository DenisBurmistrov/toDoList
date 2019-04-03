package ru.burmistrov.tm;

import ru.burmistrov.tm.bootstrap.Bootstrap;

import javax.enterprise.inject.se.SeContainerInitializer;
import java.io.IOException;


public class AppServer {

    public static void main(String[] args) throws IOException {
            SeContainerInitializer.newInstance()
                .addPackages(AppServer.class).initialize()
                .select(Bootstrap.class).get().init();
    }
}
