package ru.burmistrov.tm.api.loader;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.burmistrov.tm.api.service.IProjectService;
import ru.burmistrov.tm.api.service.ITaskService;
import ru.burmistrov.tm.api.service.IUserService;
import ru.burmistrov.tm.entity.User;

public interface ServiceLocator {

    @NotNull
    IProjectService getProjectService();

    @NotNull
    ITaskService getTaskService();

    @NotNull
    IUserService getUserService();

    @NotNull
    User getCurrentUser();

    void setCurrentUser(@Nullable User user);

    void init();

}
