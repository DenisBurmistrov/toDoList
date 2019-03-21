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
    void saveDataByDefault(@WebParam @NotNull Session session) throws IOException, CloneNotSupportedException;

    @WebMethod
    void saveDataByFasterXmlJson(@WebParam @NotNull Session session) throws IOException, CloneNotSupportedException;

    @WebMethod
    void saveDataByFasterXml(@WebParam @NotNull Session session) throws IOException, CloneNotSupportedException;

    @WebMethod
    void saveDataByJaxbJson(@WebParam @NotNull Session session) throws JAXBException, IOException, CloneNotSupportedException;

    @WebMethod
    void saveDataByJaxbXml(@WebParam @NotNull Session session) throws IOException, JAXBException, CloneNotSupportedException;

    @WebMethod
    void loadDataByDefault(@WebParam @NotNull Session session) throws IOException, ClassNotFoundException, CloneNotSupportedException;

    @WebMethod
    void loadDataByFasterXmlJson(@WebParam @NotNull Session session) throws IOException, CloneNotSupportedException;

    @WebMethod
    void loadDataByFasterXml(@WebParam @NotNull Session session) throws IOException, CloneNotSupportedException;

    @WebMethod
    void loadDataByJaxbJson(@WebParam @NotNull Session session) throws JAXBException, CloneNotSupportedException, IOException;

    @WebMethod
    void loadDataByJaxbXml(@WebParam @NotNull Session session) throws JAXBException, CloneNotSupportedException, IOException;

    @WebMethod
    @Nullable
    User createUser(@WebParam @NotNull Session session, @WebParam @NotNull String login, @WebParam @NotNull String password,
                    @WebParam @NotNull String firstName, @WebParam @NotNull String middleName,
                    @WebParam @NotNull String lastName, @WebParam @NotNull String email,
                    @WebParam @NotNull Role roleType) throws CloneNotSupportedException;

    @WebMethod
    void updatePasswordById(@WebParam @NotNull Session session, @WebParam @NotNull String userId, @WebParam @NotNull String login,
                            @WebParam @NotNull String password) throws CloneNotSupportedException;

    @WebMethod
    void updateUserById(@WebParam @NotNull Session session, @WebParam @NotNull String userId, @WebParam @NotNull String firstName,
                        @WebParam @NotNull String middleName, @WebParam @NotNull String lastName,
                        @WebParam @NotNull String email, @WebParam @NotNull Role role) throws CloneNotSupportedException;

    @WebMethod
    void removeUserById(@WebParam @NotNull Session session, @WebParam @NotNull String userId) throws CloneNotSupportedException;

    @WebMethod
    void removeAllUsers(@WebParam @NotNull Session session, @WebParam @NotNull String userId) throws CloneNotSupportedException;

}
