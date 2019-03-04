package ru.burmistrov.tm.command.task;

import ru.burmistrov.tm.Bootstrap;
import ru.burmistrov.tm.command.AbstractCommand;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TaskRemoveCommand extends AbstractCommand {


    public TaskRemoveCommand(Bootstrap bootstrap) {
        super(bootstrap);
    }

    @Override
    public String command() {
        return "-removeTask";
    }

    @Override
    public String description() {
        return "Remove task by task ID by Project ID";
    }

    @Override
    public void execute() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        try {
            System.out.println("Введите ID проекта");
            String projectId = reader.readLine();
            System.out.println("Введите ID задачи");
            String id = reader.readLine();
            System.out.println(super.getBootstrap().getTaskService().remove(projectId, id));


        } catch (IOException e) {
            System.out.println("Некорректные данные");
        }
        catch (NumberFormatException e) {
            System.out.println("Некорректные введенные данные");
        }
    }
}
