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

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class Bootstrap {

    private final Map<String, AbstractCommand> commands = new LinkedHashMap<>();

    private final ProjectRepository projectRepository = new ProjectRepository();

    private final TaskRepository taskRepository = new TaskRepository();

    private final UserRepository userRepository = new UserRepository();

    private final ProjectService projectService = new ProjectService(projectRepository, taskRepository);

    private final TaskService taskService = new TaskService(taskRepository, projectRepository);

    private final UserService userService = new UserService(userRepository);

    private final Scanner scanner = new Scanner(System.in);

    private  User currentUser;

    public void init(Bootstrap bootstrap) {
        bootstrap.initCommands(bootstrap);
        bootstrap.initProjectAndUser();

        start();
    }

    private void initProjectAndUser() {

        User admin = userService.registrate("admin", "admin", "admin", "admin", "admin", "admin@admin", Role.ADMINISTRATOR);
        User commonUser = userService.registrate("user", "user", "user", "user", "user", "user", Role.COMMON_USER)   ;
        Project project1 = projectService.persist(admin, "Первый проект", "Первое описание");
        Project project2 = projectService.persist(admin, "Второй проект", "Второе описание");
        Project project3 = projectService.persist(commonUser, "Третий проект", "Третье описание");
        Project project4 = projectService.persist(commonUser, "Четвертый проект", "Четвертое описание");

        taskService.persist(admin, project1.getId(), "Первая задача", "Первое описание");
        taskService.persist(admin, project2.getId(), "Вторая задача", "Вторая описание");
        taskService.persist(commonUser, project3.getId(), "Третья задача", "Третье описание");
        taskService.persist(commonUser, project4.getId(), "Четвертая задача", "Четвертое описание");
    }

    private void registerCommand(AbstractCommand abstractCommand) {
        commands.put(abstractCommand.getName(), abstractCommand);
    }

    private void initCommands(Bootstrap bootstrap) {
        registerCommand(new PrintListCommand(bootstrap));
        registerCommand(new ProjectListCommand(bootstrap));
        registerCommand(new ProjectCreateCommand(bootstrap));
        registerCommand(new ProjectRemoveCommand(bootstrap));
        registerCommand(new ProjectClearCommand(bootstrap));
        registerCommand(new TaskListCommand(bootstrap));
        registerCommand(new ProjectUpdateCommand(bootstrap));
        registerCommand(new TaskCreateCommand(bootstrap));
        registerCommand(new TaskClearCommand(bootstrap));
        registerCommand(new TaskRemoveCommand(bootstrap));
        registerCommand(new TaskUpdateCommand(bootstrap));
        registerCommand(new ProjectAssignUser(bootstrap));
        registerCommand(new UserLogInCommand(bootstrap));
        registerCommand(new UserLogOutCommand(bootstrap));
        registerCommand(new UserRegistrateCommand(bootstrap));
        registerCommand(new UserShowCurrentUser(bootstrap));
        registerCommand(new UserUpdateCurrentUser(bootstrap));
        registerCommand(new UserUpdatePasswordCommand(bootstrap));
    }

    private void execute(String command) {
        if (command == null || command.isEmpty()) return;
        AbstractCommand abstractCommand = commands.get(command);
        if (abstractCommand == null) return;
        if (abstractCommand.isSecure()) {
            if (isAuth()) {
                abstractCommand.execute();
            }
            return;
        }
        abstractCommand.execute();
    }

    private void start() {
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

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    public UserService getUserService() {
        return userService;
    }

    private boolean isAuth() {

        return currentUser != null;
    }
}




