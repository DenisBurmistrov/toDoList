package ru.burmistrov.tm.api.loader;

import org.jetbrains.annotations.Nullable;
import ru.burmistrov.tm.command.AbstractCommand;
import ru.burmistrov.tm.endpoint.*;
import ru.burmistrov.tm.service.TerminalCommandService;

import java.util.Map;

public interface ServiceLocator {


    void execute(@Nullable String command) throws Exception_Exception;

    ProjectEndpoint getProjectEndpoint();

    TaskEndpoint getTaskEndpoint();

    UserEndpoint getUserEndpoint();

    TerminalCommandService getTerminalCommandService();

    Map<String, AbstractCommand> getCommands();

    void setSession(Session session);

    Session getSession();

    SessionEndpoint getSessionEndpoint();

    AdminEndpoint getAdminEndpoint();


    }
