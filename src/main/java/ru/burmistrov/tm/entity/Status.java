package ru.burmistrov.tm.entity;

public enum Status {

    SHEDULED("Запланировано"),
    IN_PROCESS("В процессе"),
    COMPLETE("Готово");

    private final String displayName;

    Status(String displayName) {
        this.displayName = displayName;
    }


    @Override
    public String toString() {
        return displayName;
    }
}
