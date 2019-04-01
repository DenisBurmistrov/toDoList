package ru.burmistrov.tm.bootstrap;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.burmistrov.tm.api.loader.ServiceLocator;
import ru.burmistrov.tm.command.AbstractCommand;
import ru.burmistrov.tm.endpoint.*;
import ru.burmistrov.tm.service.TerminalCommandService;

import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.lang.ClassNotFoundException;
import java.lang.Exception;
import java.text.ParseException;
import java.util.LinkedHashMap;
import java.util.Map;
public class Bootstrap implements ServiceLocator {

    @Inject
    private ProjectEndpointService projectEndpointService;

    @Inject
    private TaskEndpointService taskEndpointService;

    @Inject
    private UserEndpointService userEndpointService;

    @Inject
    private SessionEndpointService sessionEndpointService;

    @Inject
    private AdminEndpointService adminEndpointService;

    @NotNull
    private final Map<String, AbstractCommand> commands = new LinkedHashMap<>();

    @Inject
    private TerminalCommandService terminalCommandService;

    @Nullable
    private Session session;

    private void registry(Class... classes) {
        for (Class commandClass : classes) {
            try {
                if (commandClass.getSuperclass().equals(AbstractCommand.class)) {
                    AbstractCommand abstractCommand = (AbstractCommand) commandClass.newInstance();
                    commands.put(abstractCommand.getName(), abstractCommand);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    public void init(Class... classes) {
        registry(classes);
        start();
    }

    public void start() {
        System.out.println("    [ToDoList]\nВведите -logIn авторизоваться");
        while (true) {
            @NotNull final String input = terminalCommandService.nextLine();
            if ("-exit".equals(input)) {
                System.exit(0);
            }
            try {
                execute(input);
            } catch (Exception_Exception | IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void execute(@Nullable String command) throws Exception_Exception, IOException {
        if (command == null || command.isEmpty()) return;
        @Nullable final AbstractCommand abstractCommand = commands.get(command);
        if (abstractCommand == null) return;
        if (abstractCommand.isSecure()) {
            if (isAuth()) {
                abstractCommand.execute();
            }
            return;
        }
        abstractCommand.execute();
    }

    @NotNull
    public TerminalCommandService getTerminalCommandService() {
        return terminalCommandService;
    }

    @NotNull
    public Map<String, AbstractCommand> getCommands() {
        return commands;
    }

    @Nullable
    @Produces
    public Session getSession() {
        return session;
    }

    public void setSession(final @NotNull Session session) {
        this.session = session;
    }

    private boolean isAuth() {
        return session != null;
    }

    @Produces
    public ProjectEndpoint getProjectEndpoint() {
        return projectEndpointService.getProjectEndpointPort();
    }

    @Produces
    public TaskEndpoint getTaskEndpoint() {
        return taskEndpointService.getTaskEndpointPort();
    }

    @Produces
    public AdminEndpoint getAdminEndpoint() {
        return adminEndpointService.getAdminEndpointPort();
    }

    @Produces
    public UserEndpoint getUserEndpoint() {
        return userEndpointService.getUserEndpointPort();
    }

    @Produces
    public SessionEndpoint getSessionEndpoint() {
        return sessionEndpointService.getSessionEndpointPort();
    }

}
