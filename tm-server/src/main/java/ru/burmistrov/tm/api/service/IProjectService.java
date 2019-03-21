package ru.burmistrov.tm.api.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.burmistrov.tm.entity.AbstractEntity;
import ru.burmistrov.tm.entity.Project;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.util.List;

public interface IProjectService {

    void remove(@NotNull final String userId, @NotNull final String projectId);

    @Nullable
    Project persist(@NotNull final String userId, @NotNull final String name, @NotNull final String description,
                    @NotNull final String dateEnd) throws ParseException, IOException, NoSuchAlgorithmException;

    void merge(@NotNull final String userId, @NotNull final String taskId, @NotNull final String name, @NotNull final String description,
               @NotNull final String dateEnd) throws ParseException;

    void removeAll(@NotNull final String userId);

    @NotNull
    List<Project> findAll(@NotNull final String userId);

    @NotNull
    List<Project> findAllSortByDateBegin(@NotNull final String userId);

    @NotNull
    List<Project> findAllSortByDateEnd(@NotNull final String userId);

    @NotNull
    List<Project> findAllSortByStatus(@NotNull final String userId);

    @Nullable
    Project findOneByName(@NotNull final String userId, @NotNull final String name);

    @Nullable
    Project findOneByDescription(@NotNull final String userId, @NotNull final String description);

}
