package ru.burmistrov.tm.command.project;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.burmistrov.tm.api.loader.ServiceLocator;
import ru.burmistrov.tm.command.AbstractCommand;
import ru.burmistrov.tm.endpoint.Exception_Exception;
import ru.burmistrov.tm.endpoint.ProjectEndpoint;
import ru.burmistrov.tm.endpoint.Session;
import ru.burmistrov.tm.service.TerminalCommandService;

import java.util.Objects;

@Component
public final class ProjectCreateCommand extends AbstractCommand {

    @Autowired
    private ServiceLocator serviceLocator;

    @Autowired
    private ProjectEndpoint projectEndpoint;


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
        @NotNull final String name = serviceLocator.getTerminalCommandService().nextLine();
        if (name.length() == 0) {
            System.out.println("Нельзя использовать пустое имя");
        }
        System.out.println("Введите описание:");
        @NotNull final String description = serviceLocator.getTerminalCommandService().nextLine();
        System.out.println("Введите дату окончания (Пример: 27.10.2019):");
        @NotNull final String date = serviceLocator.getTerminalCommandService().nextLine();
        System.out.println("Введите статус(Запланировано || В процессе || Готово): ");
        @NotNull final String status = serviceLocator.getTerminalCommandService().nextLine();
        projectEndpoint.createProject
                (serviceLocator.getSession(), Objects.requireNonNull(serviceLocator.getSession()).getUserId(),
                name, description, date, status);
    }

    @Override
    public boolean isSecure() {
        return true;
    }
}
