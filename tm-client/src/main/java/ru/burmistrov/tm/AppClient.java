package ru.burmistrov.tm;

import org.jetbrains.annotations.NotNull;
import ru.burmistrov.tm.bootstrap.Bootstrap;
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

import javax.enterprise.inject.se.SeContainerInitializer;
import java.net.MalformedURLException;

public class AppClient {

    @NotNull
    private static final  Class[] CLASSES = new Class[]{ProjectClearCommand.class, ProjectCreateCommand.class, ProjectListCommand.class, ProjectRemoveCommand.class,
            ProjectUpdateCommand.class, PrintListCommand.class, ProjectListSortedByDateBeginCommand.class, ProjectListSortedByDateEndCommand.class,
            ProjectFindByNameCommand.class, ProjectFindByDescriptionCommand.class,
            ProjectListSortedByStatus.class, TaskClearCommand.class, TaskCreateCommand.class, TaskListCommand.class, TaskListSortedByDateBeginCommand.class,
            TaskListSortedByDateEndCommand.class, TaskListSortedByStatus.class, TaskRemoveCommand.class, TaskUpdateCommand.class,
            TaskFindByNameCommand.class, TaskFindByDescriptionCommand.class, UserClearCommand.class,
            UserLogInCommand.class, UserLogOutCommand.class, UserRegistrateCommand.class, UserRemoveCommand.class,
            UserShowCurrentUser.class, UserUpdateCurrentUser.class, UserUpdatePasswordCommand.class, PrintManifestCommand.class, SerializeByDefaultCommand.class,
                SerializeByJaxbXmlCommand.class, SerializeByJaxbJsonCommand.class, SerializeByFasterXmlCommand.class, SerializeByFasterXmlJsonCommand.class, DeserializeByDefaultCommand.class,
                DeserializeByJaxbXmlCommand.class, DeserializeByJaxbJsonCommand.class, DeserializeByFasterXmlJsonCommand.class, DeserializeByFatserXmlCommand.class};

    public static void main(final String[] args) {
        SeContainerInitializer.newInstance()
                .addPackages(AppClient.class).initialize()
                .select(Bootstrap.class).get().init(CLASSES);
    }

}
