package ru.burmistrov.tm.api.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.burmistrov.tm.entity.AbstractEntity;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import java.text.ParseException;
import java.util.List;

public interface ITaskService<T extends AbstractEntity> {

    void merge(@NotNull String userId,@NotNull String projectId, @NotNull String taskId, @NotNull String newName,
               @NotNull String description, @NotNull String dateEnd) throws ParseException;

    @Nullable
    T persist(@NotNull String userId, @NotNull String projectId, @NotNull String name, @NotNull String description,
              @NotNull String dateEnd) throws ParseException;

    @NotNull
    List<T> findAll(@NotNull String userId);

    void removeAllInProject(@NotNull String userId, @NotNull String projectId);

    void remove(@NotNull String userId,@NotNull String projectId,@NotNull String taskId);

    void removeAll(@NotNull String userId);

    @NotNull
    List<T> findAllSortByDateBegin(@NotNull String userId);

    @NotNull
    List<T> findAllSortByDateEnd(@NotNull String userId);

    @NotNull
    List<T> findAllSortByStatus(@NotNull String userId);

    @Nullable
    T findOneByName(@NotNull String userId, String name);

    @Nullable
    T findOneByDescription(@NotNull String userId, String description);

    @NotNull
    List<T> findAllInProject(@NotNull String userId, @NotNull String projectId);
}
