package ru.burmistrov.tm.api.loader;

import org.jetbrains.annotations.NotNull;
import ru.burmistrov.tm.api.service.*;

public interface ServiceLocator {

    @NotNull
    IProjectService getProjectService();

    @NotNull
    ITaskService getTaskService();

    @NotNull
    IUserService getUserService();

    @NotNull
    ISessionService getSessionService();

    @NotNull
    IAdminService getAdminService();

    void init();

}
