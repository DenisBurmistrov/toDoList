package ru.burmistrov.tm.repository;


import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.burmistrov.tm.entity.AbstractEntity;
import ru.burmistrov.tm.entity.Project;

import java.util.List;

public interface IProjectRepository extends JpaRepository<Project, Long> {

    Project save(@NotNull final Project project);

    void delete(@NotNull final Project project);

    @Modifying
    @Query(value = "DELETE FROM Project project WHERE project.userId = :userId")
    void removeAll(@NotNull @Param(value = "userId") final String userId);

    @Query(value = "SELECT project FROM Project project WHERE project.userId = :userId")
    List<Project> findAll(@NotNull @Param(value = "userId") final String userId);

    @Query(value = "SELECT project FROM Project project WHERE project.id = :projectId AND project.userId = :userId")
    Project findOne(@NotNull @Param(value = "projectId") final String id, @NotNull @Param(value = "userId") final String userId);

    @Query(value = "SELECT project FROM Project project WHERE project.userId = :userId AND project.name = :name")
    Project findOneByName(@NotNull @Param(value = "userId") final String userId, @NotNull @Param(value = "name") final String name);

    @Query(value = "SELECT project FROM Project project WHERE project.userId = :userId AND project.description = :description")
    Project findOneByDescription(@NotNull @Param(value = "userId") final String userId, @NotNull @Param(value = "description") final String description);
}
