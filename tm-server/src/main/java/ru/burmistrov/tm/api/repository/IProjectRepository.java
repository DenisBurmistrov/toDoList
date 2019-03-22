package ru.burmistrov.tm.api.repository;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.burmistrov.tm.entity.AbstractEntity;
import ru.burmistrov.tm.entity.Project;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.List;

public interface IProjectRepository{

    @Nullable
    Project persist(@NotNull final Project abstractEntity) throws IOException, NoSuchAlgorithmException, SQLException;

    void merge(@NotNull final Project abstractEntity) throws SQLException;

    void remove(@NotNull final Project abstractEntity) throws SQLException;

    void removeAll(@NotNull final Project abstractEntity) throws SQLException;

    @NotNull List<Project> findAll(@NotNull final Project abstractEntity) throws SQLException;

    @Nullable Project findOne(@NotNull final Project abstractEntity) throws SQLException;

    @NotNull List<Project> findAllSortByDateBegin(@NotNull final Project abstractEntity) throws SQLException;

    @NotNull List<Project> findAllSortByDateEnd(@NotNull final Project abstractEntity) throws SQLException;

    @NotNull List<Project> findAllSortByStatus(@NotNull final Project abstractEntity) throws SQLException;

    @Nullable Project findOneByName(@NotNull final Project abstractEntity) throws SQLException;

    @Nullable Project findOneByDescription(@NotNull final Project abstractEntity) throws SQLException;
}
