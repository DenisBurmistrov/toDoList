package ru.burmistrov.tm.command;

import ru.burmistrov.tm.Bootstrap;

public class PrintListCommand extends AbstractCommand {


    public PrintListCommand(Bootstrap bootstrap) {
        super(bootstrap);
    }

    @Override
    public String getName() {
        return "-help";
    }

    @Override
    public String getDescription() {
        return "Print list of getName";
    }

    @Override
    public void execute() {
        System.out.println("Список команд:");
        super.getBootstrap().getCommands().forEach((k, v) -> System.out.println(k + " : " + v.getDescription()));
        System.out.println("-exit : Exit from program");
    }
}
