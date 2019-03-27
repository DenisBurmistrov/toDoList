package ru.burmistrov.tm.api.repository;

import org.apache.ibatis.annotations.*;
import org.jetbrains.annotations.NotNull;
import ru.burmistrov.tm.entity.User;
import ru.burmistrov.tm.entity.enumerated.Role;

import java.util.List;

public interface IUserRepository {

    @NotNull
    String persist = "INSERT INTO tm.app_user " +
            "(id, email, firstName, lastName, login, middleName, passwordHash, role) VALUES (#{id}, #{email}, #{firstName}, #{lastName}, #{login}, #{middleName}, #{passwordHash}, #{role})";

    @NotNull String merge = "UPDATE tm.app_task SET firstName = #{firstName}, lastName = #{lastName}," +
            " middleName = #{middleName}, email = #{email} WHERE id = #{id} ";

    @NotNull String deleteById = "DELETE from tm.app_user WHERE id = #{id}";

    @NotNull String deleteAll = "DELETE from tm.app_user";

    @NotNull String findAll = "SELECT * FROM tm.app_user";

    @NotNull String updatePasswordByLogin = "UPDATE tm.app_user SET " +
            "passwordHash = #{passwordHash}, login = #{login}";

    @NotNull String findOneById = "SELECT * FROM tm.app_user WHERE id = #{id}";

    @NotNull String findOneByLogin = "SELECT * FROM tm.app_user WHERE login = #{login}";


    @Insert(persist)
    void persist(@NotNull @Param("id") final String id, @NotNull @Param("email") final String email,
                 @NotNull @Param("firstName") final String firstName, @NotNull @Param("lastName") final String lastName,
                 @NotNull @Param("login") final String login, @NotNull @Param("middleName") final String middleName,
                 @NotNull @Param("passwordHash") final String passwordHash, @NotNull @Param("role") final Role role);

    @Update(merge)
    void merge(@NotNull final User user);

    @Delete(deleteById)
    void remove(@NotNull @Param("id") final String id);

    @Delete(deleteAll)
    void removeAll();

    @Select(findAll)
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


    @Select(findOneById)
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

    @Select(findOneByLogin)
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

    @Update(updatePasswordByLogin)
    void updatePassword(@NotNull final String login, @NotNull final String newPassword);

}
