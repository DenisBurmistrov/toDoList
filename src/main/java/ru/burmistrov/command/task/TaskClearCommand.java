package ru.burmistrov.command.task;

import ru.burmistrov.command.AbstractCommand;
import ru.burmistrov.service.TaskService;

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
        taskService.clearAllTasks();
    }
}
