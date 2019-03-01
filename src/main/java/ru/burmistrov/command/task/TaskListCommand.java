package ru.burmistrov.command.task;

import ru.burmistrov.command.AbstractCommand;
import ru.burmistrov.service.TaskService;

public class TaskListCommand extends AbstractCommand {

    private TaskService taskService = new TaskService();

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
        taskService.printTasksOfProject();
    }
}
