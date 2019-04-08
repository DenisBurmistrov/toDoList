package ru.burmistrov.tm.api.loader;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.burmistrov.tm.command.AbstractCommand;
import ru.burmistrov.tm.endpoint.*;
import ru.burmistrov.tm.service.TerminalCommandService;

import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.Map;

public interface ServiceLocator {

    void execute(@Nullable String command) throws Exception_Exception, IOException;

    @NotNull
    TerminalCommandService getTerminalCommandService();

    @NotNull
    Map<String, AbstractCommand> getCommands();

    void setSession(Session session);

    @Nullable
    Session getSession();

    void init();
}


