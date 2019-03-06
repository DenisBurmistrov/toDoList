package ru.burmistrov.tm.api.loader;

import ru.burmistrov.tm.api.service.IProjectService;
import ru.burmistrov.tm.api.service.ITaskService;
import ru.burmistrov.tm.api.service.IUserService;
import ru.burmistrov.tm.command.AbstractCommand;
import ru.burmistrov.tm.entity.User;

import java.util.Map;
import java.util.Scanner;

public interface ServiceLocator {

    IProjectService getProjectService();

    ITaskService getTaskService();

    IUserService getUserService();

    Map<String, AbstractCommand> getCommands();

    Scanner getScanner();

    User getCurrentUser();

    void setCurrentUser(User user);
}
