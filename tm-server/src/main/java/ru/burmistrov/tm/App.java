package ru.burmistrov.tm;

import ru.burmistrov.tm.bootstrap.Bootstrap;
import ru.burmistrov.tm.endpoint.ProjectEndpoint;

import javax.xml.ws.Endpoint;

public class App {

    public static void main(String[] args) {
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.init();
    }
}
