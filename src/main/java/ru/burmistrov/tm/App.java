package ru.burmistrov.tm;


import org.jetbrains.annotations.NotNull;
import ru.burmistrov.tm.command.deserialize.DeserializeByDefaultCommand;
import ru.burmistrov.tm.command.project.*;
import ru.burmistrov.tm.command.serialize.*;
import ru.burmistrov.tm.command.system.PrintListCommand;
import ru.burmistrov.tm.command.system.PrintManifestCommand;
import ru.burmistrov.tm.command.task.*;
import ru.burmistrov.tm.command.user.*;

public class App {

    @NotNull
    private static final  Class[] CLASSES = new Class[]{ProjectClearCommand.class, ProjectCreateCommand.class, ProjectListCommand.class, ProjectRemoveCommand.class,
            ProjectUpdateCommand.class, PrintListCommand.class, ProjectListSortedByDateBeginCommand.class, ProjectListSortedByDateEndCommand.class,
            ProjectFindByNameCommand.class, ProjectFindByDescriptionCommand.class,
            ProjectListSortedByStatus.class, TaskClearCommand.class, TaskCreateCommand.class, TaskListCommand.class, TaskListSortedByDateBeginCommand.class,
            TaskListSortedByDateEndCommand.class, TaskListSortedByStatus.class, TaskRemoveCommand.class, TaskUpdateCommand.class,
            TaskFindByNameCommand.class, TaskFindByDescriptionCommand.class, UserClearCommand.class,
            UserLogInCommand.class, UserLogOutCommand.class, UserRegistrateCommand.class, UserRemoveCommand.class,
            UserShowCurrentUser.class, UserUpdateCurrentUser.class, UserUpdatePasswordCommand.class, PrintManifestCommand.class, SerializeByDefaultCommand.class,
            SerializeByJaxbXmlCommand.class, SerializeByJaxbJsonCommand.class, SerializeByFatserXmlCommand.class, SerializeByFasterXmlJson.class, DeserializeByDefaultCommand.class};

    public static void main(String[] args) {
        @NotNull final Bootstrap bootstrap = new Bootstrap();
        bootstrap.init(CLASSES);
    }
}
