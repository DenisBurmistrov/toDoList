package ru.burmistrov.tm.command.task;

import ru.burmistrov.tm.Bootstrap;
import ru.burmistrov.tm.command.AbstractCommand;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

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
            System.out.println("Введите имя задачи:");
            String oldName = reader.readLine();
            System.out.println("Введите новое имя:");
            String newName = reader.readLine();
            if (newName != null) {
                System.out.println("Введите новое описание: ");
                String description = reader.readLine();
                System.out.println("Введите новый приоритет(от 0 до 5): ");
                String priority = reader.readLine();
                System.out.println(super.getBootstrap().getTaskService().persist(projectId, oldName, newName, description, priority));
            } else {
                System.out.println("У проекта нет задач");
            }
        } catch (IOException e) {
            System.out.println("Некорректное значение ID");
        }
    }
}
