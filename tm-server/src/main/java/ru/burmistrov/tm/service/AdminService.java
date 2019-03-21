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
import java.util.Objects;

public class AdminService implements IAdminService {

    private IProjectService projectService;

    private ITaskService taskService;

    private IProjectRepository projectRepository;

    private ITaskRepository taskRepository;

    private IUserRepository userRepository;

    public AdminService(IProjectService projectService, ITaskService taskService, IProjectRepository projectRepository, ITaskRepository taskRepository, IUserRepository userRepository) {
        this.projectService = projectService;
        this.taskService = taskService;
        this.projectRepository = projectRepository;
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
    }

    public void saveDataByDefault(@NotNull Session session) throws IOException {

        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("projects-and-tasks-by-admin.dat"));
        Domain domain = new Domain();

        domain.setProjects(projectService.findAll(Objects.requireNonNull(session.getUserId())));
        domain.setTasks(taskService.findAll(session.getUserId()));
        domain.setUsers(Objects.requireNonNull(userRepository.findAll(new User())));
        oos.writeObject(domain);
    }


    public void saveDataByFasterXmlJson(@NotNull Session session) throws IOException {

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

    public void saveDataByFasterXml(@NotNull Session session) throws IOException {

        Domain domain = new Domain();

        domain.setProjects(projectService.findAll(Objects.requireNonNull(session.getUserId())));
        domain.setTasks(taskService.findAll(session.getUserId()));
        domain.setUsers(Objects.requireNonNull(userRepository.findAll(new User())));
        XmlMapper xmlMapper = new XmlMapper();
        xmlMapper.writeValue(new File("projects-and-tasks-by-admin.xml"), domain);
    }

    public void saveDataByJaxbJson(@NotNull Session session) throws JAXBException, IOException {

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

    public void saveDataByJaxbXml(@NotNull Session session) throws IOException, JAXBException {

        Domain domain = new Domain();

        domain.setProjects(projectService.findAll(Objects.requireNonNull(session.getUserId())));
        domain.setTasks(taskService.findAll(session.getUserId()));
        domain.setUsers(Objects.requireNonNull(userRepository.findAll(new User())));
        JAXBContext context = JAXBContext.newInstance(Domain.class);
        Marshaller m = context.createMarshaller();
        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        m.marshal(domain, new FileWriter("projects-and-tasks-by-admin.xml"));
    }

    public void loadDataByDefault(@NotNull Session session) throws IOException, ClassNotFoundException {

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

    public void loadDataByFasterXmlJson(@NotNull Session session) throws IOException {

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

    public void loadDataByFasterXml(@NotNull Session session) throws IOException {

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

    public void loadDataByJaxbJson(@NotNull Session session) throws JAXBException, IOException {

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

    public void loadDataByJaxbXml(@NotNull Session session) throws JAXBException, IOException {

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
    public User createUser(@NotNull String login, @NotNull String password, @NotNull String firstName, @NotNull String middleName, @NotNull String lastName,
                           @NotNull String email, @NotNull Role roleType) {

        @NotNull final User user = new User();
        user.setRole(roleType);
        user.setLogin(login);
        user.setPassword(password);
        user.setFirstName(firstName);
        user.setMiddleName(middleName);
        user.setLastName(lastName);
        user.setEmail(email);
        User abstractEntity = userRepository.findOne(user);
        if(abstractEntity == null)
            return userRepository.persist(user);

        return null;
    }

    @Override
    public void updatePassword(@NotNull String userId, @NotNull String login, @NotNull String password) {
        if (password.length() > 0) {
            userRepository.updatePassword(userId, login, password);
        }
    }

    @Override
    public void updateUserById(@NotNull String userId, @NotNull String firstName, @NotNull String middleName, @NotNull String lastName, @NotNull String email,
                               @NotNull Role role) {
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
    public void removeUserById(@NotNull String userId) {
        @NotNull final User user = new User();
        user.setId(userId);
        userRepository.remove(user);
    }

    @Override
    public void removeAllUsers(@Nullable String userId) {
        @NotNull final User user = new User();
        user.setId(userId);
        userRepository.removeAll(user);
    }

}
