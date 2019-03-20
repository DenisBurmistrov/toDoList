package ru.burmistrov.tm.api.loader;

import com.sun.istack.internal.Nullable;
import ru.burmistrov.tm.command.AbstractCommand;
import ru.burmistrov.tm.endpoint.*;
import ru.burmistrov.tm.service.TerminalCommandService;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.lang.ClassNotFoundException;
import java.text.ParseException;
import java.util.Map;

public interface ServiceLocator {


    void execute(@Nullable String command) throws ParseException, IOException, JAXBException, ClassNotFoundException, ParseException_Exception, ClassNotFoundException, ClassNotFoundException_Exception, IOException_Exception, JAXBException_Exception;

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
