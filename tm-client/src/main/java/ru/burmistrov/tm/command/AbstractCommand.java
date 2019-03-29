package ru.burmistrov.tm.command;

import org.jetbrains.annotations.NotNull;
import ru.burmistrov.tm.api.loader.ServiceLocator;
import ru.burmistrov.tm.endpoint.*;

import java.io.IOException;

public abstract class AbstractCommand {

    private ServiceLocator serviceLocator;

    public AbstractCommand(){
    }

    @NotNull
    public abstract String getName();

    @NotNull
    public abstract String getDescription();

    public abstract void execute() throws Exception_Exception, IOException;

    @NotNull
    public ServiceLocator getServiceLocator() {
        return serviceLocator;
    }

    public abstract boolean isSecure();

    public void setServiceLocator(@NotNull ServiceLocator serviceLocator) {
        this.serviceLocator = serviceLocator;
    }

}
