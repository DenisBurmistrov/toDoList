package ru.burmistrov.tm.entity;

public enum Role {

    ADMINISTRATOR("Администратор"),
    COMMON_USER("Обычный пользователь");

    private final String displayName;

    Role(String displayName) {
        this.displayName = displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }
}
