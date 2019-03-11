package ru.burmistrov.tm;

import com.sun.istack.internal.NotNull;
import com.sun.istack.internal.Nullable;
import ru.burmistrov.tm.api.loader.ServiceLocator;
import ru.burmistrov.tm.api.repository.IProjectRepository;
import ru.burmistrov.tm.api.repository.ITaskRepository;
import ru.burmistrov.tm.api.repository.IUserRepository;
import ru.burmistrov.tm.api.service.IProjectService;
import ru.burmistrov.tm.api.service.ITaskService;
import ru.burmistrov.tm.api.service.IUserService;
import ru.burmistrov.tm.command.AbstractCommand;
import ru.burmistrov.tm.command.system.PrintListCommand;
import ru.burmistrov.tm.command.project.*;
import ru.burmistrov.tm.command.system.PrintManifestCommand;
import ru.burmistrov.tm.command.task.*;
import ru.burmistrov.tm.command.user.*;
import ru.burmistrov.tm.entity.AbstractEntity;
import ru.burmistrov.tm.entity.Role;
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

public final class Bootstrap implements ServiceLocator {

    private final Map<String, AbstractCommand> commands = new LinkedHashMap<>();

    private final IProjectRepository<AbstractEntity> projectRepository = new ProjectRepository();

    private final ITaskRepository<AbstractEntity> taskRepository = new TaskRepository();

    private final IUserRepository<AbstractEntity> userRepository = new UserRepository();

    private final IProjectService projectService = new ProjectService(projectRepository, taskRepository);

    private final ITaskService taskService = new TaskService(taskRepository);

    private final IUserService userService = new UserService(userRepository);

    private final Scanner scanner = new Scanner(System.in);

    private User currentUser;

    public void init(@NotNull Bootstrap bootstrap) {
        bootstrap.initCommands(bootstrap);
        bootstrap.initProjectAndUser();

        start();
    }

    private void initProjectAndUser() {

        AbstractEntity admin = userService.persist("admin", "admin", "admin", "admin", "admin", "admin@admin", Role.ADMINISTRATOR);
        AbstractEntity commonUser = userService.persist("user", "user", "user", "user", "user", "user", Role.COMMON_USER);
        AbstractEntity project1 = projectService.persist(admin.getId(), "Первый проект", "Первое описание");
        AbstractEntity project2 = projectService.persist(admin.getId(), "Второй проект", "Второе описание");
        AbstractEntity project3 = projectService.persist(commonUser.getId(), "Третий проект", "Третье описание");
        AbstractEntity project4 = projectService.persist(commonUser.getId(), "Четвертый проект", "Четвертое описание");

        taskService.persist(admin.getId(), project1.getId(), "Первая задача", "Первое описание");
        taskService.persist(admin.getId(), project2.getId(), "Вторая задача", "Вторая описание");
        taskService.persist(commonUser.getId(), project3.getId(), "Третья задача", "Третье описание");
        taskService.persist(commonUser.getId(), project4.getId(), "Четвертая задача", "Четвертое описание");
    }

    private void registerCommand(@NotNull AbstractCommand abstractCommand) {
        commands.put(abstractCommand.getName(), abstractCommand);
    }

    private void initCommands(ServiceLocator serviceLocator) {
        registerCommand(new PrintListCommand(serviceLocator));
        registerCommand(new ProjectListCommand(serviceLocator));
        registerCommand(new ProjectCreateCommand(serviceLocator));
        registerCommand(new ProjectRemoveCommand(serviceLocator));
        registerCommand(new ProjectClearCommand(serviceLocator));
        registerCommand(new TaskListCommand(serviceLocator));
        registerCommand(new ProjectUpdateCommand(serviceLocator));
        registerCommand(new TaskCreateCommand(serviceLocator));
        registerCommand(new TaskClearCommand(serviceLocator));
        registerCommand(new TaskRemoveCommand(serviceLocator));
        registerCommand(new TaskUpdateCommand(serviceLocator));
        registerCommand(new UserLogInCommand(serviceLocator));
        registerCommand(new UserLogOutCommand(serviceLocator));
        registerCommand(new UserRegistrateCommand(serviceLocator));
        registerCommand(new UserShowCurrentUser(serviceLocator));
        registerCommand(new UserUpdateCurrentUser(serviceLocator));
        registerCommand(new UserUpdatePasswordCommand(serviceLocator));
        registerCommand(new UserClearCommand(serviceLocator));
        registerCommand(new UserRemoveCommand(serviceLocator));
        registerCommand(new PrintManifestCommand(serviceLocator));
    }

    private void execute(@Nullable String command) {
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

    public IProjectRepository getProjectRepository() {
        return projectRepository;
    }

    public ITaskRepository getTaskRepository() {
        return taskRepository;
    }

    public IProjectService getProjectService() {
        return projectService;
    }

    public ITaskService getTaskService() {
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

    public IUserService getUserService() {
        return userService;
    }

    private boolean isAuth() {
        return currentUser != null;
    }
}




