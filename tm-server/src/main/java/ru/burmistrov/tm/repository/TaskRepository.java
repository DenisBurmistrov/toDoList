package ru.burmistrov.tm.repository;

import org.apache.ibatis.annotations.*;
import org.jetbrains.annotations.NotNull;
import ru.burmistrov.tm.entity.Task;
import ru.burmistrov.tm.entity.enumerated.Status;

import java.util.Date;
import java.util.List;

public interface TaskRepository {

    @Insert("INSERT INTO tm.app_task " +
            "(id, dateBegin, dateEnd, description, name, project_id, user_id, status)" +
            " VALUES (#{id}, #{dateBegin}, #{dateEnd}, #{description}, #{name}, #{projectId}, #{userId}, #{status})")
    void persist(@NotNull @Param("id") final String id, @NotNull @Param("userId") final String userId,
                 @NotNull @Param("projectId") final String projectId, @NotNull @Param("dateBegin") final Date dateBegin,
                 @NotNull @Param("dateEnd") final Date dateEnd, @NotNull @Param("description") final String description,
                 @NotNull @Param("name") final String name, @NotNull @Param("status") final Status status);

    @Update("UPDATE tm.app_task SET name = #{name}, description = #{description}," +
            " dateBegin = #{dateBegin}, dateEnd = #{dateEnd} WHERE id = #{id}")
    void merge(@NotNull final Task task);

    @Delete("DELETE from tm.app_task WHERE id = #{id} AND user_id = #{userId}")
    int remove(@NotNull @Param("id") final String id, @NotNull @Param("userId") final String userId);

    @Delete("DELETE from tm.app_task WHERE user_id = #{userId}")
    void removeAll(@NotNull @Param("userId") final String userId);

    @Select("SELECT * FROM tm.app_task WHERE user_id = #{userId}")
    @Results(value = {
            @Result(property = "projectId", column = "project_id"),
            @Result(property = "userId", column = "user_id"),
    })
    List<Task> findAll(@NotNull @Param("userId") final String userId);


    @Select("SELECT * FROM tm.app_task WHERE id = #{id} AND user_id = #{userId}")
    @Results(value = {
            @Result(property = "projectId", column = "project_id"),
            @Result(property = "userId", column = "user_id"),
    })
    Task findOne(@NotNull @Param("id") final String id, @NotNull @Param("userId") final String userId);

    @Select("SELECT * FROM tm.app_task WHERE user_id = #{userId} AND name = #{name}")
    @Results(value = {
            @Result(property = "projectId", column = "project_id"),
            @Result(property = "userId", column = "user_id"),
    })
    Task findOneByName(@NotNull @Param("userId") final String userId, @NotNull @Param("name") final String name);

    @Select("SELECT * FROM tm.app_task WHERE user_id = #{userId} AND description = #{description}")
    @Results(value = {
            @Result(property = "projectId", column = "project_id"),
            @Result(property = "userId", column = "user_id"),
    })
    Task findOneByDescription(@NotNull @Param("userId") final String userId, @NotNull @Param("description") final String description);

    @Delete("DELETE from tm.app_task WHERE user_id = #{userId} AND project_id = #{projectId}")
    void removeAllInProject(@NotNull @Param("userId") final String userId,
                            @NotNull @Param("projectId") final String projectId);

    @Select("SELECT * FROM tm.app_task WHERE user_id = #{userId} AND project_id = #{projectId}")
    @Results(value = {
            @Result(property = "projectId", column = "project_id"),
            @Result(property = "userId", column = "user_id"),
    })
    List<Task> findAllByProjectId(@NotNull @Param("userId") final String userId, @NotNull @Param("projectId") final String projectId);
}
