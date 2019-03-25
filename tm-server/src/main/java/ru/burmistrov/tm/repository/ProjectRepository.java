package ru.burmistrov.tm.repository;

import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.burmistrov.tm.api.repository.IProjectRepository;
import ru.burmistrov.tm.entity.AbstractEntity;
import ru.burmistrov.tm.entity.Project;
import ru.burmistrov.tm.entity.enumerated.FieldConst;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

@NoArgsConstructor
public final class ProjectRepository extends AbstractRepository<Project> implements IProjectRepository {

    @Nullable
    private Connection connection;

    public ProjectRepository(@Nullable final Connection connection) {
        this.connection = connection;
    }

    @NotNull
    @Override
    public Project persist(@NotNull final String userId, @NotNull final Date dateBegin,
                           @NotNull final Date dateEnd, @NotNull final String description,
                           @NotNull final String name) throws IOException, NoSuchAlgorithmException, SQLException {
        @NotNull final Project project = new Project();
        project.setUserId(userId);
        project.setDateBegin(dateBegin);
        project.setDateEnd(dateEnd);
        project.setName(name);
        project.setDescription(description);
        @NotNull final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        @NotNull final String query = "INSERT INTO tm.app_project (id, dateBegin, dateEnd, description, name, user_id) \n" +
                " VALUES ('" + project.getId() + "', '" + sdf.format(dateBegin) + "', '"
                + sdf.format(dateEnd) + "', '" + description + "', '" + name + "', '" + userId + "');";
        @NotNull final Statement statement = Objects.requireNonNull(connection).createStatement();
        statement.executeUpdate(query);

        return project;
    }

    @Override
    public void merge(@NotNull final Project project) throws SQLException {
        @NotNull final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        @NotNull final String query = "UPDATE tm.app_project SET " +
                "name = '" + project.getName() + "', " +
                "description = '" + project.getDescription() + "', " +
                "dateBegin = '" + sdf.format(project.getDateBegin()) + "', " +
                "dateEnd = '" + sdf.format(project.getDateEnd()) + "'  " +
                "WHERE id = '" + project.getId() + "'";
        @NotNull final Statement statement = Objects.requireNonNull(connection).createStatement();
        statement.executeUpdate(query);
    }

    @Override
    public void remove(@NotNull final String id, @NotNull final String userId) throws SQLException {
        @NotNull final String query = "DELETE FROM tm.app_project " +
                "WHERE id = '" + id + "' AND user_id = '" + userId + "'";
        @NotNull final Statement statement = Objects.requireNonNull(connection).createStatement();
        statement.executeUpdate(query);
    }

    @Override
    public void removeAll(@NotNull final String userId) throws SQLException {
        @NotNull final String query = "DELETE FROM tm.app_project " +
                "WHERE user_id = '" + userId + "'";
        @NotNull final Statement statement = Objects.requireNonNull(connection).createStatement();
        statement.executeUpdate(query);
    }

    @NotNull
    @Override
    public List<Project> findAll(@NotNull final String userId) throws SQLException {
        @NotNull final String query =
                "SELECT * FROM tm.app_project WHERE user_id = ?";
        @NotNull final PreparedStatement statement =
                Objects.requireNonNull(connection).prepareStatement(query);
        statement.setString(1, userId);
        @NotNull final ResultSet resultSet = statement.executeQuery();
        @NotNull final List<Project> result = new ArrayList<>();
        while (resultSet.next()) result.add(fetch(resultSet));
        statement.close();
        return result;
    }

    @Nullable
    @Override
    public Project findOne(@NotNull final String id, @NotNull final String userId) throws SQLException {
        @NotNull final String query =
                "SELECT * FROM tm.app_project WHERE id = ? AND user_id = ?";
        @NotNull final PreparedStatement statement =
                Objects.requireNonNull(connection).prepareStatement(query);
        statement.setString(1, id);
        statement.setString(2, userId);
        @NotNull final ResultSet resultSet = statement.executeQuery();
        if(resultSet.next()) {
            @NotNull final Project project = Objects.requireNonNull(fetch(resultSet));
            statement.close();
            return project;
        }
        return null;
    }

    @Nullable
    @SneakyThrows
    private Project fetch(@Nullable final ResultSet row) {
        if (row == null) return null;
        @NotNull final Project project = new Project();
        project.setId(row.getString(FieldConst.ID));
        project.setName(row.getString(FieldConst.NAME));
        project.setDescription(row.getString(FieldConst.DESCRIPTION));
        project.setDateBegin(row.getDate(FieldConst.DATE_BEGIN));
        project.setDateEnd(row.getDate(FieldConst.DATE_END));
        project.setUserId(row.getString(FieldConst.USER_ID));
        return project;
    }


    @NotNull
    @Override
    public List<Project> findAllSortByDateBegin(@NotNull final String userId) throws SQLException {
        @NotNull final List<Project> result = Objects.requireNonNull(findAll(userId));
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
    public List<Project> findAllSortByDateEnd(@NotNull final String userId) throws SQLException {
        @NotNull final List<Project> result = Objects.requireNonNull(findAll(userId));
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
    public List<Project> findAllSortByStatus(@NotNull final String userId) throws SQLException {
        @NotNull final List<Project> result = Objects.requireNonNull(findAll(userId));
        result.sort((s1, s2) -> Integer.compare(0, s1.getStatus().ordinal() - s2.getStatus().ordinal()));
        return result;
    }

    @Nullable
    @Override
    public Project findOneByName(@NotNull final String userId, @NotNull final String name) throws SQLException {
        @NotNull final String query =
                "SELECT * FROM tm.app_project WHERE user_id = ? AND name = ?";
        @NotNull final PreparedStatement statement =
                Objects.requireNonNull(connection).prepareStatement(query);
        statement.setString(1, userId);
        statement.setString(2, name);
        @NotNull final ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            @NotNull final Project project = Objects.requireNonNull(fetch(resultSet));
            statement.close();
            return project;
        }
        return null;
    }

    @Nullable
    @Override
    public Project findOneByDescription(@NotNull final String userId, @NotNull final String description) throws SQLException {
        @NotNull final String query =
                "SELECT * FROM tm.app_project WHERE user_id = ? AND description = ?";
        @NotNull final PreparedStatement statement =
                Objects.requireNonNull(connection).prepareStatement(query);
        statement.setString(1, userId);
        statement.setString(2, description);
        @NotNull final ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            @NotNull final Project project = Objects.requireNonNull(fetch(resultSet));
            statement.close();
            return project;
        }
        return null;
    }
}