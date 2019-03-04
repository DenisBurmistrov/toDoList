package ru.burmistrov.tm.command;

import ru.burmistrov.tm.Bootstrap;

public abstract class AbstractCommand {

    private Bootstrap bootstrap;

    public AbstractCommand(Bootstrap bootstrap) {
        this.bootstrap = bootstrap;
    }

    abstract public String command();

    abstract public String description();

    abstract public void execute();

    public Bootstrap getBootstrap() {
        return bootstrap;
    }
}
