package ru.burmistrov.tm.api.loader;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.burmistrov.tm.api.service.IProjectService;
import ru.burmistrov.tm.api.service.ITaskService;
import ru.burmistrov.tm.api.service.IUserService;
import ru.burmistrov.tm.command.AbstractCommand;
import ru.burmistrov.tm.entity.AbstractEntity;
import ru.burmistrov.tm.entity.User;

import java.util.Map;
import java.util.Scanner;

public interface ServiceLocator {

    @Nullable
    IProjectService<AbstractEntity> getProjectService();

    @Nullable
    ITaskService<AbstractEntity> getTaskService();

    @Nullable
    IUserService<AbstractEntity> getUserService();

    @NotNull
    Map<String, AbstractCommand> getCommands();

    @NotNull
    Scanner getScanner();

    @Nullable
    User getCurrentUser();

    void setCurrentUser(@Nullable User user);
}
