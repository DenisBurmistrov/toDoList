package ru.burmistrov.tm.utils;

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

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@ApplicationScoped
public class InitCommandUtil {

    @Inject
    private DeserializeByDefaultCommand deserializeByDefaultCommand;
    @Inject
    private DeserializeByFasterXmlJsonCommand deserializeByFasterXmlJsonCommand;
    @Inject
    private DeserializeByFatserXmlCommand deserializeByFatserXmlCommand;
    @Inject
    private DeserializeByJaxbJsonCommand deserializeByJaxbJsonCommand;
    @Inject
    private DeserializeByJaxbXmlCommand deserializeByJaxbXmlCommand;
    @Inject
    private SerializeByDefaultCommand serializeByDefaultCommand;
    @Inject
    private SerializeByFasterXmlCommand serializeByFasterXmlCommand;
    @Inject
    private SerializeByJaxbJsonCommand serializeByJaxbJsonCommand;
    @Inject
    private SerializeByFasterXmlJsonCommand serializeByFasterXmlJsonCommand;
    @Inject
    private SerializeByJaxbXmlCommand serializeByJaxbXmlCommand;
    @Inject
    private UserClearCommand userClearCommand;
    @Inject
    private UserRegistrateCommand userRegistrateCommand;
    @Inject
    private UserRemoveCommand userRemoveCommand;
    @Inject
    private UserUpdateCurrentUser userUpdateCurrentUser;
    @Inject
    private ProjectClearCommand projectClearCommand;
    @Inject
    private ProjectCreateCommand projectCreateCommand;
    @Inject
    private ProjectFindByDescriptionCommand projectFindByDescriptionCommand;
    @Inject
    private ProjectFindByNameCommand projectFindByNameCommand;
    @Inject
    private ProjectListCommand projectListCommand;
    @Inject
    private ProjectListSortedByDateBeginCommand projectListSortedByDateBeginCommand;
    @Inject
    private ProjectListSortedByDateEndCommand projectListSortedByDateEndCommand;
    @Inject
    private ProjectListSortedByStatus projectListSortedByStatus;
    @Inject
    private ProjectRemoveCommand projectRemoveCommand;
    @Inject
    private ProjectUpdateCommand projectUpdateCommand;
    @Inject
    private PrintListCommand printListCommand;
    @Inject
    private PrintManifestCommand printManifestCommand;
    @Inject
    private TaskClearCommand taskClearCommand;
    @Inject
    private TaskCreateCommand taskCreateCommand;
    @Inject
    private TaskFindByDescriptionCommand taskFindByDescriptionCommand;
    @Inject
    private TaskFindByNameCommand taskFindByNameCommand;
    @Inject
    private TaskListCommand taskListCommand;
    @Inject
    private TaskListSortedByDateBeginCommand taskListSortedByDateBeginCommand;
    @Inject
    private TaskListSortedByDateEndCommand taskListSortedByDateEndCommand;
    @Inject
    private TaskListSortedByStatus taskListSortedByStatus;
    @Inject
    private TaskRemoveCommand taskRemoveCommand;
    @Inject
    private TaskUpdateCommand taskUpdateCommand;
    @Inject
    private UserLogInCommand userLogInCommand;
    @Inject
    private UserLogOutCommand userLogOutCommand;
    @Inject
    private UserShowCurrentUser userShowCurrentUser;

    private Map<String ,AbstractCommand> commands = new HashMap<>();

    @PostConstruct
    public void initCommands(){
        commands.put(deserializeByDefaultCommand.getName() ,deserializeByDefaultCommand);
        commands.put(deserializeByFasterXmlJsonCommand.getName() ,deserializeByFasterXmlJsonCommand);
        commands.put(deserializeByFatserXmlCommand.getName() ,deserializeByFatserXmlCommand);
        commands.put(deserializeByJaxbJsonCommand.getName(), deserializeByJaxbJsonCommand);
        commands.put(deserializeByJaxbXmlCommand.getName(), deserializeByJaxbXmlCommand);
        commands.put(serializeByDefaultCommand.getName(), serializeByDefaultCommand);
        commands.put(serializeByFasterXmlCommand.getName(), serializeByFasterXmlCommand);
        commands.put(serializeByJaxbJsonCommand.getName(), serializeByJaxbJsonCommand);
        commands.put(serializeByFasterXmlJsonCommand.getName(), serializeByFasterXmlJsonCommand);
        commands.put(serializeByJaxbXmlCommand.getName(), serializeByJaxbXmlCommand);
        commands.put(userClearCommand.getName(), userClearCommand);
        commands.put(userRegistrateCommand.getName(), userRegistrateCommand);
        commands.put(userRemoveCommand.getName(), userRemoveCommand);
        commands.put(userUpdateCurrentUser.getName(), userUpdateCurrentUser);
        commands.put(projectClearCommand.getName(), projectClearCommand);
        commands.put(projectCreateCommand.getName(), projectCreateCommand);
        commands.put(projectFindByDescriptionCommand.getName(), projectFindByDescriptionCommand);
        commands.put(projectFindByNameCommand.getName(), projectFindByNameCommand);
        commands.put(projectListCommand.getName(), projectListCommand);
        commands.put(projectListSortedByDateBeginCommand.getName(), projectListSortedByDateBeginCommand);
        commands.put(projectListSortedByDateEndCommand.getName(), projectListSortedByDateEndCommand);
        commands.put(projectListSortedByStatus.getName(), projectListSortedByStatus);
        commands.put(projectRemoveCommand.getName(), projectRemoveCommand);
        commands.put(projectUpdateCommand.getName(), projectUpdateCommand);
        commands.put(printListCommand.getName(), printListCommand);
        commands.put(printManifestCommand.getName(), printManifestCommand);
        commands.put(taskClearCommand.getName(), taskClearCommand);
        commands.put(taskCreateCommand.getName(), taskCreateCommand);
        commands.put(taskFindByDescriptionCommand.getName(), taskFindByDescriptionCommand);
        commands.put(taskFindByNameCommand.getName(), taskFindByNameCommand);
        commands.put(taskListCommand.getName(), taskListCommand);
        commands.put(taskListSortedByDateBeginCommand.getName(), taskListSortedByDateBeginCommand);
        commands.put(taskListSortedByDateEndCommand.getName(), taskListSortedByDateEndCommand);
        commands.put(taskListSortedByStatus.getName(), taskListSortedByStatus);
        commands.put(taskRemoveCommand.getName(), taskRemoveCommand);
        commands.put(taskUpdateCommand.getName(), taskUpdateCommand);
        commands.put(userLogInCommand.getName(), userLogInCommand);
        commands.put(userLogOutCommand.getName(), userLogOutCommand);
        commands.put(userShowCurrentUser.getName(), userShowCurrentUser);
    }

    @Produces
    public Map<String ,AbstractCommand> getCommands() {
        return commands;
    }
}
