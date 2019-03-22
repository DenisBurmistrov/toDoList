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
    public List<Task> findAllInProject(@NotNull final Task entity) throws SQLException {
        @NotNull final String query =
                "SELECT * FROM app_task WHERE user_id = ? AND WHERE project_id = ?";
        @NotNull final PreparedStatement statement =
                Objects.requireNonNull(connection).prepareStatement(query);
        statement.setString(1, entity.getUserId());
        statement.setString(2, entity.getProjectId());
        @NotNull final ResultSet resultSet = statement.executeQuery();
        @NotNull final List<Task> result = new ArrayList<>();
        while (resultSet.next()) result.add(fetch(resultSet));
        statement.close();
        return result;
    }

    @NotNull
    @Override
    public Task findOne(@NotNull Task entity) throws SQLException {
        @NotNull final String query =
                "SELECT * FROM app_task WHERE id = ?";
        @NotNull final PreparedStatement statement =
                Objects.requireNonNull(connection).prepareStatement(query);
        statement.setString(1, entity.getId());
        @NotNull final ResultSet resultSet = statement.executeQuery();
        @NotNull final Task task = Objects.requireNonNull(fetch(resultSet));
        statement.close();
        return task;
    }

    @NotNull
    @Override
    public Task persist(@NotNull Task abstractEntity) throws SQLException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String query = "INSERT INTO tm.app_task (id, dateBegin, dateEnd, description, name, project_id, user_id) \n" +
                " VALUES ('" + abstractEntity.getId() + "', '" + sdf.format(abstractEntity.getDateBegin()) + "', '" + sdf.format(abstractEntity.getDateEnd()) + "', '" +
                abstractEntity.getDescription() + "', '" + abstractEntity.getName() + "', '" + abstractEntity.getProjectId() +
                "', '" + abstractEntity.getUserId() + "');";
        Statement statement = Objects.requireNonNull(connection).createStatement();
        int resultSet = statement.executeUpdate(query);

        return abstractEntity;
    }

    @Override
    public void merge(@NotNull Task abstractEntity) throws SQLException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String query = "UPDATE tm.app_task SET " +
                "name = '" + abstractEntity.getName() + "', " +
                "description = '" + abstractEntity.getDescription() + "', " +
                "dateBegin = '" + sdf.format(abstractEntity.getDateBegin()) + "', " +
                "dateEnd = '" + sdf.format(abstractEntity.getDateEnd()) + "'  " +
                "WHERE id = '" + abstractEntity.getId() + "'";
        Statement statement = Objects.requireNonNull(connection).createStatement();
        int resultSet = statement.executeUpdate(query);
    }

    @Override
    public void remove(@NotNull Task entity) throws SQLException {
        String query = "DELETE FROM tm.app_task " +
                "WHERE id = '" + entity.getId() + "'";
        Statement statement = Objects.requireNonNull(connection).createStatement();
        int resultSet = statement.executeUpdate(query);
    }

    @Override
    public void removeAll(@NotNull Task entity) throws SQLException {
        String query = "DELETE FROM tm.app_task " +
                "WHERE user_id = '" + entity.getUserId() + "'";
        Statement statement = Objects.requireNonNull(connection).createStatement();
        int resultSet = statement.executeUpdate(query);
    }

    @NotNull
    @Override
    public List<Task> findAll(@NotNull Task entity) throws SQLException {
        @NotNull final String query =
                "SELECT * FROM app_task WHERE user_id = ?";
        @NotNull final PreparedStatement statement =
                Objects.requireNonNull(connection).prepareStatement(query);
        statement.setString(1, entity.getUserId());
        @NotNull final ResultSet resultSet = statement.executeQuery();
        @NotNull final List<Task> result = new ArrayList<>();
        while (resultSet.next()) result.add(fetch(resultSet));
        statement.close();
        return result;
    }

    @Override
    public void removeAllInProject(@NotNull final Task entity) throws SQLException {
        String query = "DELETE FROM tm.app_task " +
                "WHERE user_id = '" + entity.getUserId() + "' AND project_id ='" + entity.getProjectId() + "'";
        Statement statement = Objects.requireNonNull(connection).createStatement();
        int resultSet = statement.executeUpdate(query);
    }

    @NotNull
    @Override
    public List<Task> findAllSortByDateBegin(@NotNull final Task abstractEntity) {
        @NotNull final Task task = abstractEntity;
        @NotNull final List<Task> result = new LinkedList<>();
        map.entrySet()
                .stream().filter(e -> Objects.requireNonNull(e.getValue().getUserId()).
                equals(task.getUserId()))
                .forEach(e -> result.add(e.getValue()));
        result.sort((s1, s2) -> {
            boolean firstDateMoreThanSecond = s1.getDateBegin().getTime() - s2.getDateBegin().getTime() < 0;
            boolean secondDateMoreThaFirst = s1.getDateBegin().getTime() - s2.getDateBegin().getTime() > 0;

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
    public List<Task> findAllSortByDateEnd(@NotNull final Task abstractEntity) {
        @NotNull final Task task = abstractEntity;
        @NotNull final List<Task> result = new LinkedList<>();
        map.entrySet()
                .stream().filter(e -> Objects.requireNonNull(e.getValue().getUserId()).
                equals(task.getUserId()))
                .forEach(e -> result.add(e.getValue()));
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
    public List<Task> findAllSortByStatus(@NotNull final Task abstractEntity) throws SQLException {
        @NotNull final Task task = abstractEntity;
        @NotNull final List<Task> result = new LinkedList<>();
        findAll(abstractEntity)
                .stream().filter(e -> Objects.requireNonNull(e.getUserId()).
                equals(task.getUserId()))
                .forEach(result::add);
        result.sort((s1, s2) -> Integer.compare(0, s1.getStatus().ordinal() - s2.getStatus().ordinal()));
        return result;
    }

    @NotNull
    @Override
    public Task findOneByName(@NotNull final Task abstractEntity) throws SQLException {
        @NotNull final String query =
                "SELECT * FROM app_task WHERE user_id = ? AND name = ?";
        @NotNull final PreparedStatement statement =
                Objects.requireNonNull(connection).prepareStatement(query);
        statement.setString(1, abstractEntity.getUserId());
        statement.setString(2, abstractEntity.getName());
        @NotNull final ResultSet resultSet = statement.executeQuery();
        @NotNull final Task task = Objects.requireNonNull(fetch(resultSet));
        statement.close();
        return task;
    }

    @NotNull
    @Override
    public Task findOneByDescription(@NotNull final Task abstractEntity) throws SQLException {
        @NotNull final String query =
                "SELECT * FROM app_task WHERE user_id = ? AND description = ?";
        @NotNull final PreparedStatement statement =
                Objects.requireNonNull(connection).prepareStatement(query);
        statement.setString(1, abstractEntity.getUserId());
        statement.setString(2, abstractEntity.getDescription());
        @NotNull final ResultSet resultSet = statement.executeQuery();
        @NotNull final Task task = Objects.requireNonNull(fetch(resultSet));
        statement.close();
        return task;
    }
}
