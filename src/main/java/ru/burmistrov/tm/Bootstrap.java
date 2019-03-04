package ru.burmistrov.tm;

import ru.burmistrov.tm.command.AbstractCommand;
import ru.burmistrov.tm.command.PrintListCommand;
import ru.burmistrov.tm.command.project.ProjectClearCommand;
import ru.burmistrov.tm.command.project.ProjectCreateCommand;
import ru.burmistrov.tm.command.project.ProjectListCommand;
import ru.burmistrov.tm.command.project.ProjectRemoveCommand;
import ru.burmistrov.tm.command.task.TaskClearCommand;
import ru.burmistrov.tm.command.task.TaskCreateCommand;
import ru.burmistrov.tm.command.task.TaskListCommand;
import ru.burmistrov.tm.command.task.TaskRemoveCommand;
import ru.burmistrov.tm.entity.Project;
import ru.burmistrov.tm.repository.ProjectRepository;
import ru.burmistrov.tm.repository.TaskRepository;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import java.util.Map;

public class Bootstrap {

    public static Map<String, AbstractCommand> commands = new LinkedHashMap<>();
    public static Map<Long, Project> projects = new LinkedHashMap<>();
    public static ProjectRepository projectRepository = new ProjectRepository();
    public static TaskRepository taskRepository = new TaskRepository();

    public static void init() {

        PrintListCommand printListCommand = new PrintListCommand();
        ProjectListCommand projectListCommand = new ProjectListCommand();
        ProjectCreateCommand projectCreateCommand = new ProjectCreateCommand();
        ProjectRemoveCommand projectRemoveCommand = new ProjectRemoveCommand();
        ProjectClearCommand projectClearCommand = new ProjectClearCommand();
        TaskListCommand taskListCommand = new TaskListCommand();
        TaskCreateCommand taskCreateCommand = new TaskCreateCommand();
        TaskClearCommand taskClearCommand = new TaskClearCommand();
        TaskRemoveCommand taskRemoveCommand = new TaskRemoveCommand();
        commands.put(printListCommand.command(), printListCommand);
        commands.put(projectListCommand.command(), projectListCommand);
        commands.put(projectCreateCommand.command(), projectCreateCommand);
        commands.put(projectRemoveCommand.command(), projectRemoveCommand);
        commands.put(projectClearCommand.command(), projectClearCommand);
        commands.put(taskCreateCommand.command(), taskCreateCommand);
        commands.put(taskListCommand.command(), taskListCommand);
        commands.put(taskRemoveCommand.command(), taskRemoveCommand);
        commands.put(taskClearCommand.command(), taskClearCommand);

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
}




