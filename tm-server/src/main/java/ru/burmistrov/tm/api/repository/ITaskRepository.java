package ru.burmistrov.tm.api.repository;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.burmistrov.tm.entity.AbstractEntity;
import ru.burmistrov.tm.entity.Task;
import ru.burmistrov.tm.repository.AbstractRepository;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

public interface ITaskRepository {

    @Nullable
    Task persist(@NotNull final Task abstractEntity) throws IOException, NoSuchAlgorithmException;

    void merge(@NotNull final Task abstractEntity);

    void remove(@NotNull final Task entity);

    void removeAll(@NotNull final Task entity);

    @NotNull
    List<Task> findAll(@NotNull final Task entity);

    void removeAllInProject(@NotNull final Task entity);

    @NotNull
    List<Task> findAllInProject(@NotNull final Task entity);

    @Nullable
    Task findOne(@NotNull final Task entity);

    @NotNull
    List<Task> findAllSortByDateBegin(@NotNull final Task abstractEntity);

    @NotNull
    List<Task> findAllSortByDateEnd(@NotNull final Task abstractEntity);

    @NotNull
    List<Task> findAllSortByStatus(@NotNull final Task abstractEntity);

    @Nullable
    Task findOneByName(@NotNull final Task abstractEntity);

    @Nullable
    Task findOneByDescription(@NotNull final Task abstractEntity);

}
