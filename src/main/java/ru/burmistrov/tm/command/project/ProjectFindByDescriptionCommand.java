package ru.burmistrov.tm.command.project;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.burmistrov.tm.api.service.IProjectService;
import ru.burmistrov.tm.command.AbstractCommand;
import ru.burmistrov.tm.entity.AbstractEntity;
import ru.burmistrov.tm.entity.Project;

import java.util.Scanner;

public class ProjectFindByDescriptionCommand extends AbstractCommand {
    @NotNull
    @Override
    public String getName() {
        return "-printProjectByDescription";
    }

    @NotNull
    @Override
    public String getDescription() {
        return "Print project by description";
    }

    @Override
    public void execute() {
        @Nullable final IProjectService<AbstractEntity> projectService = getServiceLocator().getProjectService();
        @Nullable final Scanner scanner = getServiceLocator().getScanner();
        System.out.println("Введите описание проекта:");
        @NotNull final String description = scanner.nextLine();
        System.out.println("Проект:");
        @NotNull final Project project = (Project) projectService.findOneByDescription(getServiceLocator().getCurrentUser().getId(), description);
        if (project == null) {
            System.out.println("Нет проекта с таким описанием");
        } else {
            System.out.println(project);
        }
    }

    @Override
    public boolean isSecure() {
        return true;
    }
}
