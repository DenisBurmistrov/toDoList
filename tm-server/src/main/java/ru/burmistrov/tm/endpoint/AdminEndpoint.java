package ru.burmistrov.tm.endpoint;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.burmistrov.tm.api.endpoint.IAdminEndpoint;
import ru.burmistrov.tm.api.loader.ServiceLocator;
import ru.burmistrov.tm.entity.Role;
import ru.burmistrov.tm.entity.Session;
import ru.burmistrov.tm.entity.User;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.xml.bind.JAXBException;
import java.io.IOException;

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
    public void saveDataByDefault(@NotNull Session session) throws Exception {
        if(serviceLocator.getSessionService().validateAdmin(session)) {
            serviceLocator.getAdminService().saveDataByDefault(session);
        }
    }

    @WebMethod
    @Override
    public void saveDataByFasterXmlJson(@NotNull Session session) throws Exception {
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
    public void saveDataByJaxbJson(@NotNull Session session) throws Exception {
        if (serviceLocator.getSessionService().validateAdmin(session)) {
            serviceLocator.getAdminService().saveDataByJaxbJson(session);
        }
    }

    @WebMethod
    @Override
    public void saveDataByJaxbXml(@NotNull Session session) throws Exception {
        if (serviceLocator.getSessionService().validateAdmin(session)) {
            serviceLocator.getAdminService().saveDataByJaxbXml(session);
        }
    }

    @WebMethod
    @Override
    public void loadDataByDefault(@NotNull Session session) throws Exception {
        if (serviceLocator.getSessionService().validateAdmin(session)) {
            serviceLocator.getAdminService().loadDataByDefault(session);
        }
    }

    @WebMethod
    @Override
    public void loadDataByFasterXmlJson(@NotNull Session session) throws Exception {
        if (serviceLocator.getSessionService().validateAdmin(session)) {
            serviceLocator.getAdminService().loadDataByFasterXmlJson(session);
        }
    }

    @WebMethod
    @Override
    public void loadDataByFasterXml(@NotNull Session session) throws Exception {
        if (serviceLocator.getSessionService().validateAdmin(session)) {
            serviceLocator.getAdminService().loadDataByFasterXml(session);
        }
    }

    @WebMethod
    @Override
    public void loadDataByJaxbJson(@NotNull Session session) throws Exception {
        if (serviceLocator.getSessionService().validateAdmin(session)) {
            serviceLocator.getAdminService().loadDataByJaxbJson(session);
        }
    }

    @WebMethod
    @Override
    public void loadDataByJaxbXml(@NotNull Session session) throws Exception {
        if (serviceLocator.getSessionService().validateAdmin(session)) {
            serviceLocator.getAdminService().loadDataByJaxbXml(session);
        }
    }

    @WebMethod
    @Nullable
    public User createUser(@NotNull Session session, @NotNull String login, @NotNull String password, @NotNull String firstName, @NotNull String middleName,
                           @NotNull String lastName, @NotNull String email, @NotNull Role roleType) throws Exception {
        if (serviceLocator.getSessionService().validateAdmin(session)) {
            return serviceLocator.getAdminService().persist(login, password, firstName, middleName, lastName, email, roleType);
        }
        return null;
    }

    @WebMethod
    public void updatePasswordById(@NotNull Session session, @NotNull String userId, @NotNull String login, @NotNull String password) throws Exception {
        if (serviceLocator.getSessionService().validateAdmin(session)) {
            serviceLocator.getAdminService().updatePassword(userId, login, password);
        }
    }

    @WebMethod
    public void removeUserById(@NotNull Session session, @NotNull String userId) throws Exception {
        if (serviceLocator.getSessionService().validateAdmin(session)) {
            serviceLocator.getAdminService().remove(userId);
        }
    }

    @WebMethod
    public void removeAllUsers(@NotNull Session session, @NotNull String userId) throws Exception {
        if (serviceLocator.getSessionService().validateAdmin(session)) {
            serviceLocator.getAdminService().removeAll(userId);
        }
    }

    @WebMethod
    @Override
    public void updateUserById
            (@NotNull Session session, @NotNull String userId, @NotNull String firstName,
             @NotNull String middleName, @NotNull String lastName, @NotNull String email, @NotNull Role role) throws Exception {
        if (serviceLocator.getSessionService().validateAdmin(session)) {
            serviceLocator.getAdminService().merge(userId, firstName, middleName, lastName, email, role);
        }
    }
}
