package ru.burmistrov.command.task;

import ru.burmistrov.Bootstrap;
import ru.burmistrov.command.AbstractCommand;
import ru.burmistrov.entity.Task;
import ru.burmistrov.service.TaskService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class TaskListCommand extends AbstractCommand {

    private TaskService taskService = new TaskService();
    private Map<Long, Task> map;

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
            Map<Long, Task> tasks = taskService.printTasksOfProject(id);
            System.out.println("Список задачь проекта:");
            tasks.forEach((k, v) -> System.out.println(v));
        } catch (IOException e) {
            System.out.println("Некорректное значение ID");
        }
        catch (NumberFormatException e) {
            System.out.println("Некорректные введенные данные");
        }

    }
}
