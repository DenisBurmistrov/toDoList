package ru.burmistrov.tm.repository;

import ru.burmistrov.tm.Bootstrap;
import ru.burmistrov.tm.api.ITaskRepository;
import ru.burmistrov.tm.entity.Project;
import ru.burmistrov.tm.entity.Task;

import java.util.Map;

public class TaskRepository implements ITaskRepository {

    private Map<Long, Project> projects = Bootstrap.projects;

    @Override
    public String addTaskToProjectWithTaskId(Long projectId, String name, String description, Integer priority, Long taskId) {

        if (projects.containsKey(projectId)) {
            Task task = new Task();
            task.setId(taskId);
            task.setName(name);
            task.setDescription(description);

            boolean isSetPriority = task.setPriority(priority);
            if (isSetPriority) {
                projects.get(projectId).addTask(task);
                return "Задача обновлена в проекте \"" + projects.get(projectId).getName() + "\"";
            }
            else return "";
        } else {
            return "Нет проекта с введенным ID";
        }
    }

    @Override
    public String addTaskToProject(Long projectId, String name, String description, Integer priority) {
        if (projects.containsKey(projectId)) {
            Task task = new Task();
            task.setName(name);
            task.setDescription(description);

            boolean isSetPriority = task.setPriority(priority);
            if (isSetPriority) {
                projects.get(projectId).addTask(task);
                return "Задача добавлена в проект \"" + projects.get(projectId).getName() + "\"";
            }
            else return "";
        } else {
            return "Нет проекта с введенным ID";
        }
    }

    @Override
    public String deleteTaskFromProject(Long projectId, Long taskId) {

        if (projects.containsKey(projectId)) {
            projects.get(projectId).getTasks().entrySet().removeIf((e -> e.getKey().equals(taskId)));
            return "";
        } else {
            return "Нет проекта с введённым ID";
        }
    }


    @Override
    public Map<Long, Task> printTasksOfProject(Long projectId) {
        if (projects.containsKey(projectId)) {
            return projects.get(projectId).getTasks();
        } else {
           return  null;
        }
    }

    @Override
    public String clearAllTasks(Long projectId) {

            if (projects.containsKey(projectId)) {
                projects.get(projectId).getTasks().clear();
                return "Очистка произведена";
            } else {
                return "Нет проекта с введенным ID";
            }
    }
     /*@Override
     public String updateTaskFromProject() {
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
