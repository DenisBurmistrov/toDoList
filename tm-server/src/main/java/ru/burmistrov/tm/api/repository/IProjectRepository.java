package ru.burmistrov.tm.api.repository;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.burmistrov.tm.entity.AbstractEntity;
import ru.burmistrov.tm.entity.Project;

import java.io.IOException;
import java.util.List;

public interface IProjectRepository{

    @Nullable
    Project persist(@NotNull Project abstractEntity) throws IOException;

    void merge(@NotNull Project abstractEntity);

    void remove(@NotNull Project abstractEntity);

    void removeAll(@NotNull Project abstractEntity);

    @NotNull List<Project> findAll(@NotNull Project abstractEntity);

    @Nullable Project findOne(@NotNull Project abstractEntity);

    @NotNull List<Project> findAllSortByDateBegin(@NotNull Project abstractEntity);

    @NotNull List<Project> findAllSortByDateEnd(@NotNull Project abstractEntity);

    @NotNull List<Project> findAllSortByStatus(@NotNull Project abstractEntity);

    @Nullable Project findOneByName(@NotNull Project abstractEntity);

    @Nullable Project findOneByDescription(@NotNull Project abstractEntity);
}
