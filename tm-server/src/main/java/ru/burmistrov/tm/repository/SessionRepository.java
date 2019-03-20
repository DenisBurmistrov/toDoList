package ru.burmistrov.tm.repository;

import org.jetbrains.annotations.NotNull;
import ru.burmistrov.tm.api.repository.ISessionRepository;
import ru.burmistrov.tm.entity.Role;
import ru.burmistrov.tm.entity.Session;
import ru.burmistrov.tm.utils.PasswordUtil;
import ru.burmistrov.tm.utils.SignatureUtil;

import java.util.LinkedHashMap;
import java.util.Objects;

public class SessionRepository extends AbstractRepository<Session> implements ISessionRepository {


    public SessionRepository(LinkedHashMap abstractMap) {
        super(abstractMap);
    }

    @NotNull
    private final LinkedHashMap<String, Session> sessions = getAbstractMap();

    @NotNull
    public Session persist(@NotNull Session session) {
        session.setSignature(SignatureUtil.sign(String.valueOf(session.hashCode())));
        sessions.put(session.getId(), session);
        return session;
    }

    @Override
    public boolean validate(Session session) throws CloneNotSupportedException {
        if(session == null) return false;
        else if(session.getSignature() == null) return false;
        else if(session.getUserId() == null) return false;
        else if (session.getTimesTemp() == null) return false;
        final Session temp = (Session) session.clone();
        if(temp == null) return false;
        String sourceSignature = PasswordUtil.hashPassword(String.valueOf(session.hashCode()));
        String targetSignature = PasswordUtil.hashPassword(String.valueOf(temp.hashCode()));
        boolean check = Objects.requireNonNull(sourceSignature).equals(targetSignature);
        if(!check) return false;
        return sessions.containsKey(session.getId());
    }

}
