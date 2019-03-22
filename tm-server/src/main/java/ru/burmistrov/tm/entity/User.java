package ru.burmistrov.tm.entity;

import lombok.Data;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.burmistrov.tm.entity.enumerated.Role;
import ru.burmistrov.tm.utils.PasswordUtil;

import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

@Data
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

    public void setPassword(@NotNull final String password) throws NoSuchAlgorithmException {
        this.password = PasswordUtil.hashPassword(password);
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

    public void setRole(@Nullable String string) {
        if ("Администратор".equals(string)) {
            this.role = Role.ADMINISTRATOR;
        } else {
            this.role = Role.COMMON_USER;
        }
    }

    public void setRole(@Nullable Role role) {
        this.role = role;
    }
}
