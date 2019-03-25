package ru.burmistrov.tm.api.repository;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.burmistrov.tm.entity.AbstractEntity;
import ru.burmistrov.tm.entity.Project;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public interface IProjectRepository{

    @Nullable
    Project persist
            (@NotNull final String userId, @NotNull final Date dateBegin,
             @NotNull final Date dateEnd, @NotNull final String description,
             @NotNull final String name) throws IOException, NoSuchAlgorithmException, SQLException;

    void merge(@NotNull final Project project) throws SQLException;

    void remove
            (@NotNull final String id, @NotNull final String userId) throws SQLException;

    void removeAll
            (@NotNull final String userId) throws SQLException;

    @NotNull List<Project> findAll(@NotNull final String userId) throws SQLException;

    @Nullable Project findOne
            (@NotNull final String id, @NotNull final String userId) throws SQLException;

    @NotNull List<Project> findAllSortByDateBegin
            (@NotNull final String userId) throws SQLException;

    @NotNull List<Project> findAllSortByDateEnd
            (@NotNull final String userId) throws SQLException;

    @NotNull List<Project> findAllSortByStatus
            (@NotNull final String userId) throws SQLException;

    @Nullable Project findOneByName
            (@NotNull final String userId, @NotNull final String name) throws SQLException;

    @Nullable Project findOneByDescription
            (@NotNull final String userId, @NotNull final String description) throws SQLException;
}
