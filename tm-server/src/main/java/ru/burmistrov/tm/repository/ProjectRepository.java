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

@NoArgsConstructor
public final class ProjectRepository extends AbstractRepository<Project> implements IProjectRepository {

    @Nullable
    private Connection connection;

    public ProjectRepository(@Nullable final Connection connection) {
        this.connection = connection;
    }

    @NotNull
    @Override
    public Project persist(@NotNull final Project abstractEntity) throws IOException, NoSuchAlgorithmException, SQLException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String query = "INSERT INTO tm.app_project (id, dateBegin, dateEnd, description, name, user_id) \n" +
                " VALUES ('" + Objects.requireNonNull(abstractEntity).getId() + "', '" + sdf.format(abstractEntity.getDateBegin()) + "', '"
                + sdf.format(abstractEntity.getDateEnd()) + "', '" + abstractEntity.getDescription() + "', '" + abstractEntity.getName() + "', '" + abstractEntity.getUserId() + "');";
        Statement statement = Objects.requireNonNull(connection).createStatement();
        int resultSet = statement.executeUpdate(query);

        return abstractEntity;
    }

    @Override
    public void merge(@NotNull Project abstractEntity) throws SQLException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String query = "UPDATE tm.app_project SET " +
                "name = '" + abstractEntity.getName() + "', " +
                "description = '" + abstractEntity.getDescription() + "', " +
                "dateBegin = '" + sdf.format(abstractEntity.getDateBegin()) + "', " +
                "dateEnd = '" + sdf.format(abstractEntity.getDateEnd()) + "'  " +
                "WHERE id = '" + abstractEntity.getId() + "'";
        Statement statement = Objects.requireNonNull(connection).createStatement();
        int resultSet = statement.executeUpdate(query);
    }

    @Override
    public void remove(@NotNull Project abstractEntity) throws SQLException {
        String query = "DELETE FROM tm.app_project " +
                "WHERE id = '" + abstractEntity.getId() + "'";
        Statement statement = Objects.requireNonNull(connection).createStatement();
        int resultSet = statement.executeUpdate(query);
    }

    @Override
    public void removeAll(@NotNull Project abstractEntity) throws SQLException {
        String query = "DELETE FROM tm.app_project " +
                "WHERE user_id = '" + abstractEntity.getUserId() + "'";
        Statement statement = Objects.requireNonNull(connection).createStatement();
        int resultSet = statement.executeUpdate(query);
    }

    @NotNull
    @Override
    public List<Project> findAll(@NotNull Project abstractEntity) throws SQLException {
        @NotNull final String query =
                "SELECT * FROM app_project WHERE user_id = ?";
        @NotNull final PreparedStatement statement =
                Objects.requireNonNull(connection).prepareStatement(query);
        statement.setString(1, abstractEntity.getUserId());
        @NotNull final ResultSet resultSet = statement.executeQuery();
        @NotNull final List<Project> result = new ArrayList<>();
        while (resultSet.next()) result.add(fetch(resultSet));
        statement.close();
        return result;
    }

    @NotNull
    @Override
    public Project findOne(@NotNull Project abstractEntity) throws SQLException {
        @NotNull final String query =
                "SELECT * FROM app_project WHERE id = ?";
        @NotNull final PreparedStatement statement =
                Objects.requireNonNull(connection).prepareStatement(query);
        statement.setString(1, abstractEntity.getId());
        @NotNull final ResultSet resultSet = statement.executeQuery();
        @NotNull final Project project = Objects.requireNonNull(fetch(resultSet));
        statement.close();
        return project;
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
    public List<Project> findAllSortByDateBegin(@NotNull final Project abstractEntity) throws SQLException {
        @NotNull final List<Project> result = Objects.requireNonNull(findAll(abstractEntity));
        result.sort((s1, s2) -> {
            boolean firstDateMoreThanSecond = s1.getDateBegin().getTime() - s2.getDateBegin().getTime() < 0;
            boolean secondDateMareFirst = s1.getDateBegin().getTime() - s2.getDateBegin().getTime() > 0;

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
    public List<Project> findAllSortByDateEnd(@NotNull final Project abstractEntity) throws SQLException {
        @NotNull final List<Project> result = Objects.requireNonNull(findAll(abstractEntity));
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
    public List<Project> findAllSortByStatus(@NotNull final Project abstractEntity) throws SQLException {
        @NotNull final List<Project> result = Objects.requireNonNull(findAll(abstractEntity));
        result.sort((s1, s2) -> Integer.compare(0, s1.getStatus().ordinal() - s2.getStatus().ordinal()));
        return result;
    }

    @NotNull
    @Override
    public Project findOneByName(@NotNull final Project abstractEntity) throws SQLException {
        @NotNull final String query =
                "SELECT * FROM app_project WHERE user_id = ? AND name = ?";
        @NotNull final PreparedStatement statement =
                Objects.requireNonNull(connection).prepareStatement(query);
        statement.setString(1, abstractEntity.getUserId());
        statement.setString(2, abstractEntity.getName());
        @NotNull final ResultSet resultSet = statement.executeQuery();
        @NotNull final Project project = Objects.requireNonNull(fetch(resultSet));
        statement.close();
        return project;
    }

    @NotNull
    @Override
    public Project findOneByDescription(@NotNull final Project abstractEntity) throws SQLException {
        @NotNull final String query =
                "SELECT * FROM app_project WHERE user_id = ? AND description = ?";
        @NotNull final PreparedStatement statement =
                Objects.requireNonNull(connection).prepareStatement(query);
        statement.setString(1, abstractEntity.getUserId());
        statement.setString(2, abstractEntity.getDescription());
        @NotNull final ResultSet resultSet = statement.executeQuery();
        @NotNull final Project project = Objects.requireNonNull(fetch(resultSet));
        statement.close();
        return project;
    }
}