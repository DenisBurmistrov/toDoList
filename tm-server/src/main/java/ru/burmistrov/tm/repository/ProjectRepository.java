package ru.burmistrov.tm.repository;

import org.jetbrains.annotations.NotNull;
import ru.burmistrov.tm.api.repository.IProjectRepository;
import ru.burmistrov.tm.entity.AbstractEntity;
import ru.burmistrov.tm.entity.Project;

import javax.persistence.EntityManager;
import java.util.List;

public class ProjectRepository implements IProjectRepository {

    @NotNull final private EntityManager entityManager;

    public ProjectRepository(@NotNull final EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void persist(@NotNull AbstractEntity project) {
        entityManager.persist(project);
    }

    @Override
    public void merge(@NotNull AbstractEntity project) {
        entityManager.merge(project);
    }

    @Override
    public void remove(@NotNull String userId, @NotNull String projectId) {
        entityManager.createQuery("DELETE from tm.app_project WHERE id = "+ projectId +" AND user_id = "+ userId);
    }

    @Override
    public void removeAll(@NotNull String userId) {
        entityManager.createQuery("DELETE from tm.app_project WHERE user_id = " + userId);
    }

    @Override
    public List<Project> findAll(@NotNull String userId) {
        return entityManager.createQuery("SELECT * FROM tm.app_project WHERE user_id = #{userId}").getResultList();
    }

    @Override
    public Project findOne(@NotNull String id, @NotNull String userId) {
        return (Project) entityManager.createQuery("SELECT * FROM tm.app_project WHERE id = " + id +" AND user_id = " + userId).getSingleResult();
    }


    @Override
    public Project findOneByName(@NotNull String userId, @NotNull String name) {
        return (Project) entityManager.createQuery("SELECT * FROM tm.app_project WHERE user_id = " + userId +" AND name = " + name).getSingleResult();
    }

    @Override
    public Project findOneByDescription(@NotNull String userId, @NotNull String description) {
        return (Project) entityManager.createQuery("SELECT * FROM tm.app_project WHERE user_id = " + userId +" AND description = " + description).getSingleResult();
    }
}
