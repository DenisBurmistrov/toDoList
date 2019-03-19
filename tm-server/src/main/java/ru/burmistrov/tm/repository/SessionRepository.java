package ru.burmistrov.tm.repository;

import org.jetbrains.annotations.NotNull;
import ru.burmistrov.tm.api.repository.ISessionRepository;
import ru.burmistrov.tm.entity.Session;
import ru.burmistrov.tm.entity.User;

import java.util.LinkedHashMap;

public class SessionRepository extends AbstractRepository<Session> implements ISessionRepository {

    public SessionRepository(LinkedHashMap abstractMap) {
        super(abstractMap);
    }


    @Override
    public Session persist(@NotNull User user) {
        return null;
    }
}
