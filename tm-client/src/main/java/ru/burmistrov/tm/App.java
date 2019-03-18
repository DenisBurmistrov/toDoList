package ru.burmistrov.tm;

import ru.burmistrov.tm.bootstrap.Bootstrap;

import java.net.MalformedURLException;

public class App {
    public static void main(String[] args) throws MalformedURLException {
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.initServices();
    }
}
