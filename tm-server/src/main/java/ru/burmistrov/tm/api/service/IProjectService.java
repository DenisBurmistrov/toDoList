package ru.burmistrov.tm.api.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.burmistrov.tm.entity.Project;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

public interface IProjectService {

    void remove(@NotNull final String userId, @NotNull final String projectId) throws SQLException;

    @Nullable
    Project persist(@NotNull final String userId, @NotNull final String name, @NotNull final String description,
                    @NotNull final String dateEnd, @NotNull final String status) throws ParseException, IOException, NoSuchAlgorithmException, SQLException;

    void merge(@NotNull final String userId, @NotNull final String projectId, @NotNull final String name, @NotNull final String description,
               @NotNull final String dateEnd, @NotNull final String status) throws ParseException, SQLException;

    void removeAll(@NotNull final String userId) throws SQLException;

    @Nullable
    List<Project> findAll(@NotNull final String userId) throws SQLException;

    @NotNull
    List<Project> findAllSortByDateBegin(@NotNull final String userId) throws SQLException;

    @NotNull
    List<Project> findAllSortByDateEnd(@NotNull final String userId) throws SQLException;

    @NotNull
    List<Project> findAllSortByStatus(@NotNull final String userId) throws SQLException;

    @Nullable
    Project findOneByName(@NotNull final String userId, @NotNull final String name) throws SQLException;

    @Nullable
    Project findOneByDescription(@NotNull final String userId, @NotNull final String description) throws SQLException;

}
