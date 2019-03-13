package ru.burmistrov.tm.command.project;

import org.jetbrains.annotations.Nullable;
import ru.burmistrov.tm.api.service.IProjectService;
import ru.burmistrov.tm.command.AbstractCommand;
import ru.burmistrov.tm.entity.AbstractEntity;
import ru.burmistrov.tm.entity.Project;

import java.util.List;
import java.util.Scanner;

public class ProjectFindByNameCommand extends AbstractCommand {
    @Nullable
    @Override
    public String getName() {
        return "-printProjectByName";
    }

    @Nullable
    @Override
    public String getDescription() {
        return "Print project by name";
    }

    @Override
    public void execute() {
        if (getServiceLocator() != null) {
            @Nullable final IProjectService<AbstractEntity> projectService = getServiceLocator().getProjectService();
            @Nullable final Scanner scanner = getServiceLocator().getScanner();
            System.out.println("Введите имя проекта:");
            String name = scanner.nextLine();
            System.out.println("Проект:");
            if(projectService != null) {
                @Nullable final Project project = (Project) projectService.findOneByName(getServiceLocator().getCurrentUser().getId(), name);
                if (project == null) {
                    System.out.println("Нет проекта с таким именем");
                } else {
                    System.out.println(project);
                    }
                }
            }
        }
    @Override
    public boolean isSecure() {
        return true;
    }
}
