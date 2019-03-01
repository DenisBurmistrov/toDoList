package ru.burmistrov.repository;

import ru.burmistrov.entity.Project;
import ru.burmistrov.entity.Task;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class MapOfProjects {

    private static Map<Long, Project> projects = new HashMap<>();
    private static long counter = 1;
    private static MapOfProjects mapOfProjects;

    private MapOfProjects() {
    }

    public static MapOfProjects getInstance() {
        if(mapOfProjects == null){
            mapOfProjects = new MapOfProjects();
        }
        return mapOfProjects;
    }

    public void addDefaultProjects() {

        Project project1 = new Project("Первый проект", "Первое описание", incrementCounter());
        Project project2 = new Project("Второй проект", "Второе описание", incrementCounter());
        Project project3 = new Project("Третий проект", "Третье описание", incrementCounter());
        Project project4 = new Project("Четвертый проект", "Четвертое описание", incrementCounter());
        Project project5 = new Project("Пятый проект", "Пятое описание", incrementCounter());
        Project project6 = new Project("Шестой проект", "Шестое описание", incrementCounter());
        Project project7 = new Project("Седьмой проект", "Седьмое описание", incrementCounter());
        Project project8 = new Project("Восьмой проект", "Восьмое описание", incrementCounter());
        Project project9 = new Project("Девятый проект", "Девятое описание", incrementCounter());
        Project project10 = new Project("Десятый проект", "Десятое описание", incrementCounter());
        projects.put(project1.getId(), project1);
        projects.put(project2.getId(), project2);
        projects.put(project3.getId(), project3);
        projects.put(project4.getId(), project4);
        projects.put(project5.getId(), project5);
        projects.put(project6.getId(), project6);
        projects.put(project7.getId(), project7);
        projects.put(project8.getId(), project8);
        projects.put(project9.getId(), project9);
        projects.put(project10.getId(), project10);
    }

    public Long incrementCounter(){
        return counter++;
    }

    public void addProject() throws IOException {
        Project project = new Project();
        project.setId(incrementCounter());
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Введите имя: ");
        project.setName(bufferedReader.readLine());
        System.out.println("Введите описание: ");
        project.setDescription(bufferedReader.readLine());
        if(projects.containsValue(project)) {
            System.out.println("Такой проект уже существует");
        }
        else {
            projects.put(incrementCounter(), project);
            printProjects();
        }
    }

    public  void deleteProjectById(Long id){
        projects.entrySet().removeIf(e -> e.getValue().getId().equals(id));
        printProjects();
    }

    public void printProjects() {
        System.out.println("Список проектов:");
        projects.forEach((k, v) -> System.out.println(v));
    }

    public void updateNameById(Long id, String name) {
        Iterator it = projects.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry pair = (Map.Entry) it.next();
                if(pair.getKey() == id){
                Project project = new Project((Project) pair.getValue());
                project.setName(name);
                projects.put(id, project);
                printProjects();
                }
        }
    }

    public void addTaskToProject(Long projectId) {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        Task task = new Task();
        System.out.println("Введите имя: ");
        try {
            task.setName(bufferedReader.readLine());
            System.out.println("Введите описание: ");
            task.setDescription(bufferedReader.readLine());
            System.out.println("Введите приоритет от 0 до 5: ");
            task.setPriority(Integer.parseInt(bufferedReader.readLine()));
            projects.get(projectId).addTask(task);
        } catch (IOException e) {
            System.out.println("Некорректное значение приоритета");
        }

        System.out.println("Задача добавлена в проект \"" + projects.get(projectId).getName() +"\"");
    }

    public void deleteTaskFromProject(Long projectId) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        printTasksOfProject(projectId);
        System.out.println("Введите ID задачи");
        try {
            Long id = Long.valueOf(reader.readLine());
            projects.get(projectId).getTasks().entrySet().removeIf((e -> e.getKey().equals(id)));
            printTasksOfProject(projectId);
        } catch (IOException e) {
            System.out.println("Некорректное значение id");
        }
    }

    public void updateTaskFromProject(Long projectId) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        printTasksOfProject(projectId);
        if(projects.get(projectId).getTasks().size() > 0) {
            System.out.println("Введите ID задачи:");
            try {
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
                        printTasksOfProject(projectId);
                    }
                }
                printTasksOfProject(projectId);
            } catch (IOException e) {
                System.out.println("Некорректное значение id");
            }
        }
        else {
            System.out.println("У проекта нет задач");
        }
    }

    public void printTasksOfProject(Long projectId) {
        if(projects.containsKey(projectId)) {
            System.out.println("Список задачь проекта " + projects.get(projectId).getName() + ":");
            projects.get(projectId).getTasks().forEach((k, v) -> System.out.println(v));
        }
    }


    public void deleteAll() {
        projects.clear();
    }




}