package ru.burmistrov.tm.service;

import org.jetbrains.annotations.NotNull;
import ru.burmistrov.tm.api.loader.ServiceLocator;
import ru.burmistrov.tm.endpoint.*;

import javax.inject.Inject;
import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.lang.ClassNotFoundException;
import java.text.ParseException;
import java.util.Scanner;

public class TerminalCommandService {

    @NotNull
    private final Scanner scanner = new Scanner(System.in);

    @NotNull
    public String nextLine() {
        return scanner.nextLine();
    }
}
