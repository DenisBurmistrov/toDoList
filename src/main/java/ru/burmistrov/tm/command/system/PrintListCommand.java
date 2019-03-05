package ru.burmistrov.tm.command.system;

import ru.burmistrov.tm.Bootstrap;
import ru.burmistrov.tm.command.AbstractCommand;

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
        return "Print list of getLogin";
    }

    @Override
    public void execute() {
            System.out.println("Список команд:");
            getBootstrap().getCommands().forEach((k, v) -> System.out.println(k + " : " + v.getDescription()));
            System.out.println("-exit : Exit from program");
    }

    @Override
    public boolean isSecure() {
        return true;
    }
}
