package ru.burmistrov.tm.command.system;

import org.jetbrains.annotations.NotNull;
import ru.burmistrov.tm.command.AbstractCommand;

public final class PrintListCommand extends AbstractCommand {

    public PrintListCommand() {
    }

    @NotNull
    @Override
    public String getName() {
        return "-help";
    }

    @NotNull
    @Override
    public String getDescription() {
        return "Print list of getLogin";
    }

    @Override
    public void execute() {
        System.out.println("Список команд:");
        getServiceLocator().getCommands().forEach((k, v) -> System.out.println(k + " : " + v.getDescription()));
        System.out.println("-exit : Exit from program");
    }

    @Override
    public boolean isSecure() {
        return true;
    }
}
