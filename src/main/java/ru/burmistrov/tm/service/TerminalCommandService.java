package ru.burmistrov.tm.service;

import org.jetbrains.annotations.NotNull;
import ru.burmistrov.tm.api.loader.ServiceLocator;

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
            } catch (ParseException e) {
                System.out.println("Неверно введенна дата");
            } catch (NullPointerException e) {
                System.out.println("Неверно введены данные");
                e.printStackTrace();
            }
        }
    }

    @NotNull
    public Scanner getScanner() {
        return scanner;
    }
}
