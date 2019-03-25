package ru.burmistrov.tm.repository;

import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.burmistrov.tm.api.repository.ITaskRepository;
import ru.burmistrov.tm.entity.Project;
import ru.burmistrov.tm.entity.Task;
import ru.burmistrov.tm.entity.enumerated.FieldConst;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

@NoArgsConstructor
public final class TaskRepository extends AbstractRepository<Task> implements ITaskRepository {

    @Nullable
    private Connection connection;

    public TaskRepository(@Nullable Connection connection) {
        this.connection = connection;
    }

    @Nullable
    @SneakyThrows
    private Task fetch(@Nullable final ResultSet row) {
        if (row == null) return null;
        @NotNull final Task task = new Task();
        task.setId(row.getString(FieldConst.ID));
        task.setDateBegin(row.getDate(FieldConst.DATE_BEGIN));
        task.setDateEnd(row.getDate(FieldConst.DATE_END));
        task.setDescription(row.getString(FieldConst.DESCRIPTION));
        task.setName(row.getString(FieldConst.NAME));
        task.setProjectId(row.getString(FieldConst.PROJECT_ID));
        task.setUserId(row.getString(FieldConst.USER_ID));
        return task;
    }


    @NotNull
    @Override
    public List<Task> findAllInProject(@NotNull final String userId, @NotNull final String projectId) throws SQLException {
        @NotNull final String query =
                "SELECT * FROM tm.app_task WHERE user_id = ? AND project_id = ?";
        @NotNull final PreparedStatement statement =
                Objects.requireNonNull(connection).prepareStatement(query);
        statement.setString(1, userId);
        statement.setString(2, projectId);
        @NotNull final ResultSet resultSet = statement.executeQuery();
        @NotNull final List<Task> result = new ArrayList<>();
        while (resultSet.next()) result.add(fetch(resultSet));
        statement.close();
        return result;
    }

    @Nullable
    @Override
    public Task findOne(@NotNull final String id, @NotNull final String userId) throws SQLException {
        @NotNull final String query =
                "SELECT * FROM tm.app_task WHERE id = ? AND user_id = ?";
        @NotNull final PreparedStatement statement =
                Objects.requireNonNull(connection).prepareStatement(query);
        statement.setString(1, id);
        statement.setString(1, userId);
        @NotNull final ResultSet resultSet = statement.executeQuery();
        if(resultSet.next()) {
            @NotNull final Task task = Objects.requireNonNull(fetch(resultSet));
            statement.close();
            return task;
        }
        return null;
    }

    @NotNull
    @Override
    public Task persist(@NotNull final String userId, @NotNull final Date dateBegin,
                        @NotNull final Date dateEnd, @NotNull final String description,
                        @NotNull final String name, @NotNull final String projectId) throws SQLException {

        @NotNull final Task task = new Task();
        task.setUserId(userId);
        task.setDateBegin(dateBegin);
        task.setDateEnd(dateEnd);
        task.setDescription(description);
        task.setName(name);
        task.setProjectId(projectId);
        @NotNull final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        @NotNull final String query = "INSERT INTO tm.app_task (id, dateBegin, dateEnd, description, name, project_id, user_id) \n" +
                " VALUES ('" + task.getId() + "', '" + sdf.format(dateBegin) + "', '" + sdf.format(dateEnd) + "', '" +
                description + "', '" + name + "', '" + projectId +
                "', '" + userId + "');";
        @NotNull final Statement statement = Objects.requireNonNull(connection).createStatement();
        statement.executeUpdate(query);

        return task;
    }

    @Override
    public void merge(@NotNull Task abstractEntity) throws SQLException {
        @NotNull final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        @NotNull final String query = "UPDATE tm.app_task SET " +
                "name = '" + abstractEntity.getName() + "', " +
                "description = '" + abstractEntity.getDescription() + "', " +
                "dateBegin = '" + sdf.format(abstractEntity.getDateBegin()) + "', " +
                "dateEnd = '" + sdf.format(abstractEntity.getDateEnd()) + "'  " +
                "WHERE id = '" + abstractEntity.getId() + "'";
        @NotNull final Statement statement = Objects.requireNonNull(connection).createStatement();
        statement.executeUpdate(query);
    }

    @Override
    public void remove(@NotNull final String id, @NotNull final String userId) throws SQLException {
        @NotNull final String query = "DELETE FROM tm.app_task " +
                "WHERE id = '" + id + "' AND user_id = '" + userId + "'";
        @NotNull final Statement statement = Objects.requireNonNull(connection).createStatement();
        statement.executeUpdate(query);
    }

    @Override
    public void removeAll(@NotNull final String userId) throws SQLException {
        @NotNull final String query = "DELETE FROM tm.app_task " +
                "WHERE user_id = '" + userId + "'";
        @NotNull final Statement statement = Objects.requireNonNull(connection).createStatement();
        statement.executeUpdate(query);
    }

    @NotNull
    @Override
    public List<Task> findAll(@NotNull final String userId) throws SQLException {
        @NotNull final String query =
                "SELECT * FROM tm.app_task WHERE user_id = ?";
        @NotNull final PreparedStatement statement =
                Objects.requireNonNull(connection).prepareStatement(query);
        statement.setString(1, userId);
        @NotNull final ResultSet resultSet = statement.executeQuery();
        @NotNull final List<Task> result = new ArrayList<>();
        while (resultSet.next()) result.add(fetch(resultSet));
        statement.close();
        return result;
    }

    @Override
    public void removeAllInProject(@NotNull final String userId, @NotNull final String projectId) throws SQLException {
        @NotNull final String query = "DELETE FROM tm.app_task " +
                "WHERE user_id = '" + userId + "' AND project_id ='" + projectId + "'";
        @NotNull final Statement statement = Objects.requireNonNull(connection).createStatement();
        statement.executeUpdate(query);
    }

    @NotNull
    @Override
    public List<Task> findAllSortByDateBegin(@NotNull final String userId) throws SQLException {
        @NotNull final List<Task> result = findAll(userId);
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
    public List<Task> findAllSortByDateEnd(@NotNull final String userId) throws SQLException {
        @NotNull final List<Task> result = findAll(userId);
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
    public List<Task> findAllSortByStatus(@NotNull final String userId) throws SQLException {
        @NotNull final List<Task> result = findAll(userId);
                result.stream().filter(e -> Objects.requireNonNull(e.getUserId()).
                equals(userId))
                .forEach(result::add);
        result.sort((s1, s2) -> Integer.compare(0, s1.getStatus().ordinal() - s2.getStatus().ordinal()));
        return result;
    }

    @Nullable
    @Override
    public Task findOneByName(@NotNull final String userId, @NotNull final String name) throws SQLException {
        @NotNull final String query =
                "SELECT * FROM tm.app_task WHERE user_id = ? AND name = ?";
        @NotNull final PreparedStatement statement =
                Objects.requireNonNull(connection).prepareStatement(query);
        statement.setString(1, userId);
        statement.setString(2, name);
        @NotNull final ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            @NotNull final Task task = Objects.requireNonNull(fetch(resultSet));
            statement.close();
            return task;
        }
        return null;
    }

    @Nullable
    @Override
    public Task findOneByDescription(@NotNull final String userId, @NotNull final String description) throws SQLException {
        @NotNull final String query =
                "SELECT * FROM tm.app_task WHERE user_id = ? AND description = ?";
        @NotNull final PreparedStatement statement =
                Objects.requireNonNull(connection).prepareStatement(query);
        statement.setString(1, userId);
        statement.setString(2, description);
        @NotNull final ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            @NotNull final Task task = Objects.requireNonNull(fetch(resultSet));
            statement.close();
            return task;
        }
        return null;
    }
}
