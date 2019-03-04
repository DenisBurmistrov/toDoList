package ru.burmistrov.tm.command.task;

import ru.burmistrov.tm.Bootstrap;
import ru.burmistrov.tm.command.AbstractCommand;
import ru.burmistrov.tm.entity.Task;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;

public class TaskListCommand extends AbstractCommand {


    public TaskListCommand(Bootstrap bootstrap) {
        super(bootstrap);
    }

    @Override
    public String command() {
        return "-printTasks";
    }

    @Override
    public String description() {
        return "Print tasks of project by project ID";
    }

    @Override
    public void execute() {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Введите ID проекта:");
        try {
            String id = bufferedReader.readLine();
            System.out.println(super.getBootstrap().getTaskService().findAll(id));
        } catch (IOException e) {
            System.out.println("Некорректное значение ID");
        }

    }
}
