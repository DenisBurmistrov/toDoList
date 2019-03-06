package ru.burmistrov.tm.command;

import ru.burmistrov.tm.api.loader.ServiceLocator;

public abstract class AbstractCommand {

    private ServiceLocator serviceLocator;

    public AbstractCommand(ServiceLocator serviceLocator) {
        this.serviceLocator = serviceLocator;
    }

    public abstract String getName();

    public abstract String getDescription();

    public abstract void execute();

    public ServiceLocator getServiceLocator() {
        return serviceLocator;
    }

    public abstract boolean isSecure();

}
