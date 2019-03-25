package ru.burmistrov.tm.api.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.burmistrov.tm.entity.*;
import ru.burmistrov.tm.entity.enumerated.Role;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

public interface IAdminService {

    void saveDataByDefault(@NotNull final Session session) throws IOException, SQLException;

    void saveDataByFasterXmlJson(@NotNull final Session session) throws IOException, SQLException;

    void saveDataByFasterXml(@NotNull final Session session) throws IOException, SQLException;

    void saveDataByJaxbJson(@NotNull final Session session) throws JAXBException, IOException, SQLException;

    void saveDataByJaxbXml(@NotNull final Session session) throws IOException, JAXBException, SQLException;

    void loadDataByDefault(@NotNull final Session session) throws IOException, ClassNotFoundException, NoSuchAlgorithmException, SQLException;

    void loadDataByFasterXmlJson(@NotNull final Session session) throws IOException, NoSuchAlgorithmException, SQLException;

    void loadDataByFasterXml(@NotNull final Session session) throws IOException, NoSuchAlgorithmException, SQLException;

    void loadDataByJaxbJson(@NotNull final Session session) throws JAXBException, IOException, NoSuchAlgorithmException, SQLException;

    void loadDataByJaxbXml(@NotNull final Session session) throws JAXBException, IOException, NoSuchAlgorithmException, SQLException;

    @Nullable
    User createUser
            (@NotNull final String login, @NotNull final String password, @NotNull final String firstName,
             @NotNull final String middleName, @NotNull final String lastName, @NotNull String email, @Nullable Role roleType) throws NoSuchAlgorithmException, IOException, SQLException;

    void updatePassword
            (@NotNull final String login, @NotNull final String password) throws NoSuchAlgorithmException, SQLException;

    void updateUserById
            (@NotNull final String userId, @NotNull final String firstName, @NotNull final String middleName,
             @NotNull final String lastName, @NotNull final String email, @NotNull final Role role) throws SQLException;

    void removeUserById(@NotNull final String userId) throws SQLException;

    void removeAllUsers() throws SQLException;
}
