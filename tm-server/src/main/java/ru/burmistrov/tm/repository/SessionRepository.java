package ru.burmistrov.tm.repository;

import org.jetbrains.annotations.NotNull;
import ru.burmistrov.tm.api.repository.ISessionRepository;
import ru.burmistrov.tm.entity.Session;
import ru.burmistrov.tm.utils.PasswordUtil;
import ru.burmistrov.tm.utils.SignatureUtil;
import ru.burmistrov.tm.exception.ValidateAccessException;

import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedHashMap;
import java.util.Objects;
import java.util.Properties;

public class SessionRepository extends AbstractRepository<Session> implements ISessionRepository {


    public SessionRepository(LinkedHashMap abstractMap) {
        super(abstractMap);
    }

    @NotNull
    private final LinkedHashMap<String, Session> sessions = getAbstractMap();

    @NotNull
    public Session persist(@NotNull Session session) throws IOException {

        InputStream inputStream;
        Properties property = new Properties();

        inputStream = this.getClass().getClassLoader().getResourceAsStream("application.properties");
        property.load(inputStream);
        String cycle = property.getProperty("cycle");
        String salt = property.getProperty("salt");

        session.setSignature(SignatureUtil.sign(String.valueOf(session.hashCode()), Integer.parseInt(cycle), salt));
        sessions.put(session.getId(), session);
        return session;
    }

    @Override
    public boolean validate(Session session) throws CloneNotSupportedException, ValidateAccessException {
        if(session == null) throw new ValidateAccessException();
        else if(session.getSignature() == null) throw new ValidateAccessException();
        else if(session.getUserId() == null) throw new ValidateAccessException();
        else if (session.getTimesTemp() == null) throw new ValidateAccessException();
        final Session temp = (Session) session.clone();
        if(temp == null) throw new ValidateAccessException();
        String sourceSignature = PasswordUtil.hashPassword(String.valueOf(session.hashCode()));
        String targetSignature = PasswordUtil.hashPassword(String.valueOf(temp.hashCode()));
        boolean check = Objects.requireNonNull(sourceSignature).equals(targetSignature);
        if(!check) throw new ValidateAccessException();
        return sessions.containsKey(session.getId());
    }

}
