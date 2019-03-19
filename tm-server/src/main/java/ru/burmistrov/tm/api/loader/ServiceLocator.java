package ru.burmistrov.tm.api.loader;

import org.jetbrains.annotations.NotNull;
import ru.burmistrov.tm.api.service.IProjectService;
import ru.burmistrov.tm.api.service.ISessionService;
import ru.burmistrov.tm.api.service.ITaskService;
import ru.burmistrov.tm.api.service.IUserService;

public interface ServiceLocator {

    @NotNull
    IProjectService getProjectService();

    @NotNull
    ITaskService getTaskService();

    @NotNull
    IUserService getUserService();

    @NotNull
    ISessionService getSessionService();

    void init();

}
