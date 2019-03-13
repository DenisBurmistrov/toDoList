package ru.burmistrov.tm.service;

import org.jetbrains.annotations.NotNull;
import ru.burmistrov.tm.api.loader.ServiceLocator;

import java.text.ParseException;
import java.util.Scanner;

public class TerminalCommandService {

    @NotNull
    private final ServiceLocator serviceLocator;

    public TerminalCommandService(@NotNull ServiceLocator serviceLocator) {
        this.serviceLocator = serviceLocator;
    }

    public void start() throws ParseException {
        Scanner scanner = serviceLocator.getScanner();
        System.out.println("    [ToDoList]\nВведите -logIn авторизоваться");
        while (true) {
            String input = scanner.nextLine();
            if ("-exit".equals(input)) {
                System.exit(0);
            }
            serviceLocator.execute(input);
        }
    }
}
