package ru.burmistrov.tm.endpoint;

import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.burmistrov.tm.api.endpoint.IAdminEndpoint;
import ru.burmistrov.tm.api.loader.ServiceLocator;
import ru.burmistrov.tm.dto.UserDto;
import ru.burmistrov.tm.entity.enumerated.Role;
import ru.burmistrov.tm.entity.Session;
import ru.burmistrov.tm.entity.User;
import ru.burmistrov.tm.service.AdminService;
import ru.burmistrov.tm.service.SessionService;

import javax.inject.Inject;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import java.util.List;
import java.util.Objects;

@NoArgsConstructor
@WebService
public class AdminEndpoint implements IAdminEndpoint {

    @Inject
    private SessionService sessionService;

    @Inject
    private AdminService adminService;

    @WebMethod
    @Override
    public void saveDataByDefault(@WebParam(name = "session") @NotNull final Session session) throws Exception {
        if (sessionService.validateAdmin(session)) {
            adminService.saveDataByDefault(session);
        }
    }

    @WebMethod
    @Override
    public void saveDataByFasterXmlJson(@WebParam(name = "session") @NotNull final Session session) throws Exception {
        if (sessionService.validateAdmin(session)) {
            adminService.saveDataByFasterXmlJson(session);
        }
    }

    @WebMethod
    @Override
    public void saveDataByFasterXml(@NotNull final Session session) throws Exception {
        if (sessionService.validateAdmin(session)) {
            adminService.saveDataByFasterXml(session);
        }
    }

    @WebMethod
    @Override
    public void saveDataByJaxbJson(@WebParam(name = "session") @NotNull final Session session) throws Exception {
        if (sessionService.validateAdmin(session)) {
            adminService.saveDataByJaxbJson(session);
        }
    }

    @WebMethod
    @Override
    public void saveDataByJaxbXml(@WebParam(name = "session") @NotNull final Session session) throws Exception {
        if (sessionService.validateAdmin(session)) {
            adminService.saveDataByJaxbXml(session);
        }
    }

    @WebMethod
    @Override
    public void loadDataByDefault(@WebParam(name = "session") @NotNull final Session session) throws Exception {
        if (sessionService.validateAdmin(session)) {
            adminService.loadDataByDefault(session);
        }
    }

    @WebMethod
    @Override
    public void loadDataByFasterXmlJson(@WebParam(name = "session") @NotNull final Session session) throws Exception {
        if (sessionService.validateAdmin(session)) {
            adminService.loadDataByFasterXmlJson(session);
        }
    }

    @WebMethod
    @Override
    public void loadDataByFasterXml(@WebParam(name = "session") @NotNull final Session session) throws Exception {
        if (sessionService.validateAdmin(session)) {
            adminService.loadDataByFasterXml(session);
        }
    }

    @WebMethod
    @Override
    public void loadDataByJaxbJson(@WebParam(name = "session") @NotNull final Session session) throws Exception {
        if (sessionService.validateAdmin(session)) {
            adminService.loadDataByJaxbJson(session);
        }
    }

    @WebMethod
    @Override
    public void loadDataByJaxbXml(@WebParam(name = "session") @NotNull final Session session) throws Exception {
        if (sessionService.validateAdmin(session)) {
            adminService.loadDataByJaxbXml(session);
        }
    }

    @WebMethod
    @Nullable
    public UserDto createUser
            (@WebParam(name = "session") @NotNull final Session session, @WebParam(name = "login") @NotNull final String login,
             @WebParam(name = "password") @NotNull final String password, @WebParam(name = "firstName") @NotNull final String firstName,
             @WebParam(name = "middleName") @NotNull final String middleName, @WebParam(name = "lastName") @NotNull final String lastName,
             @WebParam(name = "email") @NotNull final String email, @WebParam(name = "roleType") @NotNull final Role roleType) throws Exception {
        if (sessionService.validateAdmin(session)) {
            User user = adminService.createUser(login, password, firstName, middleName, lastName, email, roleType);

            UserDto userDto = new UserDto();
            userDto.setId(Objects.requireNonNull(user).getId());
            userDto.setLogin(user.getLogin());
            userDto.setFirstName(user.getFirstName());
            userDto.setMiddleName(user.getMiddleName());
            userDto.setLastName(user.getLastName());
            userDto.setEmail(user.getEmail());
            userDto.setRole(user.getRole());

            return userDto;
        }
        return null;
    }

    @WebMethod
    public void updatePasswordById
            (@WebParam(name = "session") @NotNull final Session session, @WebParam(name = "userId") @NotNull final String userId,
             @WebParam(name = "login") @NotNull final String login, @WebParam(name = "password") @NotNull final String password) throws Exception {
        if (sessionService.validateAdmin(session)) {
            adminService.updatePassword(login, password);
        }
    }

    @WebMethod
    public void removeUserById
            (@WebParam(name = "session") @NotNull final Session session, @WebParam(name = "userId") @NotNull final String userId) throws Exception {
        if (sessionService.validateAdmin(session)) {
            adminService.removeUserById(userId);
        }
    }

    @WebMethod
    public void removeAllUsers
            (@WebParam(name = "session") @NotNull final Session session, @WebParam(name = "userId") @NotNull final String userId) throws Exception {
        if (sessionService.validateAdmin(session)) {
            adminService.removeAllUsers();
        }
    }

    @WebMethod
    @Override
    public void updateUserById
            (@WebParam(name = "session") @NotNull final Session session, @WebParam(name = "userId") @NotNull final String userId,
             @WebParam(name = "firstName") @NotNull final String firstName, @WebParam(name = "middleName") @NotNull final String middleName,
             @WebParam(name = "lastName")  @NotNull final String lastName, @WebParam(name = "email") @NotNull final String email,
             @WebParam(name = "session") @NotNull final Role role) throws Exception {
        if (sessionService.validateAdmin(session)) {
            adminService.updateUserById(userId, firstName, middleName, lastName, email, role);
        }
    }
}
