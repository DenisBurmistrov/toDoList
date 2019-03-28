package ru.burmistrov.tm.api.repository;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.burmistrov.tm.entity.Session;

public interface ISessionRepository {

    void persist(@NotNull final Session session);

    @Nullable
    Session findOne(@NotNull final String id,
                    @NotNull final String userId);
}
