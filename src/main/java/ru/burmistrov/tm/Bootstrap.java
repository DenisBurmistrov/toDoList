package ru.burmistrov.tm;

import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.burmistrov.tm.api.loader.ServiceLocator;
import ru.burmistrov.tm.api.repository.IProjectRepository;
import ru.burmistrov.tm.api.repository.ITaskRepository;
import ru.burmistrov.tm.api.repository.IUserRepository;
import ru.burmistrov.tm.api.service.IProjectService;
import ru.burmistrov.tm.api.service.ITaskService;
import ru.burmistrov.tm.api.service.IUserService;
import ru.burmistrov.tm.command.AbstractCommand;
import ru.burmistrov.tm.entity.AbstractEntity;
import ru.burmistrov.tm.entity.Role;
import ru.burmistrov.tm.entity.User;
import ru.burmistrov.tm.repository.ProjectRepository;
import ru.burmistrov.tm.repository.TaskRepository;
import ru.burmistrov.tm.repository.UserRepository;
import ru.burmistrov.tm.service.ProjectService;
import ru.burmistrov.tm.service.TaskService;
import ru.burmistrov.tm.service.TerminalCommandService;
import ru.burmistrov.tm.service.UserService;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.text.ParseException;
import java.util.*;

@Getter
@Setter
public final class Bootstrap implements ServiceLocator {

    @NotNull private final Map<String, AbstractCommand> commands = new LinkedHashMap<>();

    @NotNull private final IProjectRepository<AbstractEntity> projectRepository = new ProjectRepository();

    @NotNull private final ITaskRepository<AbstractEntity> taskRepository = new TaskRepository();

    @NotNull private final IUserRepository<AbstractEntity> userRepository = new UserRepository();

    @NotNull private final IProjectService projectService = new ProjectService(projectRepository, taskRepository);

    @NotNull private final ITaskService taskService = new TaskService(taskRepository);

    @NotNull private final IUserService userService = new UserService(userRepository);

    @NotNull private final TerminalCommandService terminalCommandService = new TerminalCommandService(this);

    @Nullable private User currentUser;


    private void registry(Class... classes) {
        for(Class commandClass : classes) {
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
            initProjectAndUser();
            terminalCommandService.start();
    }

    private void initProjectAndUser() {
        try {
            AbstractEntity admin = userService.persist("admin", "admin", "admin", "admin", "admin", "admin@admin", Role.ADMINISTRATOR);
            AbstractEntity commonUser = userService.persist("user", "user", "user", "user", "user", "user", Role.COMMON_USER);

            AbstractEntity project1 = projectService.persist(Objects.requireNonNull(admin).getId(), "Первый проект", "Первое описание", "10.10.2019");
            AbstractEntity project2 = projectService.persist(admin.getId(), "Второй проект", "Второе описание","11.10.2019");
            AbstractEntity project3 = projectService.persist(Objects.requireNonNull(commonUser).getId(), "Третий проект", "Третье описание","12.10.2019");
            AbstractEntity project4 = projectService.persist(commonUser.getId(), "Четвертый проект", "Четвертое описание", "13.10.2019");

            taskService.persist(admin.getId(), Objects.requireNonNull(project1).getId(), "Первая задача", "Первое описание", "14.10.2019");
            taskService.persist(admin.getId(), Objects.requireNonNull(project2).getId(), "Вторая задача", "Вторая описание", "15.10.2019");
            taskService.persist(commonUser.getId(), Objects.requireNonNull(project3).getId(), "Третья задача", "Третье описание", "16.10.2019");
            taskService.persist(commonUser.getId(), Objects.requireNonNull(project4).getId(), "Четвертая задача", "Четвертое описание", "17.10.2019");
        }
        catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void execute(@Nullable String command) throws ParseException, IOException, JAXBException {
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

    private boolean isAuth() {
        return currentUser != null;
    }
}




