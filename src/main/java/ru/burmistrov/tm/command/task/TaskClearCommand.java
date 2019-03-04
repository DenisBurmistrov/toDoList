package ru.burmistrov.tm.command.task;

import ru.burmistrov.tm.command.AbstractCommand;
import ru.burmistrov.tm.service.TaskService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TaskClearCommand extends AbstractCommand {

    private TaskService taskService = new TaskService();

    @Override
    public String command() {
        return "-clearTasks";
    }

    @Override
    public String description() {
        return "Clear project tasks by ID";
    }

    @Override
    public void execute() {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
       try {

           System.out.println("Введите ID проекта:");
           String id = bufferedReader.readLine();
           System.out.println(taskService.clearAllTasks(id));
       }
       catch (IOException e) {
           System.out.println("Некорректно введенны данные");
       }
    }
}
