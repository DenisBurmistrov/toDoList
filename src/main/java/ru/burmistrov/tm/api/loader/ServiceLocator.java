package ru.burmistrov.tm.api.loader;

import ru.burmistrov.tm.api.service.IProjectService;
import ru.burmistrov.tm.api.service.ITaskService;
import ru.burmistrov.tm.api.service.IUserService;

public interface ServiceLocator {

    IProjectService getProjectService();

    ITaskService getTaskService();

    IUserService getUserService();
}
