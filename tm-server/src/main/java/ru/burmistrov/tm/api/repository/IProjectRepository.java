package ru.burmistrov.tm.api.repository;


import org.jetbrains.annotations.NotNull;
import ru.burmistrov.tm.entity.AbstractEntity;
import ru.burmistrov.tm.entity.Project;

import javax.persistence.EntityManager;
import java.util.List;

public interface IProjectRepository<T extends AbstractEntity> {

    void persist(@NotNull final T project);

    void merge(@NotNull final T project);

    void remove(@NotNull final Project project);

    void removeAll(@NotNull final String userId);

    List<Project> findAll(@NotNull final String userId);

    Project findOne(@NotNull final String id, @NotNull final String userId);

    Project findOneByName(@NotNull final String userId, @NotNull final String name);

    Project findOneByDescription(@NotNull final String userId, @NotNull final String description);

    EntityManager getEntityManager();
}
