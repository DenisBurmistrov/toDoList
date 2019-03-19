package ru.burmistrov.tm.api.loader;

import com.sun.istack.internal.Nullable;
import ru.burmistrov.tm.command.AbstractCommand;
import ru.burmistrov.tm.endpoint.ProjectEndpoint;
import ru.burmistrov.tm.endpoint.TaskEndpoint;
import ru.burmistrov.tm.endpoint.UserEndpoint;
import ru.burmistrov.tm.service.TerminalCommandService;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.text.ParseException;
import java.util.Map;

public interface ServiceLocator {
/*
    @NotNull
    IProjectService<AbstractEntity> getProjectEndpoint();

    @NotNull
    ITaskService<AbstractEntity> getTaskEndpoint();

    @NotNull
    IUserService<AbstractEntity> getUserEndpoint();

    @NotNull
    Map<String, AbstractCommand> getCommands();

    @NotNull
    TerminalCommandService getTerminalCommandService();

    @NotNull
    User getCurrentUser();

    void setCurrentUser(@Nullable User user);*/

    void execute(@Nullable String command) throws ParseException, IOException, JAXBException, ClassNotFoundException;

    ProjectEndpoint getProjectEndpoint();

    TaskEndpoint getTaskEndpoint();

    UserEndpoint getUserEndpoint();

    TerminalCommandService getTerminalCommandService();

    public Map<String, AbstractCommand> getCommands();
}
