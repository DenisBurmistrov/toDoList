package ru.burmistrov.tm.entity;

import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.burmistrov.tm.entity.enumerated.Role;
import ru.burmistrov.tm.utils.PasswordUtil;

import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

@Getter
@Setter
public final class User extends AbstractEntity implements Serializable {

    @Nullable
    private String firstName;

    @Nullable
    private String middleName;

    @Nullable
    private String lastName;

    @Nullable
    private String login;

    @Nullable
    private String password;

    @Nullable
    private String email;

    @Nullable
    private Role role;

    public void setHashPassword(@NotNull final String password) throws NoSuchAlgorithmException {
        this.password = PasswordUtil.hashPassword(password);
    }

    public void setPassword(@Nullable String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User: " + firstName + " " + middleName + " " + lastName + "; Login: " + login + "; Role: " + role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(login, user.login);
    }

    @Override
    public int hashCode() {
        return Objects.hash(login);
    }
}
