package ru.burmistrov.tm.repository;

import org.jetbrains.annotations.NotNull;
import ru.burmistrov.tm.api.repository.ITaskRepository;
import ru.burmistrov.tm.entity.Task;
import ru.burmistrov.tm.entity.enumerated.Status;

import javax.persistence.EntityManager;
import java.util.Date;
import java.util.List;

public class TaskRepository implements ITaskRepository {

    @NotNull final private EntityManager entityManager;

    public TaskRepository(@NotNull EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void persist(@NotNull final Task task) {
        entityManager.persist(task);
    }

    @Override
    public void merge(@NotNull final Task task) {
        entityManager.merge(task);
    }

    @Override
    public void remove(@NotNull String id, @NotNull String userId) {
        entityManager.createQuery("DELETE FROM Task task WHERE task.id = " + id + " AND task.user_id = " + userId);
    }

    @Override
    public void removeAll(@NotNull String userId) {
        entityManager.createQuery("DELETE FROM Task task WHERE task.user_id = " + userId);
    }

    @NotNull
    @Override
    public List<Task> findAll(@NotNull String userId) {
        return (List<Task>) entityManager.createQuery("SELECT task FROM Task task WHERE task.user_id = " + userId).getSingleResult();
    }

    @Override
    public Task findOne(@NotNull String id, @NotNull String userId) {
        return (Task) entityManager.createQuery("SELECT task FROM Task task WHERE task.id = " + id + " AND task.user_id = " + userId).getSingleResult();
    }

    @Override
    public Task findOneByName(@NotNull String userId, @NotNull String name) {
        return (Task) entityManager.createQuery("SELECT task FROM Task task WHERE task.user_id = " + userId + " AND task.name = " + name).getSingleResult();
    }

    @Override
    public Task findOneByDescription(@NotNull String userId, @NotNull String description) {
        return (Task) entityManager.createQuery("SELECT task FROM Task task WHERE task.user_id = " + userId + " AND task.description = " + description).getSingleResult();
    }

    @Override
    public void removeAllInProject(@NotNull String userId, @NotNull String projectId) {
        entityManager.createQuery("DELETE from Task task WHERE task.user_id = " + userId + " AND task.project_id = " + projectId);
    }

    @Override
    public List<Task> findAllByProjectId(@NotNull String userId, @NotNull String projectId) {
        return entityManager.createQuery("SELECT task FROM Task task WHERE task.user_id = " + userId + " AND task.project_id = " + projectId).getResultList();
    }
}
