package ru.burmistrov.tm.command;

import ru.burmistrov.tm.Bootstrap;
import ru.burmistrov.tm.entity.Role;

public abstract class AbstractCommand {

    private Bootstrap bootstrap;

    public AbstractCommand(Bootstrap bootstrap) {
        this.bootstrap = bootstrap;
    }

    public abstract String getName();

    public abstract String getDescription();

    public abstract void execute();

    public Bootstrap getBootstrap() {
        return bootstrap;
    }

    public abstract boolean isSecure();

}
