package ru.burmistrov.tm;

import ru.burmistrov.tm.command.AbstractCommand;
import ru.burmistrov.tm.command.system.PrintListCommand;
import ru.burmistrov.tm.command.project.*;
import ru.burmistrov.tm.command.task.*;
import ru.burmistrov.tm.command.user.*;
import ru.burmistrov.tm.entity.Project;
import ru.burmistrov.tm.entity.Role;
import ru.burmistrov.tm.entity.Task;
import ru.burmistrov.tm.entity.User;
import ru.burmistrov.tm.repository.ProjectRepository;
import ru.burmistrov.tm.repository.TaskRepository;
import ru.burmistrov.tm.repository.UserRepository;
import ru.burmistrov.tm.service.ProjectService;
import ru.burmistrov.tm.service.TaskService;
import ru.burmistrov.tm.service.UserService;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class Bootstrap {

    private static Bootstrap bootstrap;

    private static final Map<String, User> users = new LinkedHashMap<>();

    public static final Map<String, Project> projects = new LinkedHashMap<>();

    public static final Map<String, Task> tasks = new HashMap<>();

    private static final Map<String, AbstractCommand> commands = new LinkedHashMap<>();

    private static final ProjectRepository projectRepository = new ProjectRepository();

    private static final TaskRepository taskRepository = new TaskRepository();

    private static final UserRepository userRepository = new UserRepository();

    private static final ProjectService projectService = new ProjectService(projectRepository);

    private static final TaskService taskService = new TaskService(taskRepository);

    private static final UserService userService = new UserService(userRepository);

    private static final Scanner scanner = new Scanner(System.in);

    private static User currentUser;

    public static Bootstrap getInstance() {
        if (bootstrap != null) {
            return bootstrap;
        }
        return new Bootstrap();
    }

    private Bootstrap() {
    }

    public static void init() {
        Bootstrap bootstrap = getInstance();
        bootstrap.initCommands(bootstrap);
        bootstrap.initProjectAndUser();

        start();
    }

    private void initProjectAndUser() {
        User admin = new User();
        admin.setLogin("admin");
        admin.setPassword("admin");
        admin.setRole(Role.ADMINISTRATOR);
        admin.setFirstName("admin");
        admin.setMiddleName("admin");
        admin.setLastName("admin");
        admin.setEmail("admin@admin@");

        User user = new User();
        user.setLogin("user");
        user.setPassword("user");
        user.setRole(Role.COMMON_USER);
        user.setFirstName("user");
        user.setMiddleName("user");
        user.setLastName("user");
        user.setEmail("user");

        users.put(admin.getId(), admin);
        users.put(user.getId(), user);

        Project project1 = new Project("Первый проект", "Первое описание");
        project1.setUserId(admin.getId());
        Project project2 = new Project("Второй проект", "Второе описание");
        project2.setUserId(admin.getId());
        Project project3 = new Project("Третий проект", "Третье описание");
        project3.setUserId(admin.getId());
        Project project4 = new Project("Четвертый проект", "Четвертое описание");
        project4.setUserId(admin.getId());
        Project project5 = new Project("Пятый проект", "Пятое описание");
        project5.setUserId(admin.getId());
        Project project6 = new Project("Шестой проект", "Шестое описание");
        project6.setUserId(user.getId());
        Project project7 = new Project("Седьмой проект", "Седьмое описание");
        project7.setUserId(user.getId());
        Project project8 = new Project("Восьмой проект", "Восьмое описание");
        project8.setUserId(user.getId());
        Project project9 = new Project("Девятый проект", "Девятое описание");
        project9.setUserId(user.getId());
        Project project10 = new Project("Десятый проект", "Десятое описание");
        project10.setUserId(user.getId());
        projects.put(project1.getId(), project1);
        projects.put(project2.getId(), project2);
        projects.put(project3.getId(), project3);
        projects.put(project4.getId(), project4);
        projects.put(project5.getId(), project5);
        projects.put(project6.getId(), project6);
        projects.put(project7.getId(), project7);
        projects.put(project8.getId(), project8);
        projects.put(project9.getId(), project9);
        projects.put(project10.getId(), project10);


        }

    private void initCommands(Bootstrap bootstrap) {
        PrintListCommand printListCommand = new PrintListCommand(bootstrap);
        ProjectListCommand projectListCommand = new ProjectListCommand(bootstrap);
        ProjectCreateCommand projectCreateCommand = new ProjectCreateCommand(bootstrap);
        ProjectRemoveCommand projectRemoveCommand = new ProjectRemoveCommand(bootstrap);
        ProjectClearCommand projectClearCommand = new ProjectClearCommand(bootstrap);
        TaskListCommand taskListCommand = new TaskListCommand(bootstrap);
        ProjectUpdateCommand projectUpdateCommand = new ProjectUpdateCommand(bootstrap);
        TaskCreateCommand taskCreateCommand = new TaskCreateCommand(bootstrap);
        TaskClearCommand taskClearCommand = new TaskClearCommand(bootstrap);
        TaskRemoveCommand taskRemoveCommand = new TaskRemoveCommand(bootstrap);
        TaskUpdateCommand taskUpdateCommand = new TaskUpdateCommand(bootstrap);
        ProjectAssignUser projectAssignUser = new ProjectAssignUser(bootstrap);
        UserLogInCommand userLogInCommand = new UserLogInCommand(bootstrap);
        UserLogOutCommand userLogOutCommand = new UserLogOutCommand(bootstrap);
        UserRegistrateCommand userRegistrateCommand = new UserRegistrateCommand(bootstrap);
        UserShowCurrentUser userShowCurrentUser = new UserShowCurrentUser(bootstrap);
        UserUpdateCurrentUser userUpdateCurrentUser = new UserUpdateCurrentUser(bootstrap);
        UserUpdatePasswordCommand userUpdatePasswordCommand = new UserUpdatePasswordCommand(bootstrap);
        commands.put(printListCommand.getName(), printListCommand);
        commands.put(projectListCommand.getName(), projectListCommand);
        commands.put(projectCreateCommand.getName(), projectCreateCommand);
        commands.put(projectRemoveCommand.getName(), projectRemoveCommand);
        commands.put(projectClearCommand.getName(), projectClearCommand);
        commands.put(projectUpdateCommand.getName(), projectUpdateCommand);
        commands.put(taskCreateCommand.getName(), taskCreateCommand);
        commands.put(taskListCommand.getName(), taskListCommand);
        commands.put(taskRemoveCommand.getName(), taskRemoveCommand);
        commands.put(taskClearCommand.getName(), taskClearCommand);
        commands.put(taskUpdateCommand.getName(), taskUpdateCommand);
        commands.put(projectAssignUser.getName(), projectAssignUser);
        commands.put(userLogInCommand.getName(), userLogInCommand);
        commands.put(userLogOutCommand.getName(), userLogOutCommand);
        commands.put(userRegistrateCommand.getName(), userRegistrateCommand);
        commands.put(userShowCurrentUser.getName(), userShowCurrentUser);
        commands.put(userUpdateCurrentUser.getName(), userUpdateCurrentUser);
        commands.put(userUpdatePasswordCommand.getName(), userUpdatePasswordCommand);
    }

    private static void execute(String command) {
        if (command == null || command.isEmpty()) return;
        AbstractCommand abstractCommand = commands.get(command);
        if (abstractCommand == null) return;
        if (abstractCommand.isSecure()){
            if (currentUser != null) {
                abstractCommand.execute();
            }
            return;
        }
        abstractCommand.execute();
    }

    private static void start() {
        System.out.println("    [ToDoList]\nВведите -logIn авторизоваться");
        while (true) {
                    String input = scanner.nextLine();
                    if ("-exit".equals(input)) {
                        System.exit(0);
                    }
                    execute(input);
        }
    }

    public Map<String, AbstractCommand> getCommands() {
        return commands;
    }

    public Map<String, Project> getProjects() {
        return projects;
    }

    public ProjectRepository getProjectRepository() {
        return projectRepository;
    }

    public TaskRepository getTaskRepository() {
        return taskRepository;
    }

    public ProjectService getProjectService() {
        return projectService;
    }

    public TaskService getTaskService() {
        return taskService;
    }

    public Scanner getScanner() {
        return scanner;
    }

    public Map<String, Task> getTasks() {
        return tasks;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        Bootstrap.currentUser = currentUser;
    }

    public UserService getUserService() {
        return userService;
    }

    public Map<String, User> getUsers() {
        return users;
    }
}




