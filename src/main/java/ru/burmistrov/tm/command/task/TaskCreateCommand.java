package ru.burmistrov.tm.command.task;

import ru.burmistrov.tm.Bootstrap;
import ru.burmistrov.tm.command.AbstractCommand;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TaskCreateCommand extends AbstractCommand {


    public TaskCreateCommand(Bootstrap bootstrap) {
        super(bootstrap);
    }

    @Override
    public String getName() {
        return "-createTask";
    }

    @Override
    public String getDescription() {
        return "Create task to project by project ID";
    }

    @Override
    public void execute() {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        try {
            System.out.println("Введите ID проекта:");
            String id = bufferedReader.readLine();
            System.out.println("Введите имя задачи");
            String oldName = bufferedReader.readLine();
            System.out.println("Введите описание для задачи: ");
            String description = bufferedReader.readLine();
            System.out.println("Введите приоритет для задачи от 0 до 5: ");
            String priority = bufferedReader.readLine();
            System.out.println(super.getBootstrap().getTaskService().persist(id, oldName, description, priority));
        }
        catch (IOException e) {
            System.out.println("Некорректные данные");
        }


    }
}
