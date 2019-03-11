package ru.burmistrov.tm.entity;

import lombok.Data;
import ru.burmistrov.tm.utils.PasswordUtil;

import java.util.Objects;

@Data
public final class User extends AbstractEntity {

    private String firstName;

    private String middleName;

    private String lastName;

    private String login;

    private String password;

    private String email;

    private Role role;

    public void setPassword(String password) {
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
}
