package ru.burmistrov.tm.command.task;

import ru.burmistrov.tm.Bootstrap;
import ru.burmistrov.tm.command.AbstractCommand;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TaskClearCommand extends AbstractCommand {

    public TaskClearCommand(Bootstrap bootstrap) {
        super(bootstrap);
    }

    @Override
    public String getName() {
        return "-clearTasks";
    }

    @Override
    public String getDescription() {
        return "Clear project tasks by ID";
    }

    @Override
    public void execute() {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
       try {

           System.out.println("Введите ID проекта:");
           String id = bufferedReader.readLine();
           super.getBootstrap().getTaskService().removeAll(id);
       }
       catch (IOException e) {
           System.out.println("Некорректно введенны данные");
       }
    }
}
