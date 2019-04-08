package ru.burmistrov.tm.service;

import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class TerminalCommandService {

    @NotNull
    private final Scanner scanner = new Scanner(System.in);

    @NotNull
    public String nextLine() {
        return scanner.nextLine();
    }
}
