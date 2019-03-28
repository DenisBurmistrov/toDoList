package ru.burmistrov.tm.repository;

import org.apache.ibatis.annotations.*;
import org.jetbrains.annotations.NotNull;
import ru.burmistrov.tm.entity.User;
import ru.burmistrov.tm.entity.enumerated.Role;

import java.util.List;

public interface UserRepository {

    @Insert("INSERT INTO tm.app_user " +
            "(id, email, firstName, lastName, login, middleName, passwordHash, role) VALUES (#{id}, #{email}, #{firstName}, #{lastName}, #{login}, #{middleName}, #{passwordHash}, #{role})")
    void persist(@NotNull @Param("id") final String id, @NotNull @Param("email") final String email,
                 @NotNull @Param("firstName") final String firstName, @NotNull @Param("lastName") final String lastName,
                 @NotNull @Param("login") final String login, @NotNull @Param("middleName") final String middleName,
                 @NotNull @Param("passwordHash") final String passwordHash, @NotNull @Param("role") final Role role);

    @Update("UPDATE tm.app_task SET firstName = #{firstName}, lastName = #{lastName}," +
            " middleName = #{middleName}, email = #{email} WHERE id = #{id}")
    void merge(@NotNull final User user);

    @Delete("DELETE from tm.app_user WHERE id = #{id}")
    void remove(@NotNull @Param("id") final String id);

    @Delete("DELETE from tm.app_user")
    void removeAll();

    @Select("SELECT * FROM tm.app_user")
    @Results(value = {
            @Result(property = "id", column = "id"),
            @Result(property = "email", column = "email"),
            @Result(property = "firstName", column = "firstName"),
            @Result(property = "lastName", column = "lastName"),
            @Result(property = "login", column = "login"),
            @Result(property = "middleName", column = "middleName"),
            @Result(property = "password", column = "passwordHash"),
            @Result(property = "role", column = "role")
    })
    List<User> findAll();


    @Select("SELECT * FROM tm.app_user WHERE id = #{id}")
    @Results(value = {
            @Result(property = "id", column = "id"),
            @Result(property = "email", column = "email"),
            @Result(property = "firstName", column = "firstName"),
            @Result(property = "lastName", column = "lastName"),
            @Result(property = "login", column = "login"),
            @Result(property = "middleName", column = "middleName"),
            @Result(property = "passwordHash", column = "passwordHash"),
            @Result(property = "role", column = "role")})
    User findOne(@NotNull final String id);

    @Select("SELECT * FROM tm.app_user WHERE login = #{login}")
    @Results(value = {
            @Result(property = "id", column = "id"),
            @Result(property = "email", column = "email"),
            @Result(property = "firstName", column = "firstName"),
            @Result(property = "lastName", column = "lastName"),
            @Result(property = "login", column = "login"),
            @Result(property = "middleName", column = "middleName"),
            @Result(property = "passwordHash", column = "passwordHash"),
            @Result(property = "role", column = "role")})
    User findOneByLogin(@NotNull @Param("login") final String login);

    @Update("UPDATE tm.app_user SET " +
            "passwordHash = #{passwordHash}, login = #{login}")
    void updatePassword(@NotNull final String login, @NotNull final String newPassword);

}
