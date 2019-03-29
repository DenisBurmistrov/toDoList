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
        return (List<Task>) entityManager.createQuery("SELECT task FROM Task task WHERE task.userId = '" + userId + "'").getSingleResult();
    }

    @Override
    public Task findOne(@NotNull final String id, @NotNull final String userId) {
        return (Task) entityManager.createQuery("SELECT task FROM Task task WHERE task.id = '" + id + "' AND task.userId = '" + userId + "'").getSingleResult();
    }

    @Override
    public Task findOneByName(@NotNull final String userId, @NotNull final String name) {
        return (Task) entityManager.createQuery("SELECT task FROM Task task WHERE task.userId = '" + userId + "' AND task.name = '" + name + "'").getSingleResult();
    }

    @Override
    public Task findOneByDescription(@NotNull final String userId, @NotNull final String description) {
        return (Task) entityManager.createQuery("SELECT task FROM Task task WHERE task.userId = '" + userId + "' AND task.description = '" + description + "'").getSingleResult();
    }

    @Override
    public void removeAllInProject(@NotNull final String userId, @NotNull final String projectId) {
        entityManager.createQuery("DELETE from Task task WHERE task.userId = '" + userId + "' AND task.projectId = '" + projectId + "'");
    }

    @Override
    public List<Task> findAllByProjectId(@NotNull final String userId, @NotNull final String projectId) {
        return entityManager.createQuery("SELECT task FROM Task task WHERE task.userId = '" + userId + "' AND task.projectId = '" + projectId + "'").getResultList();
    }
}