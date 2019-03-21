package ru.burmistrov.tm.api.loader;

import org.jetbrains.annotations.NotNull;
import ru.burmistrov.tm.api.service.*;

import java.io.IOException;
import java.text.ParseException;

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

    void init() throws IOException, ParseException;

}
