package ru.burmistrov.tm;

import ru.burmistrov.tm.bootstrap.Bootstrap;

import javax.enterprise.inject.se.SeContainerInitializer;

public class AppClient {

    public static void main(final String[] args) {
        SeContainerInitializer.newInstance()
                .addPackages(AppClient.class).initialize()
                .select(Bootstrap.class).get().init();
    }

}
