package ru.burmistrov.tm.command.project;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.burmistrov.tm.api.service.IProjectService;
import ru.burmistrov.tm.command.AbstractCommand;

import java.text.ParseException;

public final class ProjectCreateCommand extends AbstractCommand {

    public ProjectCreateCommand() {
    }

    @NotNull
    @Override
    public String getName() {
        return "-createProject";
    }

    @NotNull
    @Override
    public String getDescription() {
        return "Create new project";
    }

    @Override
    public void execute() throws ParseException {
        System.out.println("Введите имя:");
        @NotNull final String name = getServiceLocator().getTerminalCommandService().nextLine();
        if (name.length() == 0) {
            System.out.println("Нельзя использовать пустое имя");
        }
        System.out.println("Введите описание:");
        @NotNull final String description = getServiceLocator().getTerminalCommandService().nextLine();
        System.out.println("Введите дату окончания (Пример: 27.10.2019):");
        @NotNull final String date = getServiceLocator().getTerminalCommandService().nextLine();
        getServiceLocator().getProjectEndpoint().createProject( , name, description, date);
    }

    @Override
    public boolean isSecure() {
        return true;
    }
}
