package ru.burmistrov.tm.command.task;

import ru.burmistrov.tm.Bootstrap;
import ru.burmistrov.tm.command.AbstractCommand;
import ru.burmistrov.tm.service.ProjectService;
import ru.burmistrov.tm.service.TaskService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.Map;

public class TaskUpdateCommand extends AbstractCommand {


    public TaskUpdateCommand(Bootstrap bootstrap) {
        super(bootstrap);
    }

    @Override
    public String command() {
        return "-updateTask";
    }

    @Override
    public String description() {
        return "Update task by project ID by task ID";
    }

    @Override
    public void execute() {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            System.out.println("Введите ID проекта:");
            String projectId = reader.readLine();
            if(super.getBootstrap().getProjectService().checkContainsProject(projectId)) {
                if (super.getBootstrap().getProjectService().checkHavingTasks(projectId)) {
                    System.out.println("Введите ID задачи:");
                    String taskId = reader.readLine();
                    Iterator it = super.getBootstrap().getProjectService().getProjectTasks(taskId).entrySet().iterator();
                    while (it.hasNext()) {
                        Map.Entry pair = (Map.Entry) it.next();
                        if (pair.getKey() == Long.valueOf(taskId)) {

                            while (true) {
                                System.out.println("Введите новое имя: ");
                                String name = reader.readLine();
                                if(name != null) {
                                    System.out.println("Введите новое описание: ");
                                    String description = reader.readLine();
                                    System.out.println("Введите новый приоритет(от 0 до 5): ");
                                    String priority = reader.readLine();
                                    super.getBootstrap().getTaskService().addTaskToProject(projectId, name, description, priority, taskId);
                                    System.out.println("Задача обновлена");
                                    break;
                                }
                            }

                        }
                    }
                } else {
                    System.out.println("У проекта нет задач");
                }
            }
        } catch (IOException e) {
            System.out.println("Некорректное значение ID");
        }
    }
}
