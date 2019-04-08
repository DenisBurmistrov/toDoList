package ru.burmistrov.tm.command.system;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.burmistrov.tm.api.loader.ServiceLocator;
import ru.burmistrov.tm.command.AbstractCommand;

@Component
public final class PrintListCommand extends AbstractCommand {

    @Autowired
    private ServiceLocator serviceLocator;

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
        serviceLocator.getCommands().forEach((k, v) -> System.out.println(k + " : " + v.getDescription()));
        System.out.println("-exit : Exit from program");
    }

    @Override
    public boolean isSecure() {
        return true;
    }
}
