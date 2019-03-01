package ru.burmistrov.repository;

import ru.burmistrov.Bootstrap;
import ru.burmistrov.api.repository.ITaskRepository;
import ru.burmistrov.entity.Project;
import ru.burmistrov.entity.Task;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;

public class TaskRepository implements ITaskRepository {

    private Map<Long, Project> projects = Bootstrap.projects;

    @Override
    public void addTaskToProject(Long projectId, String name, String description, Integer priority) {
        if (projects.containsKey(projectId)) {
            Task task = new Task();
            task.setName(name);
            task.setDescription(description);
            task.setPriority(priority);
            projects.get(projectId).addTask(task);
            System.out.println("Задача добавлена в проект \"" + projects.get(projectId).getName() + "\"");
        } else {
            System.out.println("Нет проекта с введенным ID");
        }
    }

    @Override
    public void deleteTaskFromProject(Long projectId, Long taskId) {

        if (projects.containsKey(projectId)) {
            printTasksOfProject(projectId);
            projects.get(projectId).getTasks().entrySet().removeIf((e -> e.getKey().equals(taskId)));
            printTasksOfProject(projectId);
        } else {
            System.out.println("Нет проекта с введённым ID");
        }
    }


    @Override
    public void printTasksOfProject(Long projectId) {

        if (projects.containsKey(projectId)) {
            System.out.println("Список задачь проекта " + projects.get(projectId).getName() + ":");
            projects.get(projectId).getTasks().forEach((k, v) -> System.out.println(v));
        } else {
            System.out.println("Нет проекта с введенным ID");

        }
    }

    @Override
    public void clearAllTasks() {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Введите ID проекта:");
        try {
            Long id = Long.valueOf(bufferedReader.readLine());
            if (projects.containsKey(id)) {
                projects.get(id).getTasks().clear();
            } else {
                System.out.println("Нет проекта с введенным ID");
            }
        } catch (IOException e) {
            System.out.println("Некорректное значение ID");
        }

    }

    /* @Override
     public void updateTaskFromProject() {
         BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
         try {
             System.out.println("Введите ID проекта:");
             Long projectId = Long.valueOf(reader.readLine());
             printTasksOfProjectById(projectId);
             if (projects.get(projectId).getTasks().size() > 0) {
                 System.out.println("Введите ID задачи:");

                 Long id = Long.valueOf(reader.readLine());
                 Iterator it = projects.get(projectId).getTasks().entrySet().iterator();
                 while (it.hasNext()) {
                     Map.Entry pair = (Map.Entry) it.next();
                     if (pair.getKey() == id) {
                         Task task = new Task();
                         task.setId(id);
                         System.out.println("Введите новое имя: ");
                         task.setName(reader.readLine());
                         System.out.println("Введите новое описание: ");
                         task.setDescription(reader.readLine());
                         System.out.println("Введите новый приоритет(от 0 до 5): ");
                         try {
                             task.setPriority(Integer.parseInt(reader.readLine()));
                         } catch (IOException e) {
                             System.out.println("Неккоректное значение приоритета");
                         }
                         projects.get(projectId).getTasks().put(id, task);
                         printTasksOfProjectById(projectId);
                     }
                 }
             } else {
                 System.out.println("У проекта нет задач");
             }
         } catch (IOException e) {
             System.out.println("Некорректное значение ID");
         }

     }*/

}
