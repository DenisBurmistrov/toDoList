package ru.burmistrov.tm.service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import lombok.NoArgsConstructor;
import org.apache.deltaspike.jpa.api.transaction.Transactional;
import org.eclipse.persistence.jaxb.UnmarshallerProperties;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.burmistrov.tm.repository.IProjectRepository;
import ru.burmistrov.tm.repository.ITaskRepository;
import ru.burmistrov.tm.repository.IUserRepository;
import ru.burmistrov.tm.api.service.IAdminService;
import ru.burmistrov.tm.api.service.IProjectService;
import ru.burmistrov.tm.api.service.ITaskService;
import ru.burmistrov.tm.dto.Domain;
import ru.burmistrov.tm.entity.*;
import ru.burmistrov.tm.entity.enumerated.Role;
import ru.burmistrov.tm.util.PasswordUtil;

import javax.inject.Inject;
import javax.persistence.NoResultException;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.*;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

@NoArgsConstructor
@Transactional
public class AdminService implements IAdminService {

    @Inject
    private IProjectService projectService;

    @Inject
    private ITaskService taskService;

    @Inject
    private IProjectRepository projectRepository;

    @Inject
    private ITaskRepository taskRepository;

    @Inject
    private IUserRepository userRepository;

    public void saveDataByDefault(@NotNull final Session session) throws IOException, SQLException {
        @NotNull final ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("projects-and-tasks-by-admin.dat"));
        @NotNull final Domain domain = new Domain();
        domain.setProjects(Objects.requireNonNull(projectService.findAll(Objects.requireNonNull(session.getUserId()))));
        domain.setTasks(Objects.requireNonNull(taskService.findAll(session.getUserId())));
        domain.setUsers(Objects.requireNonNull(userRepository.findAll()));
        oos.writeObject(domain);
    }

    public void saveDataByFasterXmlJson(@NotNull final Session session) throws IOException, SQLException {
        @NotNull final Domain domain = new Domain();
        domain.setProjects(projectService.findAll(Objects.requireNonNull(session.getUserId())));
        domain.setTasks(Objects.requireNonNull(taskService.findAll(session.getUserId())));
        domain.setUsers(Objects.requireNonNull(userRepository.findAll()));
        @NotNull final ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.writeValue(new File("projects-and-tasks-by-admin.json"), domain);
    }

    public void saveDataByFasterXml(@NotNull final Session session) throws IOException, SQLException {
        @NotNull final Domain domain = new Domain();
        domain.setProjects(Objects.requireNonNull(projectService.findAll(Objects.requireNonNull(session.getUserId()))));
        domain.setTasks(Objects.requireNonNull(taskService.findAll(session.getUserId())));
        domain.setUsers(Objects.requireNonNull(userRepository.findAll()));
        @NotNull final XmlMapper xmlMapper = new XmlMapper();
        xmlMapper.writeValue(new File("projects-and-tasks-by-admin.xml"), domain);
    }

    public void saveDataByJaxbJson(@NotNull final Session session) throws SQLException, JAXBException, IOException {
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
    public User createUser
            (@NotNull final String login, @NotNull final String password, @NotNull final String firstName,
             @NotNull final String middleName, final @NotNull String lastName, final @NotNull String email,
             @Nullable Role roleType) throws NoSuchAlgorithmException {
        try {
            userRepository.findOneByLogin(login);
        } catch (NoResultException e) {
            @NotNull final User user = new User();
            user.setLogin(login);
            user.setHashPassword(password);
            user.setFirstName(firstName);
            user.setMiddleName(middleName);
            user.setLastName(lastName);
            user.setEmail(email);
            user.setRole(roleType);
            Objects.requireNonNull(userRepository).persist(user);
            return user;
        }
        return null;
    }

    @Override
    public void updatePassword(@NotNull final String login, @NotNull final String password) throws NoSuchAlgorithmException {
        if (password.length() > 0) {
            Objects.requireNonNull(userRepository).updatePassword(login, PasswordUtil.hashPassword(password));
        }
    }

    @Override
    public void updateUserById
            (@NotNull final String userId, @NotNull final String firstName, @NotNull final String middleName,
             @NotNull final String lastName, final @NotNull String email, final @NotNull Role role) {
        @NotNull final User currentUser = new User();
        currentUser.setFirstName(firstName);
        currentUser.setMiddleName(middleName);
        currentUser.setLastName(lastName);
        currentUser.setEmail(email);
        currentUser.setId(userId);
        currentUser.setRole(role);
        try {
            @Nullable final AbstractEntity abstractEntity = userRepository.findOne(userId);
            if (abstractEntity != null) {
                Objects.requireNonNull(userRepository).merge(currentUser);
            }
        } catch (NoResultException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeUserById(@NotNull final String userId) {
        Objects.requireNonNull(userRepository).remove(Objects.requireNonNull(userId));
    }

    @Override
    public void removeAllUsers() {
        Objects.requireNonNull(userRepository).removeAll();
    }

    @Nullable
    public List<User> findAll() {
        return Objects.requireNonNull(userRepository).findAll();
    }

    @Nullable
    public User findOne(@NotNull final String id) {
        try {
            return Objects.requireNonNull(userRepository).findOne(id);
        } catch (NoResultException e) {
            return null;
        }
    }

    @Nullable
    public User findOneByLogin(@NotNull final String login) {
        try {
            return Objects.requireNonNull(userRepository).findOneByLogin(login);
        } catch (NoResultException e) {
            return null;
        }
    }
}
