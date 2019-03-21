package ru.burmistrov.tm.endpoint;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.burmistrov.tm.api.endpoint.IAdminEndpoint;
import ru.burmistrov.tm.api.loader.ServiceLocator;
import ru.burmistrov.tm.entity.enumerated.Role;
import ru.burmistrov.tm.entity.Session;
import ru.burmistrov.tm.entity.User;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService
public class AdminEndpoint implements IAdminEndpoint {

    private ServiceLocator serviceLocator;

    public AdminEndpoint() {
    }

    public AdminEndpoint(ServiceLocator serviceLocator) {
        this.serviceLocator = serviceLocator;
    }

    @WebMethod
    @Override
    public void saveDataByDefault(@WebParam(name = "session") @NotNull Session session) throws Exception {
        if (serviceLocator.getSessionService().validateAdmin(session)) {
            serviceLocator.getAdminService().saveDataByDefault(session);
        }
    }

    @WebMethod
    @Override
    public void saveDataByFasterXmlJson(@WebParam(name = "session") @NotNull Session session) throws Exception {
        if (serviceLocator.getSessionService().validateAdmin(session)) {
            serviceLocator.getAdminService().saveDataByFasterXmlJson(session);
        }
    }

    @WebMethod
    @Override
    public void saveDataByFasterXml(@NotNull Session session) throws Exception {
        if (serviceLocator.getSessionService().validateAdmin(session)) {
            serviceLocator.getAdminService().saveDataByFasterXml(session);
        }
    }

    @WebMethod
    @Override
    public void saveDataByJaxbJson(@WebParam(name = "session") @NotNull Session session) throws Exception {
        if (serviceLocator.getSessionService().validateAdmin(session)) {
            serviceLocator.getAdminService().saveDataByJaxbJson(session);
        }
    }

    @WebMethod
    @Override
    public void saveDataByJaxbXml(@WebParam(name = "session") @NotNull Session session) throws Exception {
        if (serviceLocator.getSessionService().validateAdmin(session)) {
            serviceLocator.getAdminService().saveDataByJaxbXml(session);
        }
    }

    @WebMethod
    @Override
    public void loadDataByDefault(@WebParam(name = "session") @NotNull Session session) throws Exception {
        if (serviceLocator.getSessionService().validateAdmin(session)) {
            serviceLocator.getAdminService().loadDataByDefault(session);
        }
    }

    @WebMethod
    @Override
    public void loadDataByFasterXmlJson(@WebParam(name = "session") @NotNull Session session) throws Exception {
        if (serviceLocator.getSessionService().validateAdmin(session)) {
            serviceLocator.getAdminService().loadDataByFasterXmlJson(session);
        }
    }

    @WebMethod
    @Override
    public void loadDataByFasterXml(@WebParam(name = "session") @NotNull Session session) throws Exception {
        if (serviceLocator.getSessionService().validateAdmin(session)) {
            serviceLocator.getAdminService().loadDataByFasterXml(session);
        }
    }

    @WebMethod
    @Override
    public void loadDataByJaxbJson(@WebParam(name = "session") @NotNull Session session) throws Exception {
        if (serviceLocator.getSessionService().validateAdmin(session)) {
            serviceLocator.getAdminService().loadDataByJaxbJson(session);
        }
    }

    @WebMethod
    @Override
    public void loadDataByJaxbXml(@WebParam(name = "session") @NotNull Session session) throws Exception {
        if (serviceLocator.getSessionService().validateAdmin(session)) {
            serviceLocator.getAdminService().loadDataByJaxbXml(session);
        }
    }

    @WebMethod
    @Nullable
    public User createUser
            (@WebParam(name = "session") @NotNull Session session, @WebParam(name = "login") @NotNull String login,
             @WebParam(name = "password") @NotNull String password, @WebParam(name = "firstName") @NotNull String firstName,
             @WebParam(name = "middleName") @NotNull String middleName, @WebParam(name = "lastName") @NotNull String lastName,
             @WebParam(name = "email") @NotNull String email, @WebParam(name = "roleType") @NotNull Role roleType) throws Exception {
        if (serviceLocator.getSessionService().validateAdmin(session)) {
            return serviceLocator.getAdminService().createUser(login, password, firstName, middleName, lastName, email, roleType);
        }
        return null;
    }

    @WebMethod
    public void updatePasswordById
            (@WebParam(name = "session") @NotNull Session session, @WebParam(name = "userId") @NotNull String userId,
             @WebParam(name = "login") @NotNull String login, @WebParam(name = "password") @NotNull String password) throws Exception {
        if (serviceLocator.getSessionService().validateAdmin(session)) {
            serviceLocator.getAdminService().updatePassword(userId, login, password);
        }
    }

    @WebMethod
    public void removeUserById
            (@WebParam(name = "session") @NotNull Session session, @WebParam(name = "userId") @NotNull String userId) throws Exception {
        if (serviceLocator.getSessionService().validateAdmin(session)) {
            serviceLocator.getAdminService().removeUserById(userId);
        }
    }

    @WebMethod
    public void removeAllUsers
            (@WebParam(name = "session") @NotNull Session session, @WebParam(name = "userId") @NotNull String userId) throws Exception {
        if (serviceLocator.getSessionService().validateAdmin(session)) {
            serviceLocator.getAdminService().removeAllUsers(userId);
        }
    }

    @WebMethod
    @Override
    public void updateUserById
            (@WebParam(name = "session") @NotNull Session session, @WebParam(name = "userId") @NotNull String userId, @WebParam(name = "firstName") @NotNull String firstName,
             @WebParam(name = "middleName") @NotNull String middleName,@WebParam(name = "lastName")  @NotNull String lastName, @WebParam(name = "email")  @NotNull String email,
             @WebParam(name = "session") @NotNull Role role) throws Exception {
        if (serviceLocator.getSessionService().validateAdmin(session)) {
            serviceLocator.getAdminService().updateUserById(userId, firstName, middleName, lastName, email, role);
        }
    }
}
