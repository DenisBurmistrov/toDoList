package ru.burmistrov.tm.service;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.burmistrov.tm.api.repository.ITaskRepository;
import ru.burmistrov.tm.api.service.ITaskService;
import ru.burmistrov.tm.entity.AbstractEntity;
import ru.burmistrov.tm.entity.Task;
import ru.burmistrov.tm.entity.enumerated.Status;
import ru.burmistrov.tm.repository.TaskRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public final class TaskService implements ITaskService {

    @Nullable
    private ITaskRepository taskRepository;

    @NotNull
    private final EntityManagerFactory entityManagerFactory;

    public TaskService(@NotNull final EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    @Override
    @Nullable
    public Task persist(@NotNull final String userId, @NotNull final String projectId, @NotNull final String name,
                        @NotNull final String description, @NotNull final String dateEndString, @NotNull final String status) throws ParseException {
        @NotNull final EntityManager entityManager = entityManagerFactory.createEntityManager();
        taskRepository = new TaskRepository(entityManager);
        @NotNull final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");
        @NotNull final Date dateEnd = simpleDateFormat.parse(dateEndString);
        @Nullable AbstractEntity abstractEntity;
        try {
            abstractEntity = Objects.requireNonNull(taskRepository).findOneByName(userId, name);
        }catch (NoResultException e) {
            abstractEntity = null;
        }
        if (abstractEntity == null) {
            @NotNull final Task task = new Task();
            task.setUserId(userId);
            task.setDateBegin(new Date());
            task.setDateEnd(dateEnd);
            task.setDescription(description);
            task.setName(name);
            task.setProjectId(projectId);
            task.setStatus(createStatus(status));
            try {
                entityManager.getTransaction().begin();
                Objects.requireNonNull(taskRepository).persist(task);
                entityManager.getTransaction().commit();
                return task;
            } catch (Exception e) {
                entityManager.getTransaction().rollback();
            }
        }
        return null;
    }

    @Override
    public void merge(@NotNull final String userId, @NotNull final String projectId, @NotNull final String taskId,
                      @NotNull final String newName, @NotNull final String description, @NotNull final String dateEndString,
                      @NotNull final String status) throws ParseException {

        @NotNull final EntityManager entityManager = entityManagerFactory.createEntityManager();
        taskRepository = new TaskRepository(entityManager);
        @NotNull final Task task = new Task();
        task.setId(taskId);
        task.setName(newName);
        task.setDescription(description);
        task.setProjectId(projectId);
        task.setUserId(userId);
        task.setStatus(createStatus(status));
        @NotNull final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");
        @NotNull final Date dateEnd = simpleDateFormat.parse(dateEndString);
        task.setDateEnd(dateEnd);
        @Nullable final AbstractEntity abstractEntity = Objects.requireNonNull(taskRepository).findOne(task.getId(), Objects.requireNonNull(task.getUserId()));
        if (newName.length() != 0 && abstractEntity != null) {
            try {
                entityManager.getTransaction().begin();
                Objects.requireNonNull(taskRepository).merge(task);

                entityManager.getTransaction().commit();
            } catch (Exception e) {
                entityManager.getTransaction().rollback();
            }
        }
    }

    @NotNull
    @Override
    public List<Task> findAll(@Nullable final String userId) {
        @NotNull final EntityManager entityManager = entityManagerFactory.createEntityManager();
        taskRepository = new TaskRepository(entityManager);
        return Objects.requireNonNull(taskRepository).findAll(Objects.requireNonNull(userId));
    }

    @Override
    public void removeAllInProject(@NotNull final String userId, @NotNull final String projectId) {
        @NotNull final EntityManager entityManager = entityManagerFactory.createEntityManager();
        taskRepository = new TaskRepository(entityManager);
        try {
            entityManager.getTransaction().begin();
            Objects.requireNonNull(taskRepository).removeAllInProject(userId, projectId);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
        }
    }

    @Override
    public void remove(@NotNull final String userId, @NotNull final String taskId) {
        @NotNull final EntityManager entityManager = entityManagerFactory.createEntityManager();
        taskRepository = new TaskRepository(entityManager);
        Task task = taskRepository.findOne(taskId, userId);
        if(task != null) {
            try {
                entityManager.getTransaction().begin();
                Objects.requireNonNull(taskRepository).remove(task);
                entityManager.getTransaction().commit();
            } catch (Exception e) {
                entityManager.getTransaction().rollback();
            }
        }
    }

    @Override
    public void removeAll(@Nullable final String userId) {
        @NotNull final EntityManager entityManager = entityManagerFactory.createEntityManager();
        taskRepository = new TaskRepository(entityManager);
        try {
            entityManager.getTransaction().begin();
            Objects.requireNonNull(taskRepository).removeAll(Objects.requireNonNull(userId));
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
        }
    }

    @NotNull

    @Override
    public List<Task> findAllSortByDateBegin(@Nullable final String userId) {
        @NotNull final List<Task> result = Objects.requireNonNull(findAll(userId));
        result.sort((s1, s2) -> {
            @NotNull final boolean firstDateMoreThanSecond = s1.getDateBegin().getTime() - s2.getDateBegin().getTime() < 0;
            @NotNull final boolean secondDateMoreThaFirst = s1.getDateBegin().getTime() - s2.getDateBegin().getTime() > 0;

            if (firstDateMoreThanSecond) {
                return 1;
            } else if (secondDateMoreThaFirst) {
                return -1;
            } else {
                return 0;
            }
        });
        return result;
    }

    @NotNull
    @Override
    public List<Task> findAllSortByDateEnd(@Nullable final String userId) {
        @NotNull final List<Task> result = Objects.requireNonNull(findAll(userId));
        result.sort((s1, s2) -> {
            boolean firstDateMoreThanSecond = Objects.requireNonNull(s1.getDateEnd()).getTime() - Objects.requireNonNull(s2.getDateEnd()).getTime() > 0;
            boolean secondDateMoreThanFirst = Objects.requireNonNull(s1.getDateEnd()).getTime() - Objects.requireNonNull(s2.getDateEnd()).getTime() < 0;

            if (firstDateMoreThanSecond) {
                return 1;
            } else if (secondDateMoreThanFirst) {
                return -1;
            } else {
                return 0;
            }
        });
        return result;
    }

    @NotNull
    @Override
    public List<Task> findAllSortByStatus(@NotNull final String userId) {
        @NotNull final List<Task> result = Objects.requireNonNull(findAll(userId));
        result.stream().filter(e -> Objects.requireNonNull(e.getUserId()).
                equals(userId))
                .forEach(result::add);
        result.sort((s1, s2) -> Integer.compare(0, Objects.requireNonNull(s1.getStatus()).ordinal() - Objects.requireNonNull(s2.getStatus()).ordinal()));
        return result;
    }

    @Nullable
    @Override
    public Task findOneByName(@NotNull final String userId, @NotNull final String name) {
        @NotNull final EntityManager entityManager = entityManagerFactory.createEntityManager();
        taskRepository = new TaskRepository(entityManager);
        try {
            return Objects.requireNonNull(taskRepository).findOneByName(userId, name);
        }catch (NoResultException e) {
            return null;
        }
    }

    @Nullable
    @Override
    public Task findOneByDescription(@Nullable final String userId, @NotNull final String description) {
        @NotNull final EntityManager entityManager = entityManagerFactory.createEntityManager();
        taskRepository = new TaskRepository(entityManager);
        try {
            return Objects.requireNonNull(taskRepository).findOneByDescription(Objects.requireNonNull(userId), description);
        }catch (NoResultException e) {
            return null;
        }
    }

    @Nullable
    @Override
    public List<Task> findAllInProject(@NotNull final String userId, @NotNull final String projectId) {
        @NotNull final EntityManager entityManager = entityManagerFactory.createEntityManager();
        taskRepository = new TaskRepository(entityManager);
        try {
            return Objects.requireNonNull(taskRepository).findAllByProjectId(userId, projectId);
        }catch (NoResultException e) {
            return null;
        }
    }

    @Nullable
    public Task findOne(@NotNull final String id, @NotNull final String userId) {
        @NotNull final EntityManager entityManager = entityManagerFactory.createEntityManager();
        taskRepository = new TaskRepository(entityManager);
        try {
            return Objects.requireNonNull(taskRepository).findOne(id, userId);
        }catch (NoResultException e) {
            return null;
        }
    }

    @Nullable
    private Status createStatus(String string) {
        switch (string) {
            case "Запланировано":
                return Status.SCHEDULED;
            case "В процессе":
                return Status.IN_PROCESS;
            case "Готово":
                return Status.COMPLETE;
        }
        return null;
    }
}
