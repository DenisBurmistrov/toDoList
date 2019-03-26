package ru.burmistrov.tm.repository;

import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.burmistrov.tm.api.repository.ISessionRepository;
import ru.burmistrov.tm.entity.Project;
import ru.burmistrov.tm.entity.Session;
import ru.burmistrov.tm.entity.enumerated.FieldConst;
import ru.burmistrov.tm.mapper.ISessionMapper;
import ru.burmistrov.tm.utils.PasswordUtil;
import ru.burmistrov.tm.utils.SignatureUtil;
import ru.burmistrov.tm.exception.ValidateAccessException;

import java.io.IOException;
import java.io.InputStream;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.Date;
import java.util.Objects;
import java.util.Properties;

@NoArgsConstructor
public class SessionRepository extends AbstractRepository<Session> implements ISessionRepository {

    @Nullable
    private ISessionMapper sessionMapper;

    public SessionRepository(@Nullable ISessionMapper sessionMapper) {
        this.sessionMapper = sessionMapper;
    }

    @NotNull
    public Session persist(@NotNull final String userId) throws IOException, NoSuchAlgorithmException {

        InputStream inputStream;
        Properties property = new Properties();

        inputStream = this.getClass().getClassLoader().getResourceAsStream("application.properties");
        property.load(inputStream);
        String cycle = property.getProperty("cycle");
        String salt = property.getProperty("salt");

        Session session = new Session();
        session.setTimesTamp(new Date().getTime());
        session.setUserId(userId);

        session.setSignature(SignatureUtil.sign(String.valueOf(session.hashCode()), Integer.parseInt(cycle), salt));

        Objects.requireNonNull(sessionMapper).persist(session.getId(), Objects.requireNonNull(session.getSignature()), session.getTimesTamp(), Objects.requireNonNull(session.getUserId()));
        return session;
    }

    @Override
    public boolean validate(@Nullable final Session session) throws CloneNotSupportedException, ValidateAccessException, NoSuchAlgorithmException {
        if(session == null) throw new ValidateAccessException();
        else if(session.getSignature() == null) throw new ValidateAccessException();
        else if(session.getUserId() == null) throw new ValidateAccessException();
        else if (session.getTimesTamp() == 0) throw new ValidateAccessException();
        final Session temp = (Session) session.clone();
        if(temp == null) throw new ValidateAccessException();
        String sourceSignature = PasswordUtil.hashPassword(String.valueOf(session.hashCode()));
        String targetSignature = PasswordUtil.hashPassword(String.valueOf(temp.hashCode()));
        boolean check = Objects.requireNonNull(sourceSignature).equals(targetSignature);
        if(!check) throw new ValidateAccessException();

        return findOne(session.getId(), session.getUserId()) != null;
    }

    @Nullable
    public Session findOne(@NotNull final String id, @NotNull final String userId) {
        return Objects.requireNonNull(sessionMapper).findOne(id, userId);
    }
}
