package ru.burmistrov.tm.api.repository;

import org.apache.ibatis.annotations.*;
import org.jetbrains.annotations.NotNull;
import ru.burmistrov.tm.entity.Task;
import ru.burmistrov.tm.entity.enumerated.Status;

import java.util.Date;
import java.util.List;

public interface ITaskRepository {

    @NotNull
    String persist = "INSERT INTO tm.app_task " +
            "(id, dateBegin, dateEnd, description, name, project_id, user_id, status)" +
            " VALUES (#{id}, #{dateBegin}, #{dateEnd}, #{description}, #{name}, #{projectId}, #{userId}, #{status})";

    @NotNull String merge = "UPDATE tm.app_task SET name = #{name}, description = #{description}," +
            " dateBegin = #{dateBegin}, dateEnd = #{dateEnd} WHERE id = #{id} ";

    @NotNull String deleteById = "DELETE from tm.app_task WHERE id = #{id} AND user_id = #{userId}";

    @NotNull String deleteAllByUserId = "DELETE from tm.app_task WHERE user_id = #{userId}";

    @NotNull String deleteAllByProjectId = "DELETE from tm.app_task WHERE user_id = #{userId} AND project_id = #{projectId}";

    @NotNull String findAllByUserId = "SELECT * FROM tm.app_task WHERE user_id = #{userId}";

    @NotNull String findAllByProjectId = "SELECT * FROM tm.app_task WHERE user_id = #{userId} AND project_id = #{projectId}";

    @NotNull String findOneById = "SELECT * FROM tm.app_task WHERE id = #{id} AND user_id = #{userId}";

    @NotNull String findOneByName = "SELECT * FROM tm.app_task WHERE user_id = #{userId} AND name = #{name}";

    @NotNull String findOneByDescription = "SELECT * FROM tm.app_task WHERE user_id = #{userId} AND description = #{description}";

    @Insert(persist)
    void persist(@NotNull @Param("id") final String id, @NotNull @Param("userId") final String userId,
                 @NotNull @Param("projectId") final String projectId, @NotNull @Param("dateBegin") final Date dateBegin,
                 @NotNull @Param("dateEnd") final Date dateEnd, @NotNull @Param("description") final String description,
                 @NotNull @Param("name") final String name, @NotNull @Param("status") final Status status);

    @Update(merge)
    void merge(@NotNull final Task task);

    @Delete(deleteById)
    int remove(@NotNull @Param("id") final String id, @NotNull @Param("userId") final String userId);

    @Delete(deleteAllByUserId)
    void removeAll(@NotNull @Param("userId") final String userId);

    @Select(findAllByUserId)
    @Results(value = {
            @Result(property = "id", column = "id"),
            @Result(property = "dateBegin", column = "dateBegin"),
            @Result(property = "dateEnd", column = "dateEnd"),
            @Result(property = "description", column = "description"),
            @Result(property = "name", column = "name"),
            @Result(property = "projectId", column = "project_id"),
            @Result(property = "userId", column = "user_id"),
            @Result(property = "status", column = "status")
            })
    List<Task> findAll(@NotNull @Param("userId") final String userId);


    @Select(findOneById)
    @Results(value = {
            @Result(property = "id", column = "id"),
            @Result(property = "dateBegin", column = "dateBegin"),
            @Result(property = "dateEnd", column = "dateEnd"),
            @Result(property = "description", column = "description"),
            @Result(property = "name", column = "name"),
            @Result(property = "projectId", column = "project_id"),
            @Result(property = "userId", column = "user_id"),
            @Result(property = "status", column = "status")})
    Task findOne(@NotNull @Param("id") final String id, @NotNull @Param("userId") final String userId);

    @Select(findOneByName)
    @Results(value = {
            @Result(property = "id", column = "id"),
            @Result(property = "dateBegin", column = "dateBegin"),
            @Result(property = "dateEnd", column = "dateEnd"),
            @Result(property = "description", column = "description"),
            @Result(property = "name", column = "name"),
            @Result(property = "projectId", column = "project_id"),
            @Result(property = "userId", column = "user_id"),
            @Result(property = "status", column = "status")})
    Task findOneByName(@NotNull @Param("userId") final String userId, @NotNull @Param("name") final String name);

    @Select(findOneByDescription)
    @Results(value = {
            @Result(property = "id", column = "id"),
            @Result(property = "dateBegin", column = "dateBegin"),
            @Result(property = "dateEnd", column = "dateEnd"),
            @Result(property = "description", column = "description"),
            @Result(property = "name", column = "name"),
            @Result(property = "projectId", column = "project_id"),
            @Result(property = "userId", column = "user_id"),
            @Result(property = "status", column = "status")})
    Task findOneByDescription(@NotNull @Param("userId") final String userId, @NotNull @Param("description") final String description);

    @Delete(deleteAllByProjectId)
    void removeAllInProject(@NotNull @Param("userId") final String userId,
                            @NotNull @Param("projectId") final String projectId);

    @Select(findAllByProjectId)
    @Results(value = {
            @Result(property = "id", column = "id"),
            @Result(property = "dateBegin", column = "dateBegin"),
            @Result(property = "dateEnd", column = "dateEnd"),
            @Result(property = "description", column = "description"),
            @Result(property = "name", column = "name"),
            @Result(property = "projectId", column = "project_id"),
            @Result(property = "userId", column = "user_id"),
            @Result(property = "status", column = "status")
    })
    List<Task> findAllByProjectId(@NotNull @Param("userId") final String userId, @NotNull @Param("projectId") final String projectId);
}
