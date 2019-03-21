package ru.burmistrov.tm.command.project;

import org.jetbrains.annotations.NotNull;
import ru.burmistrov.tm.command.AbstractCommand;
import ru.burmistrov.tm.endpoint.Exception_Exception;

import java.util.Objects;

public final class ProjectCreateCommand extends AbstractCommand {

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
    public void execute() throws Exception_Exception {
        System.out.println("Введите имя:");
        @NotNull final String name = getServiceLocator().getTerminalCommandService().nextLine();
        if (name.length() == 0) {
            System.out.println("Нельзя использовать пустое имя");
        }
        System.out.println("Введите описание:");
        @NotNull final String description = getServiceLocator().getTerminalCommandService().nextLine();
        System.out.println("Введите дату окончания (Пример: 27.10.2019):");
        @NotNull final String date = getServiceLocator().getTerminalCommandService().nextLine();
        getServiceLocator().getProjectEndpoint().createProject(getServiceLocator().getSession(), Objects.requireNonNull(getServiceLocator().getSession().getUserId()),
                name, description, date);
    }

    @Override
    public boolean isSecure() {
        return true;
    }
}
