package ru.burmistrov.tm.repository;

import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.burmistrov.tm.api.repository.ISessionRepository;
import ru.burmistrov.tm.entity.Project;
import ru.burmistrov.tm.entity.Session;
import ru.burmistrov.tm.entity.enumerated.FieldConst;
import ru.burmistrov.tm.utils.PasswordUtil;
import ru.burmistrov.tm.utils.SignatureUtil;
import ru.burmistrov.tm.exception.ValidateAccessException;

import java.io.IOException;
import java.io.InputStream;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.Objects;
import java.util.Properties;

@NoArgsConstructor
public class SessionRepository extends AbstractRepository<Session> implements ISessionRepository {

    @Nullable
    private Connection connection;

    public SessionRepository(@Nullable Connection connection) {
        this.connection = connection;
    }

    @NotNull
    public Session persist(@NotNull final Session session) throws IOException, NoSuchAlgorithmException, SQLException {

        InputStream inputStream;
        Properties property = new Properties();

        inputStream = this.getClass().getClassLoader().getResourceAsStream("application.properties");
        property.load(inputStream);
        String cycle = property.getProperty("cycle");
        String salt = property.getProperty("salt");

        session.setSignature(SignatureUtil.sign(String.valueOf(session.hashCode()), Integer.parseInt(cycle), salt));

        String query = "INSERT INTO tm.app_session (id, signature, timestamp, user_id) \n" +
                " VALUES ('" + session.getId() + "', '" + session.getSignature() + "', '"
                + session.getTimesTamp() + "', '" + session.getUserId() + "');";
        Statement statement = Objects.requireNonNull(connection).createStatement();
        int resultSet = statement.executeUpdate(query);
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


        return map.containsKey(session.getId());
    }

    public Session findOne(@NotNull Project abstractEntity) throws SQLException {
        @NotNull final String query =
                "SELECT * FROM app_session WHERE id = ?";
        @NotNull final PreparedStatement statement =
                Objects.requireNonNull(connection).prepareStatement(query);
        statement.setString(1, abstractEntity.getId());
        @NotNull final ResultSet resultSet = statement.executeQuery();
        @NotNull final Session session = Objects.requireNonNull(fetch(resultSet));
        statement.close();
        return session;
    }

    @Nullable
    @SneakyThrows
    private Session fetch(@Nullable final ResultSet row) {
        if (row == null) return null;
        @NotNull final Session session = new Session();
        session.setId(row.getString(FieldConst.ID));
        session.setSignature(row.getString(FieldConst.SIGNATURE));
        session.setTimesTamp(row.getLong(FieldConst.TIMESTAMP));
        return session;
    }
}
