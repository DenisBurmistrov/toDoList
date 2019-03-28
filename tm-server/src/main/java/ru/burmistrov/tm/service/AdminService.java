package ru.burmistrov.tm.service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.eclipse.persistence.jaxb.UnmarshallerProperties;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.burmistrov.tm.repository.ProjectRepository;
import ru.burmistrov.tm.repository.TaskRepository;
import ru.burmistrov.tm.repository.UserRepository;
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
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

public class AdminService implements IAdminService {

    @NotNull
    private final SqlSessionFactory sqlSessionFactory;

    @NotNull
    private final IProjectService projectService;

    @NotNull
    private final ITaskService taskService;

    @NotNull
    private ProjectRepository projectRepository;

    @NotNull
    private TaskRepository taskRepository;

    @NotNull
    private UserRepository userRepository;


    public AdminService(@NotNull final IProjectService projectService, @NotNull final ITaskService taskService, @NotNull final SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
        this.projectService = projectService;
        this.taskService = taskService;
    }

    public void saveDataByDefault(@NotNull final Session session) {
        try (SqlSession sqlsession = sqlSessionFactory.openSession()) {
            try {
                userRepository = sqlsession.getMapper(UserRepository.class);
                @NotNull final ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("projects-and-tasks-by-admin.dat"));
                @NotNull final Domain domain = new Domain();

                domain.setProjects(projectService.findAll(Objects.requireNonNull(session.getUserId())));
                domain.setTasks(taskService.findAll(session.getUserId()));
                domain.setUsers(Objects.requireNonNull(userRepository.findAll()));
                oos.writeObject(domain);
            } catch (Exception e) {
                sqlsession.rollback();
            }
        }
    }


    public void saveDataByFasterXmlJson(@NotNull final Session session) throws IOException, SQLException {
        try (SqlSession sqlsession = sqlSessionFactory.openSession()) {
            try {
                userRepository = sqlsession.getMapper(UserRepository.class);
                @NotNull final Domain domain = new Domain();
                domain.setProjects(projectService.findAll(Objects.requireNonNull(session.getUserId())));
                domain.setTasks(taskService.findAll(session.getUserId()));
                domain.setUsers(Objects.requireNonNull(userRepository.findAll()));

                ObjectMapper mapper = new ObjectMapper();
                mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
                mapper.writeValue(new File("projects-and-tasks-by-admin.json"), domain);
            } catch (Exception e) {
                sqlsession.rollback();
            }
        }
    }

    public void saveDataByFasterXml(@NotNull final Session session) throws IOException, SQLException {

        try (SqlSession sqlsession = sqlSessionFactory.openSession()) {
            try {
                userRepository = sqlsession.getMapper(UserRepository.class);
                @NotNull final Domain domain = new Domain();

                domain.setProjects(projectService.findAll(Objects.requireNonNull(session.getUserId())));
                domain.setTasks(taskService.findAll(session.getUserId()));
                domain.setUsers(Objects.requireNonNull(userRepository.findAll()));
                @NotNull final XmlMapper xmlMapper = new XmlMapper();
                xmlMapper.writeValue(new File("projects-and-tasks-by-admin.xml"), domain);
            } catch (Exception e) {
                sqlsession.rollback();
            }
        }
    }

    public void saveDataByJaxbJson(@NotNull final Session session) {

        try (SqlSession sqlsession = sqlSessionFactory.openSession()) {
            try {
                userRepository = sqlsession.getMapper(UserRepository.class);
                System.setProperty("javax.xml.bind.context.factory", "org.eclipse.persistence.jaxb.JAXBContextFactory");
                @NotNull final Domain domain = new Domain();
                domain.setProjects(projectService.findAll(Objects.requireNonNull(session.getUserId())));
                domain.setTasks(taskService.findAll(session.getUserId()));
                domain.setUsers(Objects.requireNonNull(userRepository.findAll()));
                @NotNull final JAXBContext jaxbContext = JAXBContext.newInstance(Domain.class);
                @NotNull final Marshaller marshaller = jaxbContext.createMarshaller();
                marshaller.setProperty("eclipselink.media-type", "application/json");

                marshaller.marshal(domain, new FileWriter("projects-and-tasks-by-admin.json"));
            } catch (Exception e) {
                sqlsession.rollback();
            }
        }
    }

    public void saveDataByJaxbXml(@NotNull final Session session) throws IOException, JAXBException, SQLException {

        try (SqlSession sqlsession = sqlSessionFactory.openSession()) {
            try {
                userRepository = sqlsession.getMapper(UserRepository.class);
                @NotNull final Domain domain = new Domain();

                domain.setProjects(projectService.findAll(Objects.requireNonNull(session.getUserId())));
                domain.setTasks(taskService.findAll(session.getUserId()));
                domain.setUsers(Objects.requireNonNull(userRepository.findAll()));
                @NotNull final JAXBContext context = JAXBContext.newInstance(Domain.class);
                @NotNull final Marshaller m = context.createMarshaller();
                m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
                m.marshal(domain, new FileWriter("projects-and-tasks-by-admin.xml"));
            } catch (Exception e) {
                sqlsession.rollback();
            }
        }
    }

    public void loadDataByDefault(@NotNull final Session session) throws IOException, ClassNotFoundException {

        try (SqlSession sqlsession = sqlSessionFactory.openSession()) {
            try {
                userRepository = sqlsession.getMapper(UserRepository.class);
                taskRepository = sqlsession.getMapper(TaskRepository.class);
                projectRepository = sqlsession.getMapper(ProjectRepository.class);
                @NotNull final FileInputStream fileInputStream = new FileInputStream("C:\\Users\\d.burmistrov\\IdeaProjects\\toDoList\\" + "projects-and-tasks-by-admin.dat");
                @NotNull final ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

                @NotNull final Domain domain = (Domain) objectInputStream.readObject();

                for (@NotNull final Project project : domain.getProjects()) {
                    projectRepository.persist(project.getId(), Objects.requireNonNull(project.getUserId()), project.getDateBegin(),
                            Objects.requireNonNull(project.getDateEnd()), Objects.requireNonNull(project.getDescription()),
                            Objects.requireNonNull(project.getName()), Objects.requireNonNull(project.getStatus()));
                }
                for (@NotNull final Task task : domain.getTasks()) {
                    taskRepository.persist(task.getId(), Objects.requireNonNull(task.getUserId()), Objects.requireNonNull(task.getProjectId()),
                            task.getDateBegin(), Objects.requireNonNull(task.getDateEnd()), Objects.requireNonNull(task.getDescription()),
                            Objects.requireNonNull(task.getName()), Objects.requireNonNull(task.getStatus()));
                }
                for (@NotNull final User user : domain.getUsers()) {
                    userRepository.persist(user.getId(), Objects.requireNonNull(user.getEmail()), Objects.requireNonNull(user.getFirstName()),
                            Objects.requireNonNull(user.getLastName()), Objects.requireNonNull(user.getLogin()),
                            Objects.requireNonNull(user.getMiddleName()), Objects.requireNonNull(user.getPassword()), Objects.requireNonNull(user.getRole()));
                }
            } catch (Exception e) {
                sqlsession.rollback();
            }
        }
    }

    public void loadDataByFasterXmlJson(@NotNull final Session session) throws IOException {

        try (SqlSession sqlsession = sqlSessionFactory.openSession()) {
            try {
                userRepository = sqlsession.getMapper(UserRepository.class);
                taskRepository = sqlsession.getMapper(TaskRepository.class);
                projectRepository = sqlsession.getMapper(ProjectRepository.class);
                @NotNull final File file = new File("projects-and-tasks-by-admin.json");
                @NotNull final ObjectMapper objectMapper = new ObjectMapper();
                @NotNull final Domain domain = objectMapper.readValue(file, Domain.class);

                for (@NotNull final Project project : domain.getProjects()) {
                    projectRepository.persist(project.getId(), Objects.requireNonNull(project.getUserId()), project.getDateBegin(),
                            Objects.requireNonNull(project.getDateEnd()), Objects.requireNonNull(project.getDescription()),
                            Objects.requireNonNull(project.getName()), Objects.requireNonNull(project.getStatus()));
                }
                for (@NotNull final Task task : domain.getTasks()) {
                    taskRepository.persist(task.getId(), Objects.requireNonNull(task.getUserId()), Objects.requireNonNull(task.getProjectId()),
                            task.getDateBegin(), Objects.requireNonNull(task.getDateEnd()), Objects.requireNonNull(task.getDescription()),
                            Objects.requireNonNull(task.getName()), Objects.requireNonNull(task.getStatus()));
                }
                for (@NotNull final User user : domain.getUsers()) {
                    userRepository.persist(user.getId(), Objects.requireNonNull(user.getEmail()), Objects.requireNonNull(user.getFirstName()),
                            Objects.requireNonNull(user.getLastName()), Objects.requireNonNull(user.getLogin()),
                            Objects.requireNonNull(user.getMiddleName()), Objects.requireNonNull(user.getPassword()), Objects.requireNonNull(user.getRole()));
                }
            } catch (Exception e) {
                sqlsession.rollback();
            }
        }
    }

    public void loadDataByFasterXml(@NotNull final Session session) throws IOException {

        try (SqlSession sqlsession = sqlSessionFactory.openSession()) {
            try {
                userRepository = sqlsession.getMapper(UserRepository.class);
                taskRepository = sqlsession.getMapper(TaskRepository.class);
                projectRepository = sqlsession.getMapper(ProjectRepository.class);
                @NotNull final File file = new File("projects-and-tasks-by-admin.xml");
                @NotNull final XmlMapper xmlMapper = new XmlMapper();
                @NotNull final Domain domain = xmlMapper.readValue(file, Domain.class);

                for (@NotNull final Project project : domain.getProjects()) {
                    projectRepository.persist(project.getId(), Objects.requireNonNull(project.getUserId()), project.getDateBegin(),
                            Objects.requireNonNull(project.getDateEnd()), Objects.requireNonNull(project.getDescription()),
                            Objects.requireNonNull(project.getName()), Objects.requireNonNull(project.getStatus()));
                }
                for (@NotNull final Task task : domain.getTasks()) {
                    taskRepository.persist(task.getId(), Objects.requireNonNull(task.getUserId()), Objects.requireNonNull(task.getProjectId()),
                            task.getDateBegin(), Objects.requireNonNull(task.getDateEnd()), Objects.requireNonNull(task.getDescription()),
                            Objects.requireNonNull(task.getName()), Objects.requireNonNull(task.getStatus()));
                }
                for (@NotNull final User user : domain.getUsers()) {
                    userRepository.persist(user.getId(), Objects.requireNonNull(user.getEmail()), Objects.requireNonNull(user.getFirstName()),
                            Objects.requireNonNull(user.getLastName()), Objects.requireNonNull(user.getLogin()),
                            Objects.requireNonNull(user.getMiddleName()), Objects.requireNonNull(user.getPassword()), Objects.requireNonNull(user.getRole()));
                }
            } catch (Exception e) {
                sqlsession.rollback();
            }
        }
    }

    public void loadDataByJaxbJson(@NotNull final Session session) throws JAXBException {

        try (SqlSession sqlsession = sqlSessionFactory.openSession()) {
            try {
                userRepository = sqlsession.getMapper(UserRepository.class);
                taskRepository = sqlsession.getMapper(TaskRepository.class);
                projectRepository = sqlsession.getMapper(ProjectRepository.class);
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
                    taskRepository.persist(task.getId(), Objects.requireNonNull(task.getUserId()), Objects.requireNonNull(task.getProjectId()),
                            task.getDateBegin(), Objects.requireNonNull(task.getDateEnd()), Objects.requireNonNull(task.getDescription()),
                            Objects.requireNonNull(task.getName()), Objects.requireNonNull(task.getStatus()));
                }
                for (@NotNull final User user : domain.getUsers()) {
                    userRepository.persist(user.getId(), Objects.requireNonNull(user.getEmail()), Objects.requireNonNull(user.getFirstName()),
                            Objects.requireNonNull(user.getLastName()), Objects.requireNonNull(user.getLogin()),
                            Objects.requireNonNull(user.getMiddleName()), Objects.requireNonNull(user.getPassword()), Objects.requireNonNull(user.getRole()));
                }
            } catch (Exception e) {
                sqlsession.rollback();
            }
        }
    }

    public void loadDataByJaxbXml(@NotNull final Session session) throws JAXBException {

        try (SqlSession sqlsession = sqlSessionFactory.openSession()) {
            try {
                userRepository = sqlsession.getMapper(UserRepository.class);
                taskRepository = sqlsession.getMapper(TaskRepository.class);
                projectRepository = sqlsession.getMapper(ProjectRepository.class);
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
                    taskRepository.persist(task.getId(), Objects.requireNonNull(task.getUserId()), Objects.requireNonNull(task.getProjectId()),
                            task.getDateBegin(), Objects.requireNonNull(task.getDateEnd()), Objects.requireNonNull(task.getDescription()),
                            Objects.requireNonNull(task.getName()), Objects.requireNonNull(task.getStatus()));
                }
                for (@NotNull final User user : domain.getUsers()) {
                    userRepository.persist(user.getId(), Objects.requireNonNull(user.getEmail()), Objects.requireNonNull(user.getFirstName()),
                            Objects.requireNonNull(user.getLastName()), Objects.requireNonNull(user.getLogin()),
                            Objects.requireNonNull(user.getMiddleName()), Objects.requireNonNull(user.getPassword()), Objects.requireNonNull(user.getRole()));
                }
            } catch (Exception e) {
                sqlsession.rollback();
            }
        }
    }

    @Override
    @Nullable
    public User createUser(@NotNull final String login, @NotNull final String password, @NotNull final String firstName,
                           @NotNull final String middleName, final @NotNull String lastName, final @NotNull String email,
                           @Nullable Role roleType) {

        try (SqlSession sqlsession = sqlSessionFactory.openSession()) {
            try {
                userRepository = sqlsession.getMapper(UserRepository.class);
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

                    Objects.requireNonNull(userRepository).persist(user.getId(), email, firstName, lastName, login, middleName,
                            Objects.requireNonNull(user.getPassword()), Objects.requireNonNull(user.getRole()));
                    Objects.requireNonNull(sqlsession).commit();
                    return user;
                }
            } catch (Exception e) {
                sqlsession.rollback();
            }
        }
        return null;
    }

    @Override
    public void updatePassword(@NotNull final String login, @NotNull final String password) {
        try (SqlSession sqlsession = sqlSessionFactory.openSession()) {
            try {
                userRepository = sqlsession.getMapper(UserRepository.class);
                if (password.length() > 0) {
                    Objects.requireNonNull(userRepository).updatePassword(login, PasswordUtil.hashPassword(password));
                    Objects.requireNonNull(sqlsession).commit();
                }
            } catch (Exception e) {
                sqlsession.rollback();
            }
        }
    }

    @Override
    public void updateUserById(@NotNull final String userId, @NotNull final String firstName, @NotNull final String middleName,
                               @NotNull final String lastName, final @NotNull String email, final @NotNull Role role) {
        try (SqlSession sqlsession = sqlSessionFactory.openSession()) {
            try {
                userRepository = sqlsession.getMapper(UserRepository.class);
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
                    Objects.requireNonNull(sqlsession).commit();
                }
            } catch (Exception e) {
                sqlsession.rollback();
            }
        }
    }

    @Override
    public void removeUserById(@NotNull final String userId) {
        try (SqlSession sqlsession = sqlSessionFactory.openSession()) {
            try {
                userRepository = sqlsession.getMapper(UserRepository.class);
                Objects.requireNonNull(userRepository).remove(Objects.requireNonNull(userId));
                Objects.requireNonNull(sqlsession).commit();
            } catch (Exception e) {
                sqlsession.rollback();
            }
        }
    }

    @Override
    public void removeAllUsers() {
        try (SqlSession sqlsession = sqlSessionFactory.openSession()) {
            try {
                userRepository = sqlsession.getMapper(UserRepository.class);
                Objects.requireNonNull(userRepository).removeAll();
                Objects.requireNonNull(sqlsession).commit();
            } catch (Exception e) {
                sqlsession.rollback();
            }
        }
    }

    @Nullable
    public List<User> findAll() {
        try (SqlSession sqlsession = sqlSessionFactory.openSession()) {
            try {
                userRepository = sqlsession.getMapper(UserRepository.class);
                return Objects.requireNonNull(userRepository).findAll();
            } catch (Exception e) {
                sqlsession.rollback();
            }
        }
        return null;
    }

    @Nullable
    public User findOne(@NotNull final String id) {
        try (SqlSession sqlsession = sqlSessionFactory.openSession()) {
            try {
                userRepository = sqlsession.getMapper(UserRepository.class);
                return Objects.requireNonNull(userRepository).findOne(id);
            } catch (Exception e) {
                sqlsession.rollback();
            }
        }
        return null;
    }

    @Nullable
    public User findOneByLogin(@NotNull final String login) {
        try (SqlSession sqlsession = sqlSessionFactory.openSession()) {
            try {
                userRepository = sqlsession.getMapper(UserRepository.class);
                return Objects.requireNonNull(userRepository).findOneByLogin(login);
            } catch (Exception e) {
                sqlsession.rollback();
            }
        }
        return null;
    }
}
