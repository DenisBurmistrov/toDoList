package ru.burmistrov.tm.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.burmistrov.tm.entity.Session;

public interface ISessionMapper {

    @NotNull String persist = "INSERT INTO tm.app_session " +
            "(id, signature, timesTamp, lastName, user_id) VALUES (#{id}, #{signature}," +
            " #{timesTamp}, #{userId})";

    @NotNull String findOneById = "SELECT * FROM tm.app_session WHERE id = #{id} AND user_id = #{userId})";



    @Nullable
    @Select(findOneById)
    Session findOne(@NotNull @Param("id") final String id,
                    @NotNull @Param("userId") final String userId);

    @NotNull
    @Insert(persist)
    Session persist(@NotNull @Param("id") final String id,
                    @NotNull @Param("signature") final String signature,
                    @NotNull @Param("timesTamp") final long timesTamp,
                    @NotNull @Param("userId") final String userId);
}
