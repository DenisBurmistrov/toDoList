package ru.burmistrov.tm;

import ru.burmistrov.tm.command.AbstractCommand;
import ru.burmistrov.tm.command.PrintListCommand;
import ru.burmistrov.tm.command.project.ProjectClearCommand;
import ru.burmistrov.tm.command.project.ProjectCreateCommand;
import ru.burmistrov.tm.command.project.ProjectListCommand;
import ru.burmistrov.tm.command.project.ProjectRemoveCommand;
import ru.burmistrov.tm.command.task.*;
import ru.burmistrov.tm.entity.Project;
import ru.burmistrov.tm.repository.ProjectRepository;
import ru.burmistrov.tm.repository.TaskRepository;
import ru.burmistrov.tm.service.ProjectService;
import ru.burmistrov.tm.service.TaskService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;

public class Bootstrap {

    private static Bootstrap bootstrap;

    public static Map<String, AbstractCommand> commands = new LinkedHashMap<>();
    public static  Map<Long, Project> projects = new LinkedHashMap<>();
    public static  ProjectRepository projectRepository = new ProjectRepository();
    public static  TaskRepository taskRepository = new TaskRepository();
    public static  ProjectService projectService = new ProjectService(projectRepository);
    public static  TaskService taskService = new TaskService(taskRepository);

    public static Bootstrap getInstance() {
        if (bootstrap == null) {
            return bootstrap = new Bootstrap();
        } else {
            return bootstrap;
        }
    }


    private Bootstrap() {
    }

    public static void init() {

        Bootstrap bootstrap = getInstance();
        System.out.println(bootstrap);
        PrintListCommand printListCommand = new PrintListCommand(bootstrap);
        ProjectListCommand projectListCommand = new ProjectListCommand(bootstrap);
        ProjectCreateCommand projectCreateCommand = new ProjectCreateCommand(bootstrap);
        ProjectRemoveCommand projectRemoveCommand = new ProjectRemoveCommand(bootstrap);
        ProjectClearCommand projectClearCommand = new ProjectClearCommand(bootstrap);
        TaskListCommand taskListCommand = new TaskListCommand(bootstrap);
        TaskCreateCommand taskCreateCommand = new TaskCreateCommand(bootstrap);
        TaskClearCommand taskClearCommand = new TaskClearCommand(bootstrap);
        TaskRemoveCommand taskRemoveCommand = new TaskRemoveCommand(bootstrap);
        TaskUpdateCommand taskUpdateCommand = new TaskUpdateCommand(bootstrap);
        commands.put(printListCommand.command(), printListCommand);
        commands.put(projectListCommand.command(), projectListCommand);
        commands.put(projectCreateCommand.command(), projectCreateCommand);
        commands.put(projectRemoveCommand.command(), projectRemoveCommand);
        commands.put(projectClearCommand.command(), projectClearCommand);
        commands.put(taskCreateCommand.command(), taskCreateCommand);
        commands.put(taskListCommand.command(), taskListCommand);
        commands.put(taskRemoveCommand.command(), taskRemoveCommand);
        commands.put(taskClearCommand.command(), taskClearCommand);
        commands.put(taskUpdateCommand.command(), taskUpdateCommand);

        Project project1 = new Project("Первый проект", "Первое описание", projectRepository.incrementCounter());
        Project project2 = new Project("Второй проект", "Второе описание", projectRepository.incrementCounter());
        Project project3 = new Project("Третий проект", "Третье описание", projectRepository.incrementCounter());
        Project project4 = new Project("Четвертый проект", "Четвертое описание", projectRepository.incrementCounter());
        Project project5 = new Project("Пятый проект", "Пятое описание", projectRepository.incrementCounter());
        Project project6 = new Project("Шестой проект", "Шестое описание", projectRepository.incrementCounter());
        Project project7 = new Project("Седьмой проект", "Седьмое описание", projectRepository.incrementCounter());
        Project project8 = new Project("Восьмой проект", "Восьмое описание", projectRepository.incrementCounter());
        Project project9 = new Project("Девятый проект", "Девятое описание", projectRepository.incrementCounter());
        Project project10 = new Project("Десятый проект", "Десятое описание", projectRepository.incrementCounter());
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

        start();


    }

    private static void execute(String command) {
        if (command == null || command.isEmpty()) {
            System.out.println("Команда введена некорректно");
            return;
        }
        AbstractCommand abstractCommand = commands.get(command);
        if (abstractCommand == null) {
            System.out.println("Такой команды не существует");
            return;
        } else {
            abstractCommand.execute();
        }
    }

    private static void start() {
        System.out.println("    [ToDoList]\nВведите -help чтобы вывести список команд");
        while (true) {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            try {
                String input = bufferedReader.readLine();
                if ("-exit".equals(input)) {
                    System.exit(0);
                } else {
                    execute(input);
                }
            } catch (IOException e) {
                System.out.println("Некорректные данные");
            }
        }
    }

    public Map<String, AbstractCommand> getCommands() {
        return commands;
    }

    public void setCommands(Map<String, AbstractCommand> commands) {
        this.commands = commands;
    }

    public Map<Long, Project> getProjects() {
        return projects;
    }

    public void setProjects(Map<Long, Project> projects) {
        this.projects = projects;
    }

    public ProjectRepository getProjectRepository() {
        return projectRepository;
    }

    public void setProjectRepository(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public TaskRepository getTaskRepository() {
        return taskRepository;
    }

    public void setTaskRepository(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public ProjectService getProjectService() {
        return projectService;
    }

    public void setProjectService(ProjectService projectService) {
        this.projectService = projectService;
    }

    public TaskService getTaskService() {
        return taskService;
    }

    public void setTaskService(TaskService taskService) {
        this.taskService = taskService;
    }
}




