package ru.burmistrov.tm.service;

import com.sun.istack.internal.NotNull;

public abstract class AbstractService {

    @NotNull
    abstract public void removeAll(@NotNull String userId);
}
