package ru.burmistrov.tm.api.repository;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.burmistrov.tm.entity.AbstractEntity;
import ru.burmistrov.tm.entity.Project;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

public interface IProjectRepository{

    @Nullable
    Project persist(@NotNull final Project abstractEntity) throws IOException, NoSuchAlgorithmException;

    void merge(@NotNull final Project abstractEntity);

    void remove(@NotNull final Project abstractEntity);

    void removeAll(@NotNull final Project abstractEntity);

    @NotNull List<Project> findAll(@NotNull final Project abstractEntity);

    @Nullable Project findOne(@NotNull final Project abstractEntity);

    @NotNull List<Project> findAllSortByDateBegin(@NotNull final Project abstractEntity);

    @NotNull List<Project> findAllSortByDateEnd(@NotNull final Project abstractEntity);

    @NotNull List<Project> findAllSortByStatus(@NotNull final Project abstractEntity);

    @Nullable Project findOneByName(@NotNull final Project abstractEntity);

    @Nullable Project findOneByDescription(@NotNull final Project abstractEntity);
}
