package ru.burmistrov.tm.api.loader;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.burmistrov.tm.api.service.IProjectService;
import ru.burmistrov.tm.api.service.ITaskService;
import ru.burmistrov.tm.api.service.IUserService;
import ru.burmistrov.tm.command.AbstractCommand;
import ru.burmistrov.tm.entity.AbstractEntity;
import ru.burmistrov.tm.entity.User;
import ru.burmistrov.tm.service.TerminalCommandService;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.text.ParseException;
import java.util.Map;

public interface ServiceLocator {

    @NotNull
    IProjectService<AbstractEntity> getProjectService();

    @NotNull
    ITaskService<AbstractEntity> getTaskService();

    @NotNull
    IUserService<AbstractEntity> getUserService();

    @NotNull
    Map<String, AbstractCommand> getCommands();

    @NotNull
    TerminalCommandService getTerminalCommandService();

    @NotNull
    User getCurrentUser();

    void setCurrentUser(@Nullable User user);

    void execute(@Nullable String command) throws ParseException, IOException, JAXBException;
}
