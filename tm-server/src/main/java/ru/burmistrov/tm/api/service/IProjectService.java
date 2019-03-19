package ru.burmistrov.tm.api.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.burmistrov.tm.entity.AbstractEntity;
import ru.burmistrov.tm.entity.Project;

import java.text.ParseException;
import java.util.List;

public interface IProjectService {

    void remove(@NotNull String userId, @NotNull String projectId);

    @Nullable
    Project persist(@NotNull String userId, @NotNull String name, @NotNull String description, @NotNull String dateEnd) throws ParseException;

    void merge(@NotNull String userId, @NotNull String taskId, @NotNull String name, @NotNull String description, @NotNull String dateEnd) throws ParseException;

    void removeAll(@NotNull String userId);

    @NotNull
    List<Project> findAll(@NotNull String userId);

    @NotNull
    List<Project> findAllSortByDateBegin(@NotNull String userId);

    @NotNull
    List<Project> findAllSortByDateEnd(@NotNull String userId);

    @NotNull
    List<Project> findAllSortByStatus(@NotNull String userId);

    @Nullable
    Project findOneByName(@NotNull String userId, @NotNull String name);

    @Nullable
    Project findOneByDescription(@NotNull String userId, @NotNull String description);

}
