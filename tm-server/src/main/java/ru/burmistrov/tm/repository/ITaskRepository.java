package ru.burmistrov.tm.repository;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.burmistrov.tm.entity.AbstractEntity;
import ru.burmistrov.tm.entity.Task;

import java.util.List;

@Repository
public interface ITaskRepository extends JpaRepository<Task, Long> {

    Task save(@NotNull final Task task);

    @Modifying
    void delete(@NotNull final Task task);

    @Modifying
    @Query(value = "DELETE FROM Task task WHERE task.userId = :userId")
    void removeAll(@NotNull @Param(value = "userId") final String userId);

    @NotNull
    @Query(value = "SELECT task FROM Task task WHERE task.userId = :userId")
    List<Task> findAll(@NotNull @Param(value = "userId") final String userId);

    @Nullable
    @Query(value = "SELECT task FROM Task task WHERE task.id = :taskId AND task.userId = :userId")
    Task findOne(@NotNull @Param(value = "taskId") final String id, @NotNull @Param(value = "userId") final String userId);

    @Nullable
    @Query(value = "SELECT task FROM Task task WHERE task.userId = :userId AND task.name = :name")
    Task findOneByName(@NotNull @Param(value = "userId") final String userId, @NotNull @Param(value = "name") final String name);

    @Nullable
    @Query(value = "SELECT task FROM Task task WHERE task.userId = :userId AND task.description = :description")
    Task findOneByDescription(@NotNull @Param(value = "userId") final String userId, @NotNull @Param(value = "description") final String description);

    @Modifying
    @Query(value = "DELETE from Task task WHERE task.userId = :userId AND task.projectId = :projectId")
    void removeAllInProject(@NotNull @Param(value = "userId") final String userId,
                            @NotNull @Param(value = "projectId") final String projectId);

    @Nullable
    @Query(value = "SELECT task FROM Task task WHERE task.userId = :userId AND task.projectId = :projectId")
    List<Task> findAllByProjectId(@NotNull @Param(value = "userId") final String userId, @NotNull @Param(value = "projectId") final String projectId);
}
