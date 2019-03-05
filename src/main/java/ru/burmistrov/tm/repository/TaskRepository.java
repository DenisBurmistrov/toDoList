package ru.burmistrov.tm.repository;

import ru.burmistrov.tm.Bootstrap;
import ru.burmistrov.tm.api.ITaskRepository;
import ru.burmistrov.tm.entity.Project;
import ru.burmistrov.tm.entity.Task;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TaskRepository implements ITaskRepository {

    private Map<String, Task> tasks = Bootstrap.tasks;

    @Override
    public String merge(String projectId, String oldName , String newName, String description, Integer priority) {

        tasks.forEach((key, value) -> {
            if (oldName.equals(value.getName()) && projectId.equals(value.getProjectId())) {
                Task task = new Task();
                task.setId(value.getId());
                task.setName(newName);
                task.setDescription(description);
                task.setPriority(priority);
                task.setProjectId(projectId);
                tasks.put(key, task);
            }
        });
        return "";

    }

    @Override
    public String persist(String projectId, String name, String description, Integer priority) {

        Task task = new Task();
        task.setName(name);
        task.setDescription(description);
        task.setProjectId(projectId);
        if (!tasks.containsValue(task)) {
            boolean isSetPriority = task.setPriority(priority);
            if (isSetPriority) {
                tasks.put(task.getId(), task);
                return "Задача добавлена в проект c ID: " + projectId + "";
            } else return "";
        } else {
            return "Нет проекта с введенным ID";
        }
    }

    @Override
    public void remove(String projectId, String taskId) {

        tasks.entrySet().removeIf((k) ->
            (k != null && projectId.equals(k.getValue().getProjectId()) && taskId.equals(k.getValue().getId())));
    }


    @Override
    public Map<String, Task> findAll(String projectId) {
        Map <String, Task> result = new LinkedHashMap<>();
        tasks.entrySet().stream().filter(e -> e.getValue().getProjectId().equals(projectId)).forEach(e -> result.put(e.getKey(), e.getValue()));

        return result;
    }



    @Override
    public void removeAll(String projectId) {
        tasks.entrySet().removeIf((v) -> v.getValue().getProjectId().equals(projectId));
    }

    @Override
    public Task findOne(String projectId, String name) {
        Task task;

        List list = tasks.entrySet().stream().filter(v -> projectId.equals(v.getValue().getProjectId()) && name.equals(v.getValue().getName())).collect(Collectors.toList());
        task = (Task) list.get(0);
        return task;
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
