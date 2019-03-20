package ru.burmistrov.tm.api.endpoint;


import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.burmistrov.tm.entity.Role;
import ru.burmistrov.tm.entity.Session;
import ru.burmistrov.tm.entity.User;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.bind.JAXBException;
import java.io.IOException;

@WebService
public interface IAdminEndpoint {

    @WebMethod
    void saveDataByDefault(@WebParam @NotNull Session session) throws IOException;

    @WebMethod
    void saveDataByFasterXmlJson(@WebParam @NotNull Session session) throws IOException;

    @WebMethod
    void saveDataByFasterXml(@WebParam @NotNull Session session) throws IOException;

    @WebMethod
    void saveDataByJaxbJson(@WebParam @NotNull Session session) throws JAXBException, IOException;

    @WebMethod
    void saveDataByJaxbXml(@WebParam @NotNull Session session) throws IOException, JAXBException;

    @WebMethod
    void loadDataByDefault(@WebParam @NotNull Session session) throws IOException, ClassNotFoundException;

    @WebMethod
    void loadDataByFasterXmlJson(@WebParam @NotNull Session session) throws IOException;

    @WebMethod
    void loadDataByFasterXml(@WebParam @NotNull Session session) throws IOException;

    @WebMethod
    void loadDataByJaxbJson(@WebParam @NotNull Session session) throws JAXBException;

    @WebMethod
    void loadDataByJaxbXml(@WebParam @NotNull Session session) throws JAXBException;

    @WebMethod
    @Nullable
    User createUser(@WebParam @NotNull String login, @WebParam @NotNull String password,
                    @WebParam @NotNull String firstName, @WebParam @NotNull String middleName,
                    @WebParam @NotNull String lastName, @WebParam @NotNull String email,
                    @WebParam @NotNull Role roleType);

    @WebMethod
    void updatePasswordById(@WebParam @NotNull String userId, @WebParam @NotNull String login,
                            @WebParam @NotNull String password);

    @WebMethod
    void updateUserById(@WebParam @NotNull String userId, @WebParam @NotNull String firstName,
                        @WebParam @NotNull String middleName, @WebParam @NotNull String lastName,
                        @WebParam @NotNull String email, @WebParam @NotNull Role role);

    @WebMethod
    void removeUserById(@WebParam @NotNull String userId);

    @WebMethod
    void removeAllUsers(@WebParam @NotNull String userId);

}
