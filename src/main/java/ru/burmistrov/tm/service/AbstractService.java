package ru.burmistrov.tm.service;


import org.jetbrains.annotations.NotNull;

public abstract class AbstractService {

    @NotNull
    abstract public void removeAll(@NotNull String userId);
}
