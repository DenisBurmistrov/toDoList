package ru.burmistrov.tm;


import ru.burmistrov.tm.command.project.*;
import ru.burmistrov.tm.command.system.PrintListCommand;
import ru.burmistrov.tm.command.task.*;
import ru.burmistrov.tm.command.user.*;

public class App {

    public static final  Class[] CLASSES = new Class[]{ProjectClearCommand.class, ProjectCreateCommand.class, ProjectListCommand.class, ProjectRemoveCommand.class,
            ProjectUpdateCommand.class, PrintListCommand.class, ProjectListSortedByDateBeginCommand.class, ProjectListSortedByDateEndCommand.class,
            ProjectFindByNameCommand.class, ProjectFindByDescriptionCommand.class,
            ProjectListSortedByStatus.class, TaskClearCommand.class, TaskCreateCommand.class, TaskListCommand.class, TaskListSortedByDateBeginCommand.class,
            TaskListSortedByDateEndCommand.class, TaskListSortedByStatus.class, TaskRemoveCommand.class, TaskUpdateCommand.class,
            TaskFindByNameCommand.class, TaskFindByDescriptionCommand.class, UserClearCommand.class,
            UserLogInCommand.class, UserLogOutCommand.class, UserRegistrateCommand.class, UserRemoveCommand.class,
            UserShowCurrentUser.class, UserUpdateCurrentUser.class, UserUpdatePasswordCommand.class};

    public static void main(String[] args) {
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.init(CLASSES);
    }
}
