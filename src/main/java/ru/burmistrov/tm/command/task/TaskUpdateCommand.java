package ru.burmistrov.tm.command.task;

import ru.burmistrov.tm.command.AbstractCommand;
import ru.burmistrov.tm.service.ProjectService;
import ru.burmistrov.tm.service.TaskService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.Map;

public class TaskUpdateCommand extends AbstractCommand {

    private TaskService taskService = new TaskService();
    private ProjectService projectService = new ProjectService();

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
            taskService.printTasksOfProject(projectId);
            if(projectService.checkContainsProject(projectId)) {
                if (projectService.checkHavingTasks(projectId)) {
                    System.out.println("Введите ID задачи:");

                    String taskId = reader.readLine();
                    Iterator it = projectService.getProjectTasks(taskId).entrySet().iterator();
                    while (it.hasNext()) {
                        Map.Entry pair = (Map.Entry) it.next();
                        if (pair.getKey() == taskId) {

                            while (true) {
                                System.out.println("Введите новое имя: ");
                                String name = reader.readLine();
                                if(name != null) {
                                    System.out.println("Введите новое описание: ");
                                    String description = reader.readLine();
                                    System.out.println("Введите новый приоритет(от 0 до 5): ");
                                    String priority = reader.readLine();
                                    taskService.addTaskToProject(projectId, name, description, priority);
                                    taskService.printTasksOfProject(projectId);
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
