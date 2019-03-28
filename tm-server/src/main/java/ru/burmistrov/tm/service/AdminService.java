package ru.burmistrov.tm.service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.eclipse.persistence.jaxb.UnmarshallerProperties;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.burmistrov.tm.api.repository.IProjectRepository;
import ru.burmistrov.tm.api.repository.ITaskRepository;
import ru.burmistrov.tm.api.repository.IUserRepository;
import ru.burmistrov.tm.api.service.IAdminService;
import ru.burmistrov.tm.api.service.IProjectService;
import ru.burmistrov.tm.api.service.ITaskService;
import ru.burmistrov.tm.entity.*;
import ru.burmistrov.tm.entity.enumerated.Role;
import ru.burmistrov.tm.repository.ProjectRepository;
import ru.burmistrov.tm.repository.TaskRepository;
import ru.burmistrov.tm.repository.UserRepository;
import ru.burmistrov.tm.utils.PasswordUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.*;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

public class AdminService implements IAdminService {

    @NotNull
    private final IProjectService projectService;

    @NotNull
    private final ITaskService taskService;

    @NotNull
    private IProjectRepository projectRepository;

    @NotNull
    private ITaskRepository taskRepository;

    @NotNull
    private IUserRepository userRepository;

    @NotNull
    private final EntityManagerFactory entityManagerFactory;


    public AdminService(@NotNull final IProjectService projectService,
                        @NotNull final ITaskService taskService, @NotNull final EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
        this.projectService = projectService;
        this.taskService = taskService;
    }

    public void saveDataByDefault(@NotNull final Session session) throws IOException, SQLException {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        projectRepository = new ProjectRepository(entityManager);
        taskRepository = new TaskRepository(entityManager);
        userRepository = new UserRepository(entityManager);
        @NotNull final ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("projects-and-tasks-by-admin.dat"));
        @NotNull final Domain domain = new Domain();

        domain.setProjects(Objects.requireNonNull(projectService.findAll(Objects.requireNonNull(session.getUserId()))));
        domain.setTasks(Objects.requireNonNull(taskService.findAll(session.getUserId())));
        domain.setUsers(Objects.requireNonNull(userRepository.findAll()));
        oos.writeObject(domain);
    }


    public void saveDataByFasterXmlJson(@NotNull final Session session) throws IOException, SQLException {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        projectRepository = new ProjectRepository(entityManager);
        taskRepository = new TaskRepository(entityManager);
        userRepository = new UserRepository(entityManager);
        @NotNull final Domain domain = new Domain();
        domain.setProjects(projectService.findAll(Objects.requireNonNull(session.getUserId())));
        domain.setTasks(Objects.requireNonNull(taskService.findAll(session.getUserId())));
        domain.setUsers(Objects.requireNonNull(userRepository.findAll()));

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.writeValue(new File("projects-and-tasks-by-admin.json"), domain);
    }

    public void saveDataByFasterXml(@NotNull final Session session) throws IOException, SQLException {

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        projectRepository = new ProjectRepository(entityManager);
        taskRepository = new TaskRepository(entityManager);
        userRepository = new UserRepository(entityManager);
        @NotNull final Domain domain = new Domain();

        domain.setProjects(Objects.requireNonNull(projectService.findAll(Objects.requireNonNull(session.getUserId()))));
        domain.setTasks(Objects.requireNonNull(taskService.findAll(session.getUserId())));
        domain.setUsers(Objects.requireNonNull(userRepository.findAll()));
        @NotNull final XmlMapper xmlMapper = new XmlMapper();
        xmlMapper.writeValue(new File("projects-and-tasks-by-admin.xml"), domain);
    }

    public void saveDataByJaxbJson(@NotNull final Session session) throws SQLException, JAXBException, IOException {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        projectRepository = new ProjectRepository(entityManager);
        taskRepository = new TaskRepository(entityManager);
        userRepository = new UserRepository(entityManager);
        System.setProperty("javax.xml.bind.context.factory", "org.eclipse.persistence.jaxb.JAXBContextFactory");
        @NotNull final Domain domain = new Domain();
        domain.setProjects(Objects.requireNonNull(projectService.findAll(Objects.requireNonNull(session.getUserId()))));
        domain.setTasks(Objects.requireNonNull(taskService.findAll(session.getUserId())));
        domain.setUsers(Objects.requireNonNull(userRepository.findAll()));
        @NotNull final JAXBContext jaxbContext = JAXBContext.newInstance(Domain.class);
        @NotNull final Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty("eclipselink.media-type", "application/json");

        marshaller.marshal(domain, new FileWriter("projects-and-tasks-by-admin.json"));
    }

    public void saveDataByJaxbXml(@NotNull final Session session) throws IOException, JAXBException, SQLException {

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        projectRepository = new ProjectRepository(entityManager);
        taskRepository = new TaskRepository(entityManager);
        userRepository = new UserRepository(entityManager);
        @NotNull final Domain domain = new Domain();

        domain.setProjects(Objects.requireNonNull(projectService.findAll(Objects.requireNonNull(session.getUserId()))));
        domain.setTasks(Objects.requireNonNull(taskService.findAll(session.getUserId())));
        domain.setUsers(Objects.requireNonNull(userRepository.findAll()));
        @NotNull final JAXBContext context = JAXBContext.newInstance(Domain.class);
        @NotNull final Marshaller m = context.createMarshaller();
        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        m.marshal(domain, new FileWriter("projects-and-tasks-by-admin.xml"));
    }

    public void loadDataByDefault(@NotNull final Session session) throws IOException, ClassNotFoundException {

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        projectRepository = new ProjectRepository(entityManager);
        taskRepository = new TaskRepository(entityManager);
        userRepository = new UserRepository(entityManager);
        @NotNull final FileInputStream fileInputStream = new FileInputStream("C:\\Users\\d.burmistrov\\IdeaProjects\\toDoList\\" + "projects-and-tasks-by-admin.dat");
        @NotNull final ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

        @NotNull final Domain domain = (Domain) objectInputStream.readObject();

        for (@NotNull final Project project : domain.getProjects()) {
            projectRepository.persist(project);
        }
        for (@NotNull final Task task : domain.getTasks()) {
            taskRepository.persist(task);
        }
        for (@NotNull final User user : domain.getUsers()) {
            userRepository.persist(user);
        }
    }

    public void loadDataByFasterXmlJson(@NotNull final Session session) throws IOException {

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        projectRepository = new ProjectRepository(entityManager);
        taskRepository = new TaskRepository(entityManager);
        userRepository = new UserRepository(entityManager);
        @NotNull final File file = new File("projects-and-tasks-by-admin.json");
        @NotNull final ObjectMapper objectMapper = new ObjectMapper();
        @NotNull final Domain domain = objectMapper.readValue(file, Domain.class);

        for (@NotNull final Project project : domain.getProjects()) {
            projectRepository.persist(project);
        }
        for (@NotNull final Task task : domain.getTasks()) {
            taskRepository.persist(task);
        }
        for (@NotNull final User user : domain.getUsers()) {
            userRepository.persist(user);
        }
    }

    public void loadDataByFasterXml(@NotNull final Session session) throws IOException {

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        projectRepository = new ProjectRepository(entityManager);
        taskRepository = new TaskRepository(entityManager);
        userRepository = new UserRepository(entityManager);
        @NotNull final File file = new File("projects-and-tasks-by-admin.xml");
        @NotNull final XmlMapper xmlMapper = new XmlMapper();
        @NotNull final Domain domain = xmlMapper.readValue(file, Domain.class);

        for (@NotNull final Project project : domain.getProjects()) {
            projectRepository.persist(project);
        }
        for (@NotNull final Task task : domain.getTasks()) {
            taskRepository.persist(task);
        }
        for (@NotNull final User user : domain.getUsers()) {
            userRepository.persist(user);
        }
    }

    public void loadDataByJaxbJson(@NotNull final Session session) throws JAXBException {

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        projectRepository = new ProjectRepository(entityManager);
        taskRepository = new TaskRepository(entityManager);
        userRepository = new UserRepository(entityManager);
        System.setProperty("javax.xml.bind.context.factory", "org.eclipse.persistence.jaxb.JAXBContextFactory");

        @NotNull final JAXBContext jaxbContext = JAXBContext.newInstance(Domain.class);
        @NotNull final Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

        unmarshaller.setProperty(UnmarshallerProperties.MEDIA_TYPE, "application/json");
        unmarshaller.setProperty(UnmarshallerProperties.JSON_INCLUDE_ROOT, true);
        @NotNull final Domain domain = (Domain) unmarshaller.unmarshal(new File("C:\\Users\\d.burmistrov\\IdeaProjects\\toDoList\\projects-and-tasks-by-admin.json"));

        for (@NotNull final Project project : domain.getProjects()) {
            projectRepository.persist(project);
        }
        for (@NotNull final Task task : domain.getTasks()) {
            taskRepository.persist(task);
        }
        for (@NotNull final User user : domain.getUsers()) {
            userRepository.persist(user);
        }
    }

    public void loadDataByJaxbXml(@NotNull final Session session) throws JAXBException {

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        projectRepository = new ProjectRepository(entityManager);
        taskRepository = new TaskRepository(entityManager);
        userRepository = new UserRepository(entityManager);
        @NotNull final File file = new File("projects-and-tasks-by-admin.xml");
        @NotNull final JAXBContext jaxbContext = JAXBContext.newInstance(Domain.class);
        @NotNull final Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        @NotNull final Domain domain = (Domain) unmarshaller.unmarshal(file);

        for (@NotNull final Project project : domain.getProjects()) {
            projectRepository.persist(project);
        }
        for (@NotNull final Task task : domain.getTasks()) {
            taskRepository.persist(task);
        }
        for (@NotNull final User user : domain.getUsers()) {
            userRepository.persist(user);
        }
    }

    @Override
    @Nullable
    public User createUser(@NotNull final String login, @NotNull final String password, @NotNull final String firstName,
                           @NotNull final String middleName, final @NotNull String lastName, final @NotNull String email,
                           @Nullable Role roleType) throws NoSuchAlgorithmException {


        EntityManager entityManager = entityManagerFactory.createEntityManager();
        userRepository = new UserRepository(entityManager);
        @Nullable final User abstractEntity = userRepository.findOneByLogin(login);
        if (abstractEntity == null) {
            @NotNull final User user = new User();
            user.setLogin(login);
            user.setHashPassword(password);
            user.setFirstName(firstName);
            user.setMiddleName(middleName);
            user.setLastName(lastName);
            user.setEmail(email);
            user.setRole(roleType);
            try {
                entityManager.getTransaction().begin();
                Objects.requireNonNull(userRepository).persist(user);
                entityManager.getTransaction().commit();
                return user;
            } catch (Exception e) {
                entityManager.getTransaction().rollback();
            }
        }
        return null;
    }

    @Override
    public void updatePassword(@NotNull final String login, @NotNull final String password) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        userRepository = new UserRepository(entityManager);
        if (password.length() > 0) {
            try {
                entityManager.getTransaction().begin();
                Objects.requireNonNull(userRepository).updatePassword(login, PasswordUtil.hashPassword(password));
                entityManager.getTransaction().commit();
            } catch (Exception e) {
                entityManager.getTransaction().rollback();
            }
        }
    }

    @Override
    public void updateUserById(@NotNull final String userId, @NotNull final String firstName, @NotNull final String middleName,
                               @NotNull final String lastName, final @NotNull String email, final @NotNull Role role) {

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        userRepository = new UserRepository(entityManager);
        @NotNull final User currentUser = new User();
        currentUser.setFirstName(firstName);
        currentUser.setMiddleName(middleName);
        currentUser.setLastName(lastName);
        currentUser.setEmail(email);
        currentUser.setId(userId);
        currentUser.setRole(role);
        @Nullable final AbstractEntity abstractEntity = userRepository.findOne(userId);
        if (abstractEntity != null) {
            try {
                entityManager.getTransaction().begin();
                Objects.requireNonNull(userRepository).merge(currentUser);
                entityManager.getTransaction().commit();
            } catch (Exception e) {
                entityManager.getTransaction().rollback();
            }
        }
    }

    @Override
    public void removeUserById(@NotNull final String userId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        userRepository = new UserRepository(entityManager);
        try {
            entityManager.getTransaction().begin();
            Objects.requireNonNull(userRepository).remove(Objects.requireNonNull(userId));
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
        }
    }

    @Override
    public void removeAllUsers() {

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        userRepository = new UserRepository(entityManager);
        try {
            entityManager.getTransaction().begin();
            Objects.requireNonNull(userRepository).removeAll();
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
        }
    }

    @Nullable
    public List<User> findAll() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        userRepository = new UserRepository(entityManager);
        return Objects.requireNonNull(userRepository).findAll();
    }

    @Nullable
    public User findOne(@NotNull final String id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        userRepository = new UserRepository(entityManager);
        return Objects.requireNonNull(userRepository).findOne(id);
    }

    @Nullable
    public User findOneByLogin(@NotNull final String login) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        userRepository = new UserRepository(entityManager);
        return Objects.requireNonNull(userRepository).findOneByLogin(login);
    }
}
