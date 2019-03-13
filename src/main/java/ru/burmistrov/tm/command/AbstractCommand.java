package ru.burmistrov.tm.command;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.burmistrov.tm.api.loader.ServiceLocator;

import java.text.ParseException;

public abstract class AbstractCommand {

    private ServiceLocator serviceLocator;

    public AbstractCommand(){
    }

    @Nullable
    public abstract String getName();

    @Nullable
    public abstract String getDescription();

    public abstract void execute() throws ParseException;

    @Nullable
    public ServiceLocator getServiceLocator() {
        return serviceLocator;
    }

    public abstract boolean isSecure();

    public void setServiceLocator(@NotNull ServiceLocator serviceLocator) {
        this.serviceLocator = serviceLocator;
    }

}
