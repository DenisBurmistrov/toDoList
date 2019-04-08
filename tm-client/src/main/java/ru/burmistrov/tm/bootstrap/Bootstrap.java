package ru.burmistrov.tm.bootstrap;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import ru.burmistrov.tm.api.loader.ServiceLocator;
import ru.burmistrov.tm.command.AbstractCommand;
import ru.burmistrov.tm.command.admin.deserialize.*;
import ru.burmistrov.tm.command.admin.serialize.*;
import ru.burmistrov.tm.command.admin.user.*;
import ru.burmistrov.tm.command.project.*;
import ru.burmistrov.tm.command.system.PrintListCommand;
import ru.burmistrov.tm.command.system.PrintManifestCommand;
import ru.burmistrov.tm.command.task.*;
import ru.burmistrov.tm.command.user.UserLogInCommand;
import ru.burmistrov.tm.command.user.UserLogOutCommand;
import ru.burmistrov.tm.command.user.UserShowCurrentUser;
import ru.burmistrov.tm.endpoint.*;
import ru.burmistrov.tm.service.TerminalCommandService;

import java.io.IOException;
import java.lang.Exception;
import java.util.HashMap;
import java.util.Map;

@Component
public class Bootstrap implements ServiceLocator {

    @Autowired
    ApplicationContext context;

    @Autowired
    private TerminalCommandService terminalCommandService;

    @Nullable
    private Session session;

    private Map<String ,AbstractCommand> commands = new HashMap<>();


    @NotNull
    private static final  Class[] CLASSES = new Class[]{ProjectClearCommand.class, ProjectCreateCommand.class, ProjectListCommand.class, ProjectRemoveCommand.class,
            ProjectUpdateCommand.class, PrintListCommand.class, ProjectListSortedByDateBeginCommand.class, ProjectListSortedByDateEndCommand.class,
            ProjectFindByNameCommand.class, ProjectFindByDescriptionCommand.class,
            ProjectListSortedByStatus.class, TaskClearCommand.class, TaskCreateCommand.class, TaskListCommand.class, TaskListSortedByDateBeginCommand.class,
            TaskListSortedByDateEndCommand.class, TaskListSortedByStatus.class, TaskRemoveCommand.class, TaskUpdateCommand.class,
            TaskFindByNameCommand.class, TaskFindByDescriptionCommand.class, UserClearCommand.class,
            UserLogInCommand.class, UserLogOutCommand.class, UserRegistrateCommand.class, UserRemoveCommand.class,
            UserShowCurrentUser.class, UserUpdateUserByLogin.class, UserUpdatePasswordCommand.class, PrintManifestCommand.class, SerializeByDefaultCommand.class,
            SerializeByJaxbXmlCommand.class, SerializeByJaxbJsonCommand.class, SerializeByFasterXmlCommand.class, SerializeByFasterXmlJsonCommand.class, DeserializeByDefaultCommand.class,
            DeserializeByJaxbXmlCommand.class, DeserializeByJaxbJsonCommand.class, DeserializeByFasterXmlJsonCommand.class, DeserializeByFatserXmlCommand.class};


    public void init() {
        registry(CLASSES);
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

    private void registry(Class... classes) {
        for (Class commandClass : classes) {
            try {
                if (commandClass.getSuperclass().equals(AbstractCommand.class)) {
                    AbstractCommand abstractCommand = (AbstractCommand) context.getBean(commandClass);
                    if(abstractCommand != null) {
                        commands.put(abstractCommand.getName(), abstractCommand);
                    }
                }
            } catch (Exception e) {
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
    @Override
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
}
