package ru.burmistrov.tm.api.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.burmistrov.tm.entity.AbstractEntity;
import ru.burmistrov.tm.entity.Task;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

public interface ITaskService {

    void merge(@NotNull String userId, @NotNull String projectId, @NotNull String taskId, @NotNull String newName,
               @NotNull String description, @NotNull String dateEnd) throws ParseException;

    @Nullable
    Task persist(@NotNull String userId, @NotNull String projectId, @NotNull String name, @NotNull String description,
                 @NotNull String dateEnd) throws ParseException, IOException;

    @NotNull
    List<Task> findAll(@NotNull String userId);

    void removeAllInProject(@NotNull String userId, @NotNull String projectId);

    void remove(@NotNull String userId, @NotNull String taskId);

    void removeAll(@NotNull String userId);

    @NotNull
    List<Task> findAllSortByDateBegin(@NotNull String userId);

    @NotNull
    List<Task> findAllSortByDateEnd(@NotNull String userId);

    @NotNull
    List<Task> findAllSortByStatus(@NotNull String userId);

    @Nullable
    Task findOneByName(@NotNull String userId, String name);

    @Nullable
    Task findOneByDescription(@NotNull String userId, String description);

    @NotNull
    List<Task> findAllInProject(@NotNull String userId, @NotNull String projectId);
}
