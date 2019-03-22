package ru.burmistrov.tm.service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
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

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.*;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.Objects;

public class AdminService implements IAdminService {

    private final IProjectService projectService;

    private final ITaskService taskService;

    private final IProjectRepository projectRepository;

    private final ITaskRepository taskRepository;

    private final IUserRepository userRepository;

    public AdminService(@NotNull final IProjectService projectService, @NotNull final ITaskService taskService, @NotNull final IProjectRepository projectRepository,
                        @NotNull final ITaskRepository taskRepository, @NotNull final IUserRepository userRepository) {
        this.projectService = projectService;
        this.taskService = taskService;
        this.projectRepository = projectRepository;
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
    }

    public void saveDataByDefault(@NotNull final Session session) throws IOException, SQLException {

        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("projects-and-tasks-by-admin.dat"));
        Domain domain = new Domain();

        domain.setProjects(projectService.findAll(Objects.requireNonNull(session.getUserId())));
        domain.setTasks(taskService.findAll(session.getUserId()));
        domain.setUsers(Objects.requireNonNull(userRepository.findAll(new User())));
        oos.writeObject(domain);
    }


    public void saveDataByFasterXmlJson(@NotNull final Session session) throws IOException, SQLException {

        User user = new User();
        user.setId(session.getUserId());
        Domain domain = new Domain();
        domain.setProjects(projectService.findAll(Objects.requireNonNull(session.getUserId())));
        domain.setTasks(taskService.findAll(session.getUserId()));
        domain.setUsers(Objects.requireNonNull(userRepository.findAll(user)));

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.writeValue(new File("projects-and-tasks-by-admin.json"), domain);
    }

    public void saveDataByFasterXml(@NotNull final Session session) throws IOException, SQLException {

        Domain domain = new Domain();

        domain.setProjects(projectService.findAll(Objects.requireNonNull(session.getUserId())));
        domain.setTasks(taskService.findAll(session.getUserId()));
        domain.setUsers(Objects.requireNonNull(userRepository.findAll(new User())));
        XmlMapper xmlMapper = new XmlMapper();
        xmlMapper.writeValue(new File("projects-and-tasks-by-admin.xml"), domain);
    }

    public void saveDataByJaxbJson(@NotNull final Session session) throws JAXBException, IOException, SQLException {

        System.setProperty("javax.xml.bind.context.factory","org.eclipse.persistence.jaxb.JAXBContextFactory");
        Domain domain = new Domain();
        domain.setProjects(projectService.findAll(Objects.requireNonNull(session.getUserId())));
        domain.setTasks(taskService.findAll(session.getUserId()));
        domain.setUsers(Objects.requireNonNull(userRepository.findAll(new User())));
        JAXBContext jaxbContext = JAXBContext.newInstance(Domain.class);
        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty("eclipselink.media-type", "application/json");

        marshaller.marshal(domain,  new FileWriter("projects-and-tasks-by-admin.json"));
    }

    public void saveDataByJaxbXml(@NotNull final Session session) throws IOException, JAXBException, SQLException {

        Domain domain = new Domain();

        domain.setProjects(projectService.findAll(Objects.requireNonNull(session.getUserId())));
        domain.setTasks(taskService.findAll(session.getUserId()));
        domain.setUsers(Objects.requireNonNull(userRepository.findAll(new User())));
        JAXBContext context = JAXBContext.newInstance(Domain.class);
        Marshaller m = context.createMarshaller();
        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        m.marshal(domain, new FileWriter("projects-and-tasks-by-admin.xml"));
    }

    public void loadDataByDefault(@NotNull final Session session) throws IOException, ClassNotFoundException, NoSuchAlgorithmException, SQLException {

        FileInputStream fileInputStream = new FileInputStream("C:\\Users\\d.burmistrov\\IdeaProjects\\toDoList\\" + "projects-and-tasks-by-admin.dat");
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

        Domain domain = (Domain) objectInputStream.readObject();

        for (Project project : domain.getProjects()) {
            projectRepository.persist(project);
        }
        for (Task task : domain.getTasks()) {
            taskRepository.persist(task);
        }
        for (User user : domain.getUsers()) {
            userRepository.persist(user);
        }
    }

    public void loadDataByFasterXmlJson(@NotNull final Session session) throws IOException, NoSuchAlgorithmException, SQLException {

        File file = new File("projects-and-tasks-by-admin.json");
        ObjectMapper objectMapper = new ObjectMapper();
        Domain domain = objectMapper.readValue(file, Domain.class);

        for (Project project : domain.getProjects()) {
            projectRepository.persist(project);
        }
        for (Task task : domain.getTasks()) {
            taskRepository.persist(task);
        }
        for (User user : domain.getUsers()) {
            userRepository.persist(user);
        }
    }

    public void loadDataByFasterXml(@NotNull final Session session) throws IOException, NoSuchAlgorithmException, SQLException {

        File file = new File("projects-and-tasks-by-admin.xml");
        XmlMapper xmlMapper = new XmlMapper();
        Domain domain = xmlMapper.readValue(file, Domain.class);

        for (Project project : domain.getProjects()) {
            projectRepository.persist(project);
        }
        for (Task task : domain.getTasks()) {
            taskRepository.persist(task);
        }
        for (User user : domain.getUsers()) {
            userRepository.persist(user);
        }
    }

    public void loadDataByJaxbJson(@NotNull final Session session) throws JAXBException, IOException, NoSuchAlgorithmException, SQLException {

        System.setProperty("javax.xml.bind.context.factory", "org.eclipse.persistence.jaxb.JAXBContextFactory");

        JAXBContext jaxbContext = JAXBContext.newInstance(Domain.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

        //Set JSON type
        unmarshaller.setProperty(UnmarshallerProperties.MEDIA_TYPE, "application/json");
        unmarshaller.setProperty(UnmarshallerProperties.JSON_INCLUDE_ROOT, true);
        Domain domain = (Domain) unmarshaller.unmarshal(new File("C:\\Users\\d.burmistrov\\IdeaProjects\\toDoList\\projects-and-tasks-by-admin.json"));

        for (Project project : domain.getProjects()) {
            projectRepository.persist(project);
        }
        for (Task task : domain.getTasks()) {
            taskRepository.persist(task);
        }
        for (User user : domain.getUsers()) {
            userRepository.persist(user);
        }
    }

    public void loadDataByJaxbXml(@NotNull final Session session) throws JAXBException, IOException, NoSuchAlgorithmException, SQLException {

        File file = new File("projects-and-tasks-by-admin.xml");
        JAXBContext jaxbContext = JAXBContext.newInstance(Domain.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        Domain domain = (Domain) unmarshaller.unmarshal(file);

        for (Project project : domain.getProjects()) {
            projectRepository.persist(project);
        }
        for (Task task : domain.getTasks()) {
            taskRepository.persist(task);
        }
        for (User user : domain.getUsers()) {
            userRepository.persist(user);
        }
    }

    @Override
    @Nullable
    public User createUser(@NotNull final String login, @NotNull final String password, @NotNull final String firstName,
                           @NotNull final String middleName, final @NotNull String lastName, final @NotNull String email,
                           @Nullable Role roleType) throws NoSuchAlgorithmException, IOException, SQLException {

        @NotNull final User user = new User();
        user.setRole(roleType);
        user.setLogin(login);
        user.setPassword(password);
        user.setFirstName(firstName);
        user.setMiddleName(middleName);
        user.setLastName(lastName);
        user.setEmail(email);
        @Nullable final User abstractEntity = userRepository.findOne(user);
        if(abstractEntity == null)
            return userRepository.persist(user);

        return null;
    }

    @Override
    public void updatePassword(@NotNull final String userId, @NotNull final String login, @NotNull final String password) throws NoSuchAlgorithmException, SQLException {
        if (password.length() > 0) {
            userRepository.updatePassword(userId, login, password);
        }
    }

    @Override
    public void updateUserById(@NotNull final String userId, @NotNull final String firstName, @NotNull final String middleName,
                               @NotNull final String lastName, final @NotNull String email,final @NotNull Role role) throws SQLException {
        @NotNull final User currentUser = new User();
        currentUser.setFirstName(firstName);
        currentUser.setMiddleName(middleName);
        currentUser.setLastName(lastName);
        currentUser.setEmail(email);
        currentUser.setId(userId);
        currentUser.setRole(role);
        AbstractEntity abstractEntity = userRepository.findOne(currentUser);
        if(abstractEntity != null)
            userRepository.merge(currentUser);
    }

    @Override
    public void removeUserById(@NotNull final String userId) throws SQLException {
        @NotNull final User user = new User();
        user.setId(userId);
        userRepository.remove(user);
    }

    @Override
    public void removeAllUsers(@Nullable final String userId) throws SQLException {
        @NotNull final User user = new User();
        user.setId(userId);
        userRepository.removeAll(user);
    }

}
