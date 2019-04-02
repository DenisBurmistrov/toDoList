package ru.burmistrov.tm.repository;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import ru.burmistrov.tm.api.repository.ITaskRepository;
import ru.burmistrov.tm.entity.Task;
import ru.burmistrov.tm.entity.enumerated.Status;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.Date;
import java.util.List;

@Getter
@NoArgsConstructor
public class TaskRepository implements ITaskRepository {

    @Inject
    private EntityManager entityManager;

    @Override
    public void persist(@NotNull final Task task) {
        entityManager.persist(task);
    }

    @Override
    public void merge(@NotNull final Task task) {
        entityManager.merge(task);
    }

    @Override
    public void remove(@NotNull final Task task) {
        entityManager.remove(task);
    }

    @Override
    public void removeAll(@NotNull final String userId) {
        entityManager.createQuery("DELETE FROM Task task WHERE task.userId = '" + userId + "'");
    }

    @NotNull
    @Override
    public List<Task> findAll(@NotNull final String userId) {
        return entityManager.createQuery
                ("SELECT task FROM Task task WHERE task.userId =: userId", Task.class)
                .setParameter("userId", userId)
                .getResultList();
    }

    @Override
    public Task findOne(@NotNull final String id, @NotNull final String userId) {
        return entityManager.createQuery
                ("SELECT task FROM Task task WHERE task.id =: taskId AND task.userId =: userId", Task.class)
                .setParameter("userId", userId)
                .setParameter("taskId", id)
                .getSingleResult();
    }

    @Override
    public Task findOneByName(@NotNull final String userId, @NotNull final String name) {
        return entityManager.createQuery
                ("SELECT task FROM Task task WHERE task.userId = '" + userId + "' AND task.name = '" + name + "'", Task.class).getSingleResult();
    }

    @Override
    public Task findOneByDescription(@NotNull final String userId, @NotNull final String description) {
        return entityManager.createQuery
                ("SELECT task FROM Task task WHERE task.userId = '" + userId + "' AND task.description = '" + description + "'", Task.class).getSingleResult();
    }

    @Override
    public void removeAllInProject(@NotNull final String userId, @NotNull final String projectId) {
        entityManager.createQuery("DELETE from Task task WHERE task.userId = '" + userId + "' AND task.projectId = '" + projectId + "'");
    }

    @Override
    public List<Task> findAllByProjectId(@NotNull final String userId, @NotNull final String projectId) {
        return entityManager.createQuery
                ("SELECT task FROM Task task WHERE task.userId = '" + userId + "' AND task.projectId = '" + projectId + "'", Task.class).getResultList();
    }
}
