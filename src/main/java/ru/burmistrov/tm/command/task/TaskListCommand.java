package ru.burmistrov.tm.command.task;

import ru.burmistrov.tm.Bootstrap;
import ru.burmistrov.tm.command.AbstractCommand;
import ru.burmistrov.tm.entity.Task;
import ru.burmistrov.tm.service.TaskService;

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
            Map<Long, Task> tasks = super.getBootstrap().getTaskService().printTasksOfProject(id);
            if(tasks == null) {
                System.out.println("Нет проекта с веденный ID");
            }
            else {
                System.out.println("Список задачь проекта:");
                tasks.forEach((k, v) -> System.out.println(v));
            }
        } catch (IOException e) {
            System.out.println("Некорректное значение ID");
        }
        catch (NumberFormatException e) {
            System.out.println("Некорректные введенные данные");
        }

    }
}
