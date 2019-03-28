package ru.burmistrov.tm.repository;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.burmistrov.tm.entity.Session;

public interface SessionRepository {

    @Insert("INSERT INTO tm.app_session " +
            "(id, signature, timesTamp, user_id) VALUES (#{id}, #{signature}," +
            " #{timesTamp}, #{userId})")
    void persist(@NotNull @Param("id") final String id,
                    @NotNull @Param("signature") final String signature,
                    @Param("timesTamp") final long timesTamp,
                    @NotNull @Param("userId") final String userId);

    @Nullable
    @Select("SELECT * FROM tm.app_session WHERE id = #{id} AND user_id = #{userId}")
    Session findOne(@NotNull @Param("id") final String id,
                    @NotNull @Param("userId") final String userId);
}
