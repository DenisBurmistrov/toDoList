package ru.burmistrov.tm.bootstrap;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.burmistrov.tm.api.loader.ServiceLocator;
import ru.burmistrov.tm.command.AbstractCommand;
import ru.burmistrov.tm.endpoint.*;
import ru.burmistrov.tm.service.TerminalCommandService;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.lang.ClassNotFoundException;
import java.lang.Exception;
import java.text.ParseException;
import java.util.LinkedHashMap;
import java.util.Map;
public class Bootstrap implements ServiceLocator {

    @NotNull
    private final ProjectEndpointService projectEndpointService = new ProjectEndpointService();

    @NotNull
    private final TaskEndpointService taskEndpointService = new TaskEndpointService();

    @NotNull
    private final UserEndpointService userEndpointService = new UserEndpointService();

    @NotNull
    private final SessionEndpointService sessionEndpointService = new SessionEndpointService();

    @NotNull
    private final AdminEndpointService adminEndpointService = new AdminEndpointService();

    @NotNull
    private final SessionEndpoint sessionEndpoint = sessionEndpointService.getSessionEndpointPort();

    @NotNull
    private final ProjectEndpoint projectEndpoint = projectEndpointService.getProjectEndpointPort();

    @NotNull
    private final TaskEndpoint taskEndpoint = taskEndpointService.getTaskEndpointPort();

    @NotNull
    private final UserEndpoint userEndpoint = userEndpointService.getUserEndpointPort();

    @NotNull
    private final AdminEndpoint adminEndpoint = adminEndpointService.getAdminEndpointPort();

    @NotNull
    private final Map<String, AbstractCommand> commands = new LinkedHashMap<>();

    @NotNull
    private final TerminalCommandService terminalCommandService = new TerminalCommandService(this);

    @Nullable
    private Session session;

    private void registry(Class... classes) {
        for (Class commandClass : classes) {
            try {
                if (commandClass.getSuperclass().equals(AbstractCommand.class)) {
                    AbstractCommand abstractCommand = (AbstractCommand) commandClass.newInstance();
                    abstractCommand.setServiceLocator(this);
                    commands.put(abstractCommand.getName(), abstractCommand);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    public void init(Class... classes) {
        registry(classes);
        terminalCommandService.start();
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
    public ProjectEndpoint getProjectEndpoint() {
        return projectEndpoint;
    }

    @NotNull
    public TaskEndpoint getTaskEndpoint() {
        return taskEndpoint;
    }

    @NotNull
    public UserEndpoint getUserEndpoint() {
        return userEndpoint;
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
    public Session getSession() {
        return session;
    }

    public void setSession(final @NotNull Session session) {
        this.session = session;
    }

    private boolean isAuth() {
        return session != null;
    }

    @NotNull
    public SessionEndpoint getSessionEndpoint() {
        return sessionEndpoint;
    }

    @NotNull
    @Override
    public AdminEndpoint getAdminEndpoint() {
        return adminEndpoint;
    }
}
