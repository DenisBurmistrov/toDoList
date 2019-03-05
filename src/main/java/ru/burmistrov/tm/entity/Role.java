package ru.burmistrov.tm.entity;

public enum Role {

    ADMINISTRATOR, COMMON_USER;

    public String displayName() {
        switch (this){
            case ADMINISTRATOR:
                return "Администратор";
            case COMMON_USER:
                return "Обычный пользователь";
            default:
                return "Пользователь не авторизован";
        }
    }

}
