package ru.burmistrov.command.task;

import ru.burmistrov.command.AbstractCommand;
import ru.burmistrov.service.TaskService;

public class TaskRemoveCommand extends AbstractCommand {

    private TaskService taskService = new TaskService();

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
        taskService.deleteTaskFromProject();
    }
}
