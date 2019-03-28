package ru.burmistrov.tm.repository;


import org.apache.ibatis.annotations.*;
import org.jetbrains.annotations.NotNull;
import ru.burmistrov.tm.entity.Project;
import ru.burmistrov.tm.entity.enumerated.Status;

import java.util.Date;
import java.util.List;

public interface ProjectRepository {

    @Insert("INSERT INTO tm.app_project " +
            "(id, dateBegin, dateEnd, description, name, user_id, status)" +
            " VALUES (#{id}, #{dateBegin}, #{dateEnd}, #{description}, #{name}, #{userId}, #{status})")
    void persist(@NotNull @Param("id") final String id, @NotNull @Param("userId") final String userId,
                 @NotNull @Param("dateBegin") final Date dateBegin, @NotNull @Param("dateEnd") final Date dateEnd,
                 @NotNull @Param("description") final String description, @NotNull @Param("name") final String name,
                 @NotNull @Param("status") final Status status);

    @Update("UPDATE tm.app_project SET name = #{name}, description = #{description}," +
            " dateBegin = #{dateBegin}, dateEnd = #{dateEnd} WHERE id = #{id}")
    void merge(@NotNull final Project project);

    @Delete("DELETE from tm.app_project WHERE id = #{id} AND user_id = #{userId}")
    void remove(@NotNull @Param("userId") final String userId, @NotNull @Param("id") final String id);

    @Delete("DELETE from tm.app_project WHERE user_id = #{userId}")
    void removeAll(@NotNull @Param("userId") final String userId);

    @Select("SELECT * FROM tm.app_project WHERE user_id = #{userId}")
    @Results(value = {
            @Result(property = "userId", column = "user_id")})
    List<Project> findAll(@NotNull @Param("userId") final String userId);

    @Select("SELECT * FROM tm.app_project WHERE id = #{id} AND user_id = #{userId}")
    @Results(value = {
            @Result(property = "userId", column = "user_id")})
    Project findOne(@NotNull @Param("id") final String id, @NotNull @Param("userId") final String userId);

    @Select("SELECT * FROM tm.app_project WHERE user_id = #{userId} AND name = #{name}")
    @Results(value = {
            @Result(property = "userId", column = "user_id")})
    Project findOneByName(@NotNull @Param("userId") final String userId, @NotNull @Param("name") final String name);

    @Select("SELECT * FROM tm.app_project WHERE user_id = #{userId} AND description = #{description}")
    @Results(value = {
            @Result(property = "userId", column = "user_id")})
    Project findOneByDescription(@NotNull @Param("userId") final String userId, @NotNull @Param("description") final String description);


}
