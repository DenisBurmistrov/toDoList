package ru.burmistrov.tm.repository;

import org.apache.deltaspike.data.api.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.burmistrov.tm.entity.Task;

import java.util.List;

@Repository
public interface ITaskRepository extends FullEntityRepository<Task, Long> {

    void persist(@NotNull final Task task);

    Task merge(@NotNull final Task task);

    void remove(@NotNull final Task task);

    @Modifying
    @Query(value = "DELETE FROM Task task WHERE task.userId = :userId")
    void removeAll(@NotNull @QueryParam(value = "userId") final String userId);

    @NotNull
    @Query(value = "SELECT task FROM Task task WHERE task.userId = :userId")
    List<Task> findAll(@NotNull @QueryParam(value = "userId") final String userId);

    @Nullable
    @Query(value = "SELECT task FROM Task task WHERE task.id = :taskId AND task.userId = :userId", max = 1)
    Task findOne(@NotNull @QueryParam(value = "taskId") final String id, @NotNull @QueryParam(value = "userId") final String userId);

    @Nullable
    @Query(value = "SELECT task FROM Task task WHERE task.userId = :userId AND task.name = :name", max = 1)
    Task findOneByName(@NotNull @QueryParam(value = "userId") final String userId, @NotNull @QueryParam(value = "name") final String name);

    @Nullable
    @Query(value = "SELECT task FROM Task task WHERE task.userId = :userId AND task.description = :description", max = 1)
    Task findOneByDescription(@NotNull @QueryParam(value = "userId") final String userId, @NotNull @QueryParam(value = "description") final String description);

    @Modifying
    @Query(value = "DELETE from Task task WHERE task.userId = :userId AND task.projectId = :projectId")
    void removeAllInProject(@NotNull @QueryParam(value = "userId") final String userId,
                            @NotNull @QueryParam(value = "projectId") final String projectId);

    @Nullable
    @Query(value = "SELECT task FROM Task task WHERE task.userId = :userId AND task.projectId = :projectId")
    List<Task> findAllByProjectId(@NotNull @QueryParam(value = "userId") final String userId, @NotNull @QueryParam(value = "projectId") final String projectId);
}
