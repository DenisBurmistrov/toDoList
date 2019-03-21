package ru.burmistrov.tm.service;

import org.jetbrains.annotations.NotNull;
import ru.burmistrov.tm.api.loader.ServiceLocator;
import ru.burmistrov.tm.endpoint.*;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.lang.ClassNotFoundException;
import java.text.ParseException;
import java.util.Scanner;

public class TerminalCommandService {

    @NotNull
    private final ServiceLocator serviceLocator;

    @NotNull
    private final Scanner scanner = new Scanner(System.in);

    public TerminalCommandService(@NotNull ServiceLocator serviceLocator) {
        this.serviceLocator = serviceLocator;
    }

    public void start() {
        System.out.println("    [ToDoList]\nВведите -logIn авторизоваться");
        while (true) {
            @NotNull final String input = scanner.nextLine();
            if ("-exit".equals(input)) {
                System.exit(0);
            }
            try {
                serviceLocator.execute(input);
            } catch (Exception_Exception e) {
                e.printStackTrace();
            }
        }
    }

    @NotNull
    public String nextLine() {
        return scanner.nextLine();
    }
}
