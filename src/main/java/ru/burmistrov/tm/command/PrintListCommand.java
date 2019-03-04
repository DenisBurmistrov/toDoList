package ru.burmistrov.tm.command;

import ru.burmistrov.tm.Bootstrap;

public class PrintListCommand extends AbstractCommand {


    public PrintListCommand(Bootstrap bootstrap) {
        super(bootstrap);
    }

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
        super.getBootstrap().getCommands().forEach((k, v) -> System.out.println(k + " : " + v.description()));
        System.out.println("-exit : Exit from program");
    }
}
