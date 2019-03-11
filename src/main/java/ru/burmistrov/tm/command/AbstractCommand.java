package ru.burmistrov.tm.command;

import org.jetbrains.annotations.NotNull;
import ru.burmistrov.tm.api.loader.ServiceLocator;

public abstract class AbstractCommand {

    @NotNull
    private ServiceLocator serviceLocator;

    public AbstractCommand(@NotNull ServiceLocator serviceLocator) {
        this.serviceLocator = serviceLocator;
    }

    @NotNull
    public abstract String getName();

    @NotNull
    public abstract String getDescription();

    public abstract void execute();

    @NotNull
    public ServiceLocator getServiceLocator() {
        return serviceLocator;
    }

    @NotNull
    public abstract boolean isSecure();

}
