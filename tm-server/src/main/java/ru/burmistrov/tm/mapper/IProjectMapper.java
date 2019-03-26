package ru.burmistrov.tm.mapper;

import org.apache.ibatis.annotations.*;
import org.jetbrains.annotations.NotNull;
import ru.burmistrov.tm.entity.Project;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public interface IProjectMapper {

    @NotNull String persist = "INSERT INTO tm.app_project " +
            "(id, dateBegin, dateEnd, description, name, user_id) VALUES (#{id}, #{dateBegin}, #{dateEnd}, #{description}, #{name}, #{userId})";

    @NotNull String merge = "UPDATE tm.app_project SET name = #{name}, description = #{description}," +
            " dateBegin = #{dateBegin}, dateEnd = #{dateEnd} WHERE id = #{id}";

    @NotNull String deleteById = "DELETE from tm.app_project WHERE id = #{id} AND user_id = #{userId}";

    @NotNull String deleteAllByUserId = "DELETE from tm.app_project WHERE user_id = #{userId}";

    @NotNull String findAllByUserId = "SELECT * FROM tm.app_project WHERE user_id = #{userId}";

    @NotNull String findOneById = "SELECT * FROM tm.app_project WHERE id = #{id} AND user_id = #{userId}";

    @NotNull String findOneByName = "SELECT * FROM tm.app_project WHERE user_id = #{userId} AND name = #{name}";

    @NotNull String findOneByDescription = "SELECT * FROM tm.app_project WHERE user_id = #{userId} AND description = #{description}";

    @Insert(persist)
    @Results(value = {
            @Result(property = "id", column = "id"),
            @Result(property = "dateBegin", column = "dateBegin"),
            @Result(property = "dateEnd", column = "dateEnd"),
            @Result(property = "description", column = "description"),
            @Result(property = "name", column = "name"),
            @Result(property = "userId", column = "user_id"),
            })
    Project persist(@NotNull @Param("id") final String id, @NotNull @Param("userId") final String userId,
                    @NotNull @Param("dateBegin") final Date dateBegin, @NotNull @Param("dateEnd") final Date dateEnd,
                    @NotNull @Param("description") final String description, @NotNull @Param("name") final String name);

    @Update(merge)
    void merge(@NotNull final Project project);

    @Delete(deleteById)
    int remove(@NotNull @Param("id") final String id, @NotNull @Param("userId") final String userId);

    @Delete(deleteAllByUserId)
    void removeAll(@NotNull final String userId);

    @Select(findAllByUserId)
    @Results(value = {
            @Result(property = "id", column = "id"),
            @Result(property = "dateBegin", column = "dateBegin"),
            @Result(property = "dateEnd", column = "dateEnd"),
            @Result(property = "description", column = "description"),
            @Result(property = "name", column = "name"),
            @Result(property = "userId", column = "user_id")})
    List<Project> findAll(@NotNull final String userId);


    @Select(findOneById)
    @Results(value = {
            @Result(property = "id", column = "id"),
            @Result(property = "dateBegin", column = "dateBegin"),
            @Result(property = "dateEnd", column = "dateEnd"),
            @Result(property = "description", column = "description"),
            @Result(property = "name", column = "name"),
            @Result(property = "userId", column = "user_id")})
    Project findOne(@NotNull final String id, @NotNull final String userId);

    @Select(findOneByName)
    @Results(value = {
            @Result(property = "id", column = "id"),
            @Result(property = "dateBegin", column = "dateBegin"),
            @Result(property = "dateEnd", column = "dateEnd"),
            @Result(property = "description", column = "description"),
            @Result(property = "name", column = "name"),
            @Result(property = "userId", column = "user_id")})
    Project findOneByName(@NotNull @Param("userId") final String userId, @NotNull @Param("name") final String name);

    @Select(findOneByDescription)
    @Results(value = {
            @Result(property = "id", column = "id"),
            @Result(property = "dateBegin", column = "dateBegin"),
            @Result(property = "dateEnd", column = "dateEnd"),
            @Result(property = "description", column = "description"),
            @Result(property = "name", column = "name"),
            @Result(property = "userId", column = "user_id")})
    Project findOneByDescription(@NotNull final String userId, @NotNull final String description);

 /*
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
*/
}
