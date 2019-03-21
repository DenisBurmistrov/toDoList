package ru.burmistrov.tm.api.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.burmistrov.tm.entity.*;
import ru.burmistrov.tm.entity.enumerated.Role;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public interface IAdminService {

    void saveDataByDefault(@NotNull final Session session) throws IOException;

    void saveDataByFasterXmlJson(@NotNull final Session session) throws IOException;

    void saveDataByFasterXml(@NotNull final Session session) throws IOException;

    void saveDataByJaxbJson(@NotNull final Session session) throws JAXBException, IOException;

    void saveDataByJaxbXml(@NotNull final Session session) throws IOException, JAXBException;

    void loadDataByDefault(@NotNull final Session session) throws IOException, ClassNotFoundException, NoSuchAlgorithmException;

    void loadDataByFasterXmlJson(@NotNull final Session session) throws IOException, NoSuchAlgorithmException;

    void loadDataByFasterXml(@NotNull final Session session) throws IOException, NoSuchAlgorithmException;

    void loadDataByJaxbJson(@NotNull final Session session) throws JAXBException, IOException, NoSuchAlgorithmException;

    void loadDataByJaxbXml(@NotNull final Session session) throws JAXBException, IOException, NoSuchAlgorithmException;

    @Nullable
    User createUser
            (@NotNull final String login, @NotNull final String password, @NotNull final String firstName,
             @NotNull final String middleName, @NotNull final String lastName, @NotNull String email, @Nullable Role roleType) throws NoSuchAlgorithmException, IOException;

    void updatePassword
            (@NotNull final String userId, @NotNull final String login, @NotNull final String password) throws NoSuchAlgorithmException;

    void updateUserById
            (@NotNull final String userId, @NotNull final String firstName, @NotNull final String middleName,
             @NotNull final String lastName, @NotNull final String email, @NotNull final Role role);

    void removeUserById(@NotNull final String userId);

    void removeAllUsers(@NotNull final String userId);
}
