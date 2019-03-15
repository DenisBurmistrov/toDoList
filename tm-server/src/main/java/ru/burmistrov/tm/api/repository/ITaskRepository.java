package ru.burmistrov.tm.api.repository;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.burmistrov.tm.entity.AbstractEntity;
import ru.burmistrov.tm.entity.Task;
import ru.burmistrov.tm.repository.AbstractRepository;

import java.util.List;

public interface ITaskRepository {

    @Nullable
    Task persist(@NotNull Task abstractEntity);

    void merge(@NotNull Task abstractEntity);

    void remove(@NotNull Task entity);

    void removeAll(@NotNull Task entity);

    @NotNull
    List<Task> findAll(@NotNull Task entity);

    void removeAllInProject(@NotNull Task entity);

    @NotNull
    List<Task> findAllInProject(@NotNull Task entity);

    @Nullable
    Task findOne(@NotNull Task entity);

    @NotNull
    List<Task> findAllSortByDateBegin(@NotNull Task abstractEntity);

    @NotNull
    List<Task> findAllSortByDateEnd(@NotNull Task abstractEntity);

    @NotNull
    List<Task> findAllSortByStatus(@NotNull Task abstractEntity);

    @Nullable
    Task findOneByName(@NotNull Task abstractEntity);

    @Nullable
    Task findOneByDescription(@NotNull Task abstractEntity);

}
