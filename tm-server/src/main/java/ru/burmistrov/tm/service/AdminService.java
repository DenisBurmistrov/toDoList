package ru.burmistrov.tm.service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.apache.ibatis.session.SqlSession;
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
import ru.burmistrov.tm.utils.PasswordUtil;

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
    private final SqlSession session;

    @NotNull
    private final IProjectService projectService;

    @NotNull
    private final ITaskService taskService;

    @NotNull
    private final IProjectRepository projectRepository;

    @NotNull
    private final ITaskRepository taskRepository;

    @NotNull
    private final IUserRepository userRepository;


    public AdminService( @NotNull final IProjectService projectService,  @NotNull final ITaskService taskService, @NotNull final SqlSession session) {
        this.session = session;
        this.projectService = projectService;
        this.taskService = taskService;
        this.projectRepository = session.getMapper(IProjectRepository.class);
        this.taskRepository = session.getMapper(ITaskRepository.class);
        this.userRepository = session.getMapper(IUserRepository.class);
    }

    public void saveDataByDefault(@NotNull final Session session) throws IOException, SQLException {

        @NotNull final ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("projects-and-tasks-by-admin.dat"));
        @NotNull final Domain domain = new Domain();

        domain.setProjects(projectService.findAll(Objects.requireNonNull(session.getUserId())));
        domain.setTasks(taskService.findAll(session.getUserId()));
        domain.setUsers(Objects.requireNonNull(userRepository.findAll()));
        oos.writeObject(domain);
    }


    public void saveDataByFasterXmlJson(@NotNull final Session session) throws IOException, SQLException {

        @NotNull final Domain domain = new Domain();
        domain.setProjects(projectService.findAll(Objects.requireNonNull(session.getUserId())));
        domain.setTasks(taskService.findAll(session.getUserId()));
        domain.setUsers(Objects.requireNonNull(userRepository.findAll()));

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.writeValue(new File("projects-and-tasks-by-admin.json"), domain);
    }

    public void saveDataByFasterXml(@NotNull final Session session) throws IOException, SQLException {

        @NotNull final Domain domain = new Domain();

        domain.setProjects(projectService.findAll(Objects.requireNonNull(session.getUserId())));
        domain.setTasks(taskService.findAll(session.getUserId()));
        domain.setUsers(Objects.requireNonNull(userRepository.findAll()));
        @NotNull final XmlMapper xmlMapper = new XmlMapper();
        xmlMapper.writeValue(new File("projects-and-tasks-by-admin.xml"), domain);
    }

    public void saveDataByJaxbJson(@NotNull final Session session) throws JAXBException, IOException, SQLException {

        System.setProperty("javax.xml.bind.context.factory","org.eclipse.persistence.jaxb.JAXBContextFactory");
        @NotNull final Domain domain = new Domain();
        domain.setProjects(projectService.findAll(Objects.requireNonNull(session.getUserId())));
        domain.setTasks(taskService.findAll(session.getUserId()));
        domain.setUsers(Objects.requireNonNull(userRepository.findAll()));
        @NotNull final JAXBContext jaxbContext = JAXBContext.newInstance(Domain.class);
        @NotNull final Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty("eclipselink.media-type", "application/json");

        marshaller.marshal(domain,  new FileWriter("projects-and-tasks-by-admin.json"));
    }

    public void saveDataByJaxbXml(@NotNull final Session session) throws IOException, JAXBException, SQLException {

        @NotNull final Domain domain = new Domain();

        domain.setProjects(projectService.findAll(Objects.requireNonNull(session.getUserId())));
        domain.setTasks(taskService.findAll(session.getUserId()));
        domain.setUsers(Objects.requireNonNull(userRepository.findAll()));
        @NotNull final JAXBContext context = JAXBContext.newInstance(Domain.class);
        @NotNull final Marshaller m = context.createMarshaller();
        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        m.marshal(domain, new FileWriter("projects-and-tasks-by-admin.xml"));
    }

    public void loadDataByDefault(@NotNull final Session session) throws IOException, ClassNotFoundException {

        @NotNull final FileInputStream fileInputStream = new FileInputStream("C:\\Users\\d.burmistrov\\IdeaProjects\\toDoList\\" + "projects-and-tasks-by-admin.dat");
        @NotNull final ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

        @NotNull final Domain domain = (Domain) objectInputStream.readObject();

        for (@NotNull final Project project : domain.getProjects()) {
            projectRepository.persist(project.getId(), Objects.requireNonNull(project.getUserId()), project.getDateBegin(),
                    Objects.requireNonNull(project.getDateEnd()), Objects.requireNonNull(project.getDescription()),
                    Objects.requireNonNull(project.getName()), Objects.requireNonNull(project.getStatus()));
        }
        for (@NotNull final Task task : domain.getTasks()) {
            taskRepository.persist(task.getId() ,Objects.requireNonNull(task.getUserId()), Objects.requireNonNull(task.getProjectId()),
                    task.getDateBegin(), Objects.requireNonNull(task.getDateEnd()), Objects.requireNonNull(task.getDescription()),
                    Objects.requireNonNull(task.getName()), Objects.requireNonNull(task.getStatus()));
        }
        for (@NotNull final User user : domain.getUsers()) {
            userRepository.persist(user.getId(), Objects.requireNonNull(user.getEmail()), Objects.requireNonNull(user.getFirstName()),
                    Objects.requireNonNull(user.getLastName()), Objects.requireNonNull(user.getLogin()),
                    Objects.requireNonNull(user.getMiddleName()), Objects.requireNonNull(user.getPassword()), Objects.requireNonNull(user.getRole()));
        }
    }

    public void loadDataByFasterXmlJson(@NotNull final Session session) throws IOException {

        @NotNull final File file = new File("projects-and-tasks-by-admin.json");
        @NotNull final ObjectMapper objectMapper = new ObjectMapper();
        @NotNull final Domain domain = objectMapper.readValue(file, Domain.class);

        for (@NotNull final Project project : domain.getProjects()) {
            projectRepository.persist(project.getId(), Objects.requireNonNull(project.getUserId()), project.getDateBegin(),
                    Objects.requireNonNull(project.getDateEnd()), Objects.requireNonNull(project.getDescription()),
                    Objects.requireNonNull(project.getName()), Objects.requireNonNull(project.getStatus()));
        }
        for (@NotNull final Task task : domain.getTasks()) {
            taskRepository.persist(task.getId() ,Objects.requireNonNull(task.getUserId()), Objects.requireNonNull(task.getProjectId()),
                    task.getDateBegin(), Objects.requireNonNull(task.getDateEnd()), Objects.requireNonNull(task.getDescription()),
                    Objects.requireNonNull(task.getName()), Objects.requireNonNull(task.getStatus()));
        }
        for (@NotNull final User user : domain.getUsers()) {
            userRepository.persist(user.getId(), Objects.requireNonNull(user.getEmail()), Objects.requireNonNull(user.getFirstName()),
                    Objects.requireNonNull(user.getLastName()), Objects.requireNonNull(user.getLogin()),
                    Objects.requireNonNull(user.getMiddleName()), Objects.requireNonNull(user.getPassword()), Objects.requireNonNull(user.getRole()));
        }
    }

    public void loadDataByFasterXml(@NotNull final Session session) throws IOException {

        @NotNull final File file = new File("projects-and-tasks-by-admin.xml");
        @NotNull final XmlMapper xmlMapper = new XmlMapper();
        @NotNull final Domain domain = xmlMapper.readValue(file, Domain.class);

        for (@NotNull final Project project : domain.getProjects()) {
            projectRepository.persist(project.getId(), Objects.requireNonNull(project.getUserId()), project.getDateBegin(),
                    Objects.requireNonNull(project.getDateEnd()), Objects.requireNonNull(project.getDescription()),
                    Objects.requireNonNull(project.getName()), Objects.requireNonNull(project.getStatus()));
        }
        for (@NotNull final Task task : domain.getTasks()) {
            taskRepository.persist(task.getId() ,Objects.requireNonNull(task.getUserId()), Objects.requireNonNull(task.getProjectId()),
                    task.getDateBegin(), Objects.requireNonNull(task.getDateEnd()), Objects.requireNonNull(task.getDescription()),
                    Objects.requireNonNull(task.getName()), Objects.requireNonNull(task.getStatus()));
        }
        for (@NotNull final User user : domain.getUsers()) {
            userRepository.persist(user.getId(), Objects.requireNonNull(user.getEmail()), Objects.requireNonNull(user.getFirstName()),
                    Objects.requireNonNull(user.getLastName()), Objects.requireNonNull(user.getLogin()),
                    Objects.requireNonNull(user.getMiddleName()), Objects.requireNonNull(user.getPassword()), Objects.requireNonNull(user.getRole()));
        }
    }

    public void loadDataByJaxbJson(@NotNull final Session session) throws JAXBException {

        System.setProperty("javax.xml.bind.context.factory", "org.eclipse.persistence.jaxb.JAXBContextFactory");

        @NotNull final JAXBContext jaxbContext = JAXBContext.newInstance(Domain.class);
        @NotNull final Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

        unmarshaller.setProperty(UnmarshallerProperties.MEDIA_TYPE, "application/json");
        unmarshaller.setProperty(UnmarshallerProperties.JSON_INCLUDE_ROOT, true);
        @NotNull final Domain domain = (Domain) unmarshaller.unmarshal(new File("C:\\Users\\d.burmistrov\\IdeaProjects\\toDoList\\projects-and-tasks-by-admin.json"));

        for (@NotNull final Project project : domain.getProjects()) {
            projectRepository.persist(project.getId(), Objects.requireNonNull(project.getUserId()), project.getDateBegin(),
                    Objects.requireNonNull(project.getDateEnd()), Objects.requireNonNull(project.getDescription()),
                    Objects.requireNonNull(project.getName()), Objects.requireNonNull(project.getStatus()));
        }
        for (@NotNull final Task task : domain.getTasks()) {
            taskRepository.persist(task.getId() ,Objects.requireNonNull(task.getUserId()), Objects.requireNonNull(task.getProjectId()),
                    task.getDateBegin(), Objects.requireNonNull(task.getDateEnd()), Objects.requireNonNull(task.getDescription()),
                    Objects.requireNonNull(task.getName()), Objects.requireNonNull(task.getStatus()));
        }
        for (@NotNull final User user : domain.getUsers()) {
            userRepository.persist(user.getId(), Objects.requireNonNull(user.getEmail()), Objects.requireNonNull(user.getFirstName()),
                    Objects.requireNonNull(user.getLastName()), Objects.requireNonNull(user.getLogin()),
                    Objects.requireNonNull(user.getMiddleName()), Objects.requireNonNull(user.getPassword()), Objects.requireNonNull(user.getRole()));
        }
    }

    public void loadDataByJaxbXml(@NotNull final Session session) throws JAXBException {

        @NotNull final File file = new File("projects-and-tasks-by-admin.xml");
        @NotNull final JAXBContext jaxbContext = JAXBContext.newInstance(Domain.class);
        @NotNull final Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        @NotNull final Domain domain = (Domain) unmarshaller.unmarshal(file);

        for (@NotNull final Project project : domain.getProjects()) {
            projectRepository.persist(project.getId(), Objects.requireNonNull(project.getUserId()), project.getDateBegin(),
                    Objects.requireNonNull(project.getDateEnd()), Objects.requireNonNull(project.getDescription()),
                    Objects.requireNonNull(project.getName()), Objects.requireNonNull(project.getStatus()));
        }
        for (@NotNull final Task task : domain.getTasks()) {
            taskRepository.persist(task.getId() ,Objects.requireNonNull(task.getUserId()), Objects.requireNonNull(task.getProjectId()),
                    task.getDateBegin(), Objects.requireNonNull(task.getDateEnd()), Objects.requireNonNull(task.getDescription()),
                    Objects.requireNonNull(task.getName()), Objects.requireNonNull(task.getStatus()));
        }
        for (@NotNull final User user : domain.getUsers()) {
            userRepository.persist(user.getId(), Objects.requireNonNull(user.getEmail()), Objects.requireNonNull(user.getFirstName()),
                    Objects.requireNonNull(user.getLastName()), Objects.requireNonNull(user.getLogin()),
                    Objects.requireNonNull(user.getMiddleName()), Objects.requireNonNull(user.getPassword()), Objects.requireNonNull(user.getRole()));
        }
    }

    @Override
    @Nullable
    public User createUser(@NotNull final String login, @NotNull final String password, @NotNull final String firstName,
                           @NotNull final String middleName, final @NotNull String lastName, final @NotNull String email,
                           @Nullable Role roleType) throws NoSuchAlgorithmException {

        @Nullable final User abstractEntity = userRepository.findOneByLogin(login);
        if(abstractEntity == null) {
            @NotNull final User user = new User();
            user.setLogin(login);
            user.setHashPassword(password);
            user.setFirstName(firstName);
            user.setMiddleName(middleName);
            user.setLastName(lastName);
            user.setEmail(email);
            user.setRole(roleType);

            Objects.requireNonNull(userRepository).persist(user.getId(), email, firstName, lastName, login, middleName,
                    Objects.requireNonNull(user.getPassword()), Objects.requireNonNull(user.getRole()));
            Objects.requireNonNull(session).commit();
            return user;
        }

        return null;
    }

    @Override
    public void updatePassword(@NotNull final String login, @NotNull final String password) throws NoSuchAlgorithmException {
        if (password.length() > 0) {
            Objects.requireNonNull(userRepository).updatePassword(login, PasswordUtil.hashPassword(password));
            Objects.requireNonNull(session).commit();
        }
    }

    @Override
    public void updateUserById(@NotNull final String userId,  @NotNull final String firstName, @NotNull final String middleName,
                               @NotNull final String lastName, final @NotNull String email,final @NotNull Role role) {
        @NotNull final User currentUser = new User();
        currentUser.setFirstName(firstName);
        currentUser.setMiddleName(middleName);
        currentUser.setLastName(lastName);
        currentUser.setEmail(email);
        currentUser.setId(userId);
        currentUser.setRole(role);
        @Nullable final AbstractEntity abstractEntity = userRepository.findOne(userId);
        if (abstractEntity != null) {
            Objects.requireNonNull(userRepository).merge(currentUser);
            Objects.requireNonNull(session).commit();
        }
    }

    @Override
    public void removeUserById(@NotNull final String userId) {
        Objects.requireNonNull(userRepository).remove(Objects.requireNonNull(userId));
        Objects.requireNonNull(session).commit();
    }

    @Override
    public void removeAllUsers() {

        Objects.requireNonNull(userRepository).removeAll();
        Objects.requireNonNull(session).commit();
    }

    @NotNull
    public List<User> findAll() {
        return Objects.requireNonNull(userRepository).findAll();
    }

    @Nullable
    public User findOne(@NotNull final String id) {
        return Objects.requireNonNull(userRepository).findOne(id);
    }

    @Nullable
    public User findOneByLogin(@NotNull final String login) {
        return Objects.requireNonNull(userRepository).findOneByLogin(login);
    }
}
