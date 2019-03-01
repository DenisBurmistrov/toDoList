package ru.burmistrov.command;

import ru.burmistrov.Bootstrap;

public class PrintListCommand extends AbstractCommand {
    @Override
    public String command() {
        return "-help";
    }

    @Override
    public String description() {
        return "Print list of command";
    }

    @Override
    public void execute() {
        System.out.println("Список команд:");
        Bootstrap.commands.forEach((k, v) -> System.out.println(k + " : " + v.description()));
        System.out.println("-exit : Exit from program");
    }
}
