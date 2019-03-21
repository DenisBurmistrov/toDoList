package ru.burmistrov.tm.api.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.burmistrov.tm.entity.AbstractEntity;
import ru.burmistrov.tm.entity.Task;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.util.List;

public interface ITaskService {

    void merge
            (@NotNull final String userId, @NotNull final String projectId, @NotNull final String taskId, @NotNull final String newName,
             @NotNull final String description, @NotNull final String dateEnd) throws ParseException;

    @Nullable
    Task persist
            (@NotNull final String userId, @NotNull final String projectId, @NotNull final String name, @NotNull final String description,
             @NotNull final String dateEnd) throws ParseException, IOException, NoSuchAlgorithmException;

    @NotNull
    List<Task> findAll(@NotNull final String userId);

    void removeAllInProject(@NotNull final String userId, @NotNull final String projectId);

    void remove(@NotNull final String userId, @NotNull final String taskId);

    void removeAll(@NotNull final String userId);

    @NotNull
    List<Task> findAllSortByDateBegin(@NotNull final String userId);

    @NotNull
    List<Task> findAllSortByDateEnd(@NotNull final String userId);

    @NotNull
    List<Task> findAllSortByStatus(@NotNull final String userId);

    @Nullable
    Task findOneByName(@NotNull final String userId, @NotNull final String name);

    @Nullable
    Task findOneByDescription(@NotNull final String userId, @NotNull final String description);

    @NotNull
    List<Task> findAllInProject(@NotNull final String userId, @NotNull final String projectId);
}
