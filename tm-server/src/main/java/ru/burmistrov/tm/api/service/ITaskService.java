package ru.burmistrov.tm.api.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.burmistrov.tm.entity.AbstractEntity;
import ru.burmistrov.tm.entity.Task;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

public interface ITaskService {

    void merge
            (@NotNull final String userId, @NotNull final String projectId, @NotNull final String taskId, @NotNull final String newName,
             @NotNull final String description, @NotNull final String dateEnd) throws ParseException, SQLException;

    @Nullable
    Task persist
            (@NotNull final String userId, @NotNull final String projectId, @NotNull final String name, @NotNull final String description,
             @NotNull final String dateEnd) throws ParseException, IOException, NoSuchAlgorithmException, SQLException;

    @NotNull
    List<Task> findAll(@NotNull final String userId) throws SQLException;

    void removeAllInProject(@NotNull final String userId, @NotNull final String projectId) throws SQLException;

    void remove(@NotNull final String userId, @NotNull final String taskId) throws SQLException;

    void removeAll(@NotNull final String userId) throws SQLException;

    @NotNull
    List<Task> findAllSortByDateBegin(@NotNull final String userId);

    @NotNull
    List<Task> findAllSortByDateEnd(@NotNull final String userId);

    @NotNull
    List<Task> findAllSortByStatus(@NotNull final String userId) throws SQLException;

    @Nullable
    Task findOneByName(@NotNull final String userId, @NotNull final String name) throws SQLException;

    @Nullable
    Task findOneByDescription(@NotNull final String userId, @NotNull final String description) throws SQLException;

    @NotNull
    List<Task> findAllInProject(@NotNull final String userId, @NotNull final String projectId) throws SQLException;
}
