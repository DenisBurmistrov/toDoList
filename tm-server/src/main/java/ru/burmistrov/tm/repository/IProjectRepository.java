package ru.burmistrov.tm.repository;


import org.apache.deltaspike.data.api.*;
import org.jetbrains.annotations.NotNull;
import ru.burmistrov.tm.entity.Project;

import java.util.List;

@Repository
public interface IProjectRepository extends FullEntityRepository<Project, Long> {

    void persist(@NotNull final Project project);

    Project merge(@NotNull final Project project);

    void remove(@NotNull final Project project);

    @Modifying
    @Query(value = "DELETE FROM Project project WHERE project.user_id = :userId")
    void removeAll(@NotNull @QueryParam(value = "userId") final String userId);

    @Query(value = "SELECT project FROM Project project WHERE project.userId = :userId")
    List<Project> findAll(@NotNull @QueryParam(value = "userId") final String userId);

    @Query(value = "SELECT project FROM Project project WHERE project.id = :projectId AND project.userId = :userId", max = 1)
    Project findOne(@NotNull @QueryParam(value = "projectId") final String id, @NotNull @QueryParam(value = "userId") final String userId);

    @Query(value = "SELECT project FROM Project project WHERE project.userId = :userId AND project.name = :name", max = 1)
    Project findOneByName(@NotNull @QueryParam(value = "userId") final String userId, @NotNull @QueryParam(value = "name") final String name);

    @Query(value = "SELECT project FROM Project project WHERE project.userId = :userId AND project.description = :description", max = 1)
    Project findOneByDescription(@NotNull @QueryParam(value = "userId") final String userId, @NotNull @QueryParam(value = "description") final String description);
}
