package ru.burmistrov.command.task;

import ru.burmistrov.command.AbstractCommand;
import ru.burmistrov.service.TaskService;

public class TaskCreateCommand extends AbstractCommand {

    private TaskService taskService = new TaskService();

    @Override
    public String command() {
        return "-createTask";
    }

    @Override
    public String description() {
        return "Create task to project by project ID";
    }

    @Override
    public void execute() {
        taskService.addTaskToProject();
    }
}
