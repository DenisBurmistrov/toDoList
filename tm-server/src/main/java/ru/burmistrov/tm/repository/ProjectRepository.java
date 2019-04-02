package ru.burmistrov.tm.repository;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import ru.burmistrov.tm.api.repository.IProjectRepository;
import ru.burmistrov.tm.entity.AbstractEntity;
import ru.burmistrov.tm.entity.Project;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

@Getter
@NoArgsConstructor
public class ProjectRepository implements IProjectRepository {

    @Inject
    private EntityManager entityManager;

    @Override
    public void persist(@NotNull final AbstractEntity project) {
        entityManager.persist(project);
    }

    @Override
    public void merge(@NotNull final AbstractEntity project) {
        entityManager.merge(project);
    }

    @Override
    public void remove(@NotNull final Project project) {
        entityManager.remove(project);
    }

    @Override
    public void removeAll(@NotNull final String userId) {
        entityManager.createQuery
                ("DELETE FROM Project project WHERE project.user_id =: userId")
                .setParameter("userId", userId);
    }

    @Override
    public List<Project> findAll(@NotNull final String userId) {
        return entityManager.createQuery
                ("SELECT project FROM Project project WHERE project.userId =: userId", Project.class)
                .setParameter("userId", userId)
                .getResultList();
    }

    @Override
    public Project findOne(@NotNull final String id, @NotNull final String userId) {
        return entityManager.createQuery
                ("SELECT project FROM Project project WHERE project.id =: projectId AND project.userId =: userId", Project.class)
                .setParameter("userId", userId)
                .setParameter("projectId", id)
                .getSingleResult();
    }


    @Override
    public Project findOneByName(@NotNull final String userId, @NotNull final String name) {
        return entityManager.createQuery
                ("SELECT project FROM Project project WHERE project.userId =: userId AND project.name =: name", Project.class)
                .setParameter("userId", userId)
                .setParameter("name", name)
                .getSingleResult();
    }

    @Override
    public Project findOneByDescription(@NotNull String userId, @NotNull String description) {
        return entityManager.createQuery
                ("SELECT project FROM Project project WHERE project.userId =: userId AND project.description =: description", Project.class)
                .setParameter("userId", userId)
                .setParameter("description", description)
                .getSingleResult();
    }
}
