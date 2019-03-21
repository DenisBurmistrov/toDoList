package ru.burmistrov.tm;

import ru.burmistrov.tm.bootstrap.Bootstrap;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;


public class AppServ {

    public static void main(String[] args) throws IOException, ParseException, NoSuchAlgorithmException {
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.init();
    }
}
