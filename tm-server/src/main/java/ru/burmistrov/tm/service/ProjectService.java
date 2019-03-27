package ru.burmistrov.tm.service;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.burmistrov.tm.api.repository.IProjectRepository;
import ru.burmistrov.tm.api.repository.ITaskRepository;
import ru.burmistrov.tm.api.service.IProjectService;
import ru.burmistrov.tm.entity.AbstractEntity;
import ru.burmistrov.tm.entity.Project;
import ru.burmistrov.tm.entity.enumerated.Status;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public final class ProjectService implements IProjectService {

    @Nullable
    private IProjectRepository projectRepository;

    @Nullable
    private ITaskRepository taskRepository;

    @NotNull
    private final SqlSessionFactory sqlSessionFactory;

    public ProjectService(@NotNull final SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    @Override
    public void remove(@NotNull final String userId, @NotNull final String projectId) throws NullPointerException {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            try {
                projectRepository = session.getMapper(IProjectRepository.class);
                @Nullable final AbstractEntity abstractEntity = projectRepository.findOne(projectId, userId);
                if (abstractEntity != null) {
                    Objects.requireNonNull(taskRepository).removeAllInProject(userId, projectId);
                    Objects.requireNonNull(projectRepository).remove(userId, projectId);
                    Objects.requireNonNull(session).commit();
                }
            } catch (Exception e) {
                session.rollback();
            }
        }
    }

    @Override
    public Project persist(@NotNull final String userId, @NotNull final String name, @NotNull final String description,
                           @NotNull final String dateEndString, @NotNull final String status) throws NullPointerException {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            try {
                projectRepository = session.getMapper(IProjectRepository.class);
                @NotNull final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");
                @NotNull final Date dateEnd = simpleDateFormat.parse(dateEndString);
                @Nullable final AbstractEntity abstractEntity = Objects.requireNonNull(projectRepository).findOneByName(userId, name);
                if (abstractEntity == null) {
                    @NotNull final Project project = new Project();
                    project.setUserId(userId);
                    project.setDateBegin(new Date());
                    project.setDateEnd(dateEnd);
                    project.setName(name);
                    project.setDescription(description);
                    project.setStatus(createStatus(status));

                    Objects.requireNonNull(projectRepository).persist(project.getId(), Objects.requireNonNull(project.getUserId()),
                            project.getDateBegin(), Objects.requireNonNull(project.getDateEnd()), Objects.requireNonNull(project.getDescription()),
                            Objects.requireNonNull(project.getName()), Objects.requireNonNull(project.getStatus()));
                    Objects.requireNonNull(session).commit();
                    return project;
                }
            } catch (Exception e) {
                session.rollback();
            }
        }
        return null;
    }

    @Override
    public void merge(@NotNull final String userId, @NotNull final String projectId, @NotNull final String name,
                      @NotNull final String description, @NotNull final String dateEndString, @NotNull final String status) throws NullPointerException {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            try {
                projectRepository = session.getMapper(IProjectRepository.class);
                @NotNull final Project project = new Project();
                project.setId(projectId);
                project.setUserId(userId);
                project.setName(name);
                project.setDescription(description);
                project.setStatus(createStatus(status));

                @NotNull final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy"); //dd-MM-yyyy
                @NotNull final Date dateEnd = simpleDateFormat.parse(dateEndString);
                project.setDateEnd(dateEnd);
                @Nullable final AbstractEntity abstractEntity =
                        Objects.requireNonNull(projectRepository).findOne(project.getId(), Objects.requireNonNull(project.getUserId()));
                if (abstractEntity != null) {
                    Objects.requireNonNull(projectRepository).merge(project);
                    Objects.requireNonNull(session).commit();
                }
            } catch (Exception e) {
                session.rollback();
            }
        }

    }

    @Override
    public void removeAll(@Nullable final String userId) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            try {
                projectRepository = session.getMapper(IProjectRepository.class);
                taskRepository = session.getMapper(ITaskRepository.class);
                Objects.requireNonNull(taskRepository).removeAll(Objects.requireNonNull(userId));
                Objects.requireNonNull(projectRepository).removeAll(userId);
                Objects.requireNonNull(session).commit();
            } catch (Exception e) {
                session.rollback();
            }
        }
    }

    @Override
    @Nullable
    public List<Project> findAll(@NotNull final String userId) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            try {
                projectRepository = session.getMapper(IProjectRepository.class);
                return Objects.requireNonNull(projectRepository).findAll(userId);
            }
            catch (Exception e) {
                session.rollback();
            }
        }
        return null;
    }

    @NotNull
    @Override
    public List<Project> findAllSortByDateBegin(@Nullable final String userId) {
        @NotNull final List<Project> result = Objects.requireNonNull(findAll(Objects.requireNonNull(userId)));
        result.sort((s1, s2) -> {
            @NotNull final boolean firstDateMoreThanSecond = s1.getDateBegin().getTime() - s2.getDateBegin().getTime() < 0;
            @NotNull final boolean secondDateMareFirst = s1.getDateBegin().getTime() - s2.getDateBegin().getTime() > 0;

            if (firstDateMoreThanSecond) {
                return 1;
            } else if (secondDateMareFirst) {
                return -1;
            } else {
                return 0;
            }
        });
        return result;
    }

    @NotNull
    @Override
    public List<Project> findAllSortByDateEnd(@Nullable final String userId) {
        @NotNull final List<Project> result = Objects.requireNonNull(findAll(Objects.requireNonNull(userId)));
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
    public List<Project> findAllSortByStatus(@NotNull final String userId) {
        @NotNull final List<Project> result = Objects.requireNonNull(findAll(userId));
        result.sort((s1, s2) -> Integer.compare(0, Objects.requireNonNull(s1.getStatus()).ordinal() - Objects.requireNonNull(s2.getStatus()).ordinal()));
        return result;
    }

    @Nullable
    @Override
    public Project findOneByName(@Nullable final String userId, @NotNull final String name) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            try {
                projectRepository = session.getMapper(IProjectRepository.class);
                return Objects.requireNonNull(projectRepository).findOneByName(Objects.requireNonNull(userId), name);
            } catch (Exception e) {
                session.rollback();
            }
        }
        return null;
    }

    @Nullable
    @Override
    public Project findOneByDescription(@Nullable final String userId, @NotNull final String description) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            try {
                projectRepository = session.getMapper(IProjectRepository.class);
                return Objects.requireNonNull(projectRepository).findOneByDescription(Objects.requireNonNull(userId), description);
            } catch (Exception e) {
                session.rollback();
            }
        }
        return null;
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
