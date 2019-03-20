package ru.burmistrov.tm.bootstrap;

import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import ru.burmistrov.tm.api.loader.ServiceLocator;
import ru.burmistrov.tm.api.repository.IProjectRepository;
import ru.burmistrov.tm.api.repository.ISessionRepository;
import ru.burmistrov.tm.api.repository.ITaskRepository;
import ru.burmistrov.tm.api.repository.IUserRepository;
import ru.burmistrov.tm.api.service.*;
import ru.burmistrov.tm.endpoint.*;
import ru.burmistrov.tm.entity.*;
import ru.burmistrov.tm.repository.ProjectRepository;
import ru.burmistrov.tm.repository.SessionRepository;
import ru.burmistrov.tm.repository.TaskRepository;
import ru.burmistrov.tm.repository.UserRepository;
import ru.burmistrov.tm.service.*;

import javax.xml.ws.Endpoint;
import java.text.ParseException;
import java.util.LinkedHashMap;
import java.util.Objects;

@Getter
@Setter
public final class Bootstrap implements ServiceLocator {

    @NotNull private final IProjectRepository projectRepository = new ProjectRepository(new LinkedHashMap<>());

    @NotNull private final ITaskRepository taskRepository = new TaskRepository(new LinkedHashMap<>());

    @NotNull private final IUserRepository userRepository = new UserRepository();

    @NotNull private final ISessionRepository sessionRepository = new SessionRepository(new LinkedHashMap());

    @NotNull private final IProjectService projectService = new ProjectService(projectRepository, taskRepository);

    @NotNull private final ITaskService taskService = new TaskService(taskRepository);

    @NotNull private final IUserService userService = new UserService(userRepository);

    @NotNull private final ISessionService sessionService = new SessionService(sessionRepository, userRepository);

    @NotNull private final IAdminService adminService = new AdminService(projectService, taskService, projectRepository, taskRepository, userRepository);

    private void initProjectAndUserAndTask() {
        try {
            AbstractEntity admin = adminService.persist("admin", "admin", "admin", "admin", "admin", "admin@admin", Role.ADMINISTRATOR);
            AbstractEntity commonUser = adminService.persist("user", "user", "user", "user", "user", "user", Role.COMMON_USER);

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

    private void initEndpoints() {
        Endpoint.publish("http://localhost:8080/ProjectEndpoint", new ProjectEndpoint(this));
        Endpoint.publish("http://localhost:8080/TaskEndpoint", new TaskEndpoint(this));
        Endpoint.publish("http://localhost:8080/UserEndpoint", new UserEndpoint(this));
        Endpoint.publish("http://localhost:8080/SessionEndpoint", new SessionEndpoint(this));
        Endpoint.publish("http://localhost:8080/AdminEndpoint", new AdminEndpoint(this));
    }


    @Override
    public void init() {
        initProjectAndUserAndTask();
        initEndpoints();
    }
}




