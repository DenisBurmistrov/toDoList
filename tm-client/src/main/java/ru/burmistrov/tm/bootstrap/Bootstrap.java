package ru.burmistrov.tm.bootstrap;

import com.sun.istack.internal.Nullable;
import org.jetbrains.annotations.NotNull;
import ru.burmistrov.tm.api.loader.ServiceLocator;
import ru.burmistrov.tm.command.AbstractCommand;
import ru.burmistrov.tm.endpoint.ProjectEndpoint;
import ru.burmistrov.tm.endpoint.TaskEndpoint;
import ru.burmistrov.tm.endpoint.UserEndpoint;
import ru.burmistrov.tm.service.TerminalCommandService;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.text.ParseException;
import java.util.LinkedHashMap;
import java.util.Map;

public class Bootstrap implements ServiceLocator {

    private final ProjectEndpoint projectEndpoint = new ProjectEndpoint(this);

    private final TaskEndpoint taskEndpoint = new TaskEndpoint(this);

    private final UserEndpoint userEndpoint = new UserEndpoint(this);

   private final Map<String, AbstractCommand> commands = new LinkedHashMap<>();

    @NotNull
    private final TerminalCommandService terminalCommandService = new TerminalCommandService(this);

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

    void init(Class... classes) {
        registry(classes);
    }

    @Override
    public void execute(@Nullable String command) throws ParseException, IOException, JAXBException, ClassNotFoundException {
        if (command == null || command.isEmpty()) return;
        @Nullable final AbstractCommand abstractCommand = commands.get(command);
        if (abstractCommand == null) return;
        if (abstractCommand.isSecure()) {
            /*if (isAuth()) {*/
                abstractCommand.execute();
           // }
            return;
        }
        abstractCommand.execute();
    }

    public ProjectEndpoint getProjectEndpoint() {
        return projectEndpoint;
    }

    public TaskEndpoint getTaskEndpoint() {
        return taskEndpoint;
    }

    public UserEndpoint getUserEndpoint() {
        return userEndpoint;
    }

    @NotNull
    public TerminalCommandService getTerminalCommandService() {
        return terminalCommandService;
    }

    public Map<String, AbstractCommand> getCommands() {
        return commands;
    }
}

